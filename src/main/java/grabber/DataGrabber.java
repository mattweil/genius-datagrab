package grabber;


import api.Genius;
import api.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataGrabber {

    public static List<String> songNames;
    public static List<String> artists;

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to genius-datagrab!");
        System.out.println("You can begin by enter a command, type 'help' for more information.");
        System.out.print("gdg> ");
        String command = reader.nextLine().toLowerCase();

        if (command.startsWith("songs ")) {
            String artist = command.substring(command.indexOf(" ") + 1 , command.length());
            System.out.println(artist);
            ArrayList<Song> songList = Genius.getSongs(artist);
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.print(String.format("%-65s", "| Song"));
            System.out.println(String.format("%-10s", "| Primary Artist"));
            System.out.println("------------------------------------------------------------------------------------------------");
            for (Song s: songList) {
                String padded = String.format("%-65s", s.getSongTitle());
                System.out.print(padded);
                System.out.println("| " + String.format("%-25s", s.getPrimaryArtist()) + "|");
            }
        }

    }

    static {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.SEVERE);
    }

}
