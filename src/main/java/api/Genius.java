package api;


import grabber.DataGrabber;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Genius {

    public static String accessToken = "";

    public static String grabArtistID(String artist) throws IOException {
        System.out.println(artist);
        String artistID;
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get("https://genius.com/artists/" + artist);
        String source = driver.getPageSource();
        artistID = StringUtils.substringBetween(source, "s/artists/", "\"");
        System.out.println(artistID);
        return artistID;
    }

    public static Artist grabArtistData(String artist) throws IOException {
        Artist a = new Artist();
        String artistID = grabArtistID(artist);
        String rawData = Request.get("artists/" + artistID);
        String rawAlias = StringUtils.substringBetween(rawData, "alternate_names\":[\"", "],\"");
        rawAlias = StringUtils.replace(rawAlias, "\"", "");
        rawAlias = StringUtils.replace(rawAlias, ",", ", ");
        a.setName("test");
        a.setAliases(rawAlias);
        System.out.println(rawData);
        return a;
    }

    public static ArrayList<Song> grabSongs(String artist) throws IOException {
        ArrayList<Song> songList = new ArrayList<Song>();
        String artistID = grabArtistID(artist);
        for (int page = 1; page < 100; page++) {
            String rawData = Request.get("artists/" + artistID + "/songs?per_page=50&sort=popularity&page=" + page);
            String songTitle[] = StringUtils.substringsBetween(rawData, "\"title\":\"", "\",\"tit");
            String songPrimaryArtist[] = StringUtils.substringsBetween(rawData, "\"name\":\"", "\",\"url");
            if (songTitle == null) {
                break;
            }
            for (int i = 0; i < songTitle.length; i++) {
                if(!songTitle[i].toLowerCase().contains("tracklist") && !songTitle[i].toLowerCase().contains("album art")){
                    Song current = new Song();
                    current.setSongTitle(songTitle[i]);
                    current.setPrimaryArtist(songPrimaryArtist[i]);
                    songList.add(current);
                }
            }
        }
        return songList;
    }

    public static void grabLyrics(String song) throws IOException {
        ArrayList<String> songLyrics = new ArrayList<String>();
        ArrayList<String> responses = new ArrayList<String>();
        //String artistID = grabArtistID(artist);
        song = StringUtils.replace(song, "\'", "").toLowerCase();
        String url = "search?q=" + song + " ";
        String link = StringUtils.replace(url, " ", "%20");
        String rawData = Request.get(link);
        String searchResponse[] = StringUtils.substringsBetween(rawData, "{\"highlight", "}}}");

        for (String response : searchResponse) {
            if (response.contains("\"type\":\"song\"")) {
                responses.add(StringUtils.substringBetween(response, "\"stats\":", "primary_artist"));
            }
        }
        HtmlUnitDriver driver = new HtmlUnitDriver();

        driver.get(StringUtils.substringBetween(searchResponse[0], "\"url\":\"", "\""));


        String rawSource = StringUtils.substringBetween(driver.getPageSource(), "<div class=\"lyrics\">", "<!--/sse-->");

        //System.out.println(rawSource);

        rawSource = StringUtils.replace(rawSource, "&quot", "");

        String deleteList[] = StringUtils.substringsBetween(rawSource, "<a href", ">");


        for (String e: deleteList) {
            rawSource = StringUtils.replace(rawSource, e, "");
        }

        rawSource = StringUtils.replace(rawSource, "<span>", "");
        rawSource = StringUtils.replace(rawSource, "</span>", "");
        rawSource = StringUtils.replace(rawSource, "</p>", "");
        rawSource = StringUtils.replace(rawSource, "<p>", "");
        rawSource = StringUtils.replace(rawSource, "</p>", "");
        rawSource = StringUtils.replace(rawSource, "<!--sse-->", "");
        rawSource = StringUtils.replace(rawSource, "<br>", "");
        rawSource = StringUtils.replace(rawSource, "<br/>", "");
        rawSource = StringUtils.replace(rawSource, "<i>", "");
        rawSource = StringUtils.replace(rawSource, "</i>", "");
        rawSource = StringUtils.replace(rawSource, "<a href>", "");
        rawSource = StringUtils.replace(rawSource, "</a>", "");
       //rawSource = StringUtils.deleteWhitespace(rawSource);



        String rawLyrics[] =  rawSource.split("\\s{5,100}");


        for (String line: rawLyrics) {
            line = StringUtils.trim(line);
            line = StringUtils.strip(line);
            if (line.matches(".*\\w.*") && !line.contains("[")){
                System.out.println(line);
                //System.out.println(line.length());
                songLyrics.add(line);
                //System.out.println(line);
            }

            //System.out.println(rawLyrics[0]);




        }


    }
}