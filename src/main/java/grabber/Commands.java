package grabber;

import api.Artist;
import api.Genius;
import api.Song;

import java.io.IOException;
import java.util.ArrayList;

public class Commands {

    public static void getDataforArtist(String artist) throws IOException {
        Artist a = Genius.grabArtistData(artist);
        System.out.println("Displaying data associated with the artist: " + artist);
        System.out.println("Artist name: " + a.getName());
        System.out.println("Artist aliases: " + a.getAliases());
        //System.out.println("Songs released: " + a.getPrimarySongs());
        //System.out.println("Songs featured on " + a.getSecondarySongs());
    }

    public static void getSongsForArtist(String artist) throws IOException {
        System.out.println("Displaying songs associated with the artist: " + artist);
        ArrayList<Song> songList = Genius.grabSongs(artist);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.print(String.format("%-80s", "Song"));
        System.out.println("| Primary Artist");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for (Song s: songList) {
            String padded = String.format("%-80s", s.getSongTitle());
            System.out.print(padded);
            System.out.println("| " + String.format("%-40s", s.getPrimaryArtist()));
        }
    }

    public static void sendHelp() {
        System.out.println("The genius-datagrabber allows the operator to extract artist specific data using the Genius API.");
        System.out.println("The currently implemented commands are listed below alongside a brief description.");
        System.out.println("________________________________________________________________________________________________");
        System.out.println("lyrics -s [song] -a [artist] - provides a list of songs by a given artist                                  ");
        System.out.println("songs -a [artist] - provides a list of songs by a given artist                                  ");
        System.out.println("                                                                                              ");
        System.out.println("                                                                                              ");
        System.out.println("                                                                                              ");
        System.out.println("                                                                                              ");
        System.out.println("                                                                                              ");
        System.out.println("________________________________________________________________________________________________");
    }

    public static void setToken(String token){
        Genius.accessToken = token;
        System.out.println("Genius token set.");
    }
}
