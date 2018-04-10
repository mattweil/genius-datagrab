package grabber;


import api.Genius;
import api.Song;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DataGrabber {

    public static List<String> songNames;
    public static List<String> artists;

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to genius-datagrab!");
        System.out.println("You can begin by entering a command, type 'help' for more information.");
        waitForCommand();


    }

    public static void waitForCommand() throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.print("gdg> ");
        String command = reader.nextLine().toLowerCase();

        if (command.startsWith("songs ")) {
            String artist = StringUtils.substringAfter(command,"-a ");
            Commands.getSongsForArtist(artist);
            waitForCommand();
        }

        else if (command.startsWith("help")) {
            Commands.sendHelp();
            waitForCommand();


        }

        else if(command.startsWith("artist ")){
            String artist = command.substring(command.indexOf(" ") + 1 , command.length());
            Genius.grabArtistData(artist);
            waitForCommand();
        }

        else if(command.startsWith("lyrics " ) && command.contains("-s ") && command.contains(" -a ")){
            String song = StringUtils.substringBetween(command, "-s ", " -a");
            String artist = StringUtils.substringAfter(command,"-a ");
            Genius.grabLyrics(song, artist);
            waitForCommand();
        }

    }

    static {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.SEVERE);
    }

}
