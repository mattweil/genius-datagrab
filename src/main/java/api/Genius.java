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
        String artistID;
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get("https://genius.com/artists/" + artist);
        String source = driver.getPageSource();
        artistID = StringUtils.substringBetween(source,"s/artists/", "\"");
        return artistID;
    }

    public static ArrayList<Song> getSongs(String artist) throws IOException {
        ArrayList<Song> songList = new ArrayList<Song>();
        String artistID = grabArtistID(artist);
        for (int page = 1; page < 100; page++) {
            String rawData = searchSongsByArtist(artistID, page);
            String songTitle[] = StringUtils.substringsBetween(rawData, "\"title\":\"", "\",\"tit");
            String songPrimaryArtist[] = StringUtils.substringsBetween(rawData, "\"name\":\"", "\",\"url");
            if(songTitle == null){
                break;
            }
            for (int i = 0; i < songTitle.length; i++) {
                Song current = new Song();
                current.setSongTitle(songTitle[i]);
                current.setPrimaryArtist(songPrimaryArtist[i]);
                songList.add(current);
            }
        }
        return songList;
    }


    public static String searchSongsByArtist(String artistID, int page) throws IOException {
        String response = Request.get("artists/" + artistID + "/songs?per_page=50&sort=popularity&page=" + page);
        System.out.println(response);
        return response;
    }


}
