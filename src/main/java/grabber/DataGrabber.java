package grabber;


import api.Genius;
import api.Song;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;


public class DataGrabber {

    public static List<String> songNames;
    public static List<String> artists;

    public static void main(String[] args) throws IOException {
        System.err.close();
        System.setErr(System.out);

        if(Genius.accessToken.length() > 0){
            System.out.println("Welcome to genius-datagrab!");
            System.out.println("You can begin by entering a command, type 'help' for more information.");
            waitForCommand();
        } else {
            System.out.println("A Genius authorization token is required to use the data grabber. More information is provided in the README.");
            System.out.println("If you have a token you can enter it below.");
            Scanner reader = new Scanner(System.in);
            String token = reader.nextLine().toLowerCase();
            Commands.setToken(token);
        }

    }

    public static void waitForCommand() throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.print("gdg> ");
        String command = reader.nextLine().toLowerCase();

        if (command.startsWith("songs ")) {
            String artist = StringUtils.substringAfter(command,"songs ");
            Commands.getSongsForArtist(artist);
            waitForCommand();
        }

        else if (command.startsWith("help")) {
            Commands.sendHelp();
            waitForCommand();


        }

        else if(command.startsWith("artist ")){
            String artist = StringUtils.substringAfter(command,"artist ");
            Commands.getDataforArtist(artist);
            waitForCommand();
        }

        else if(command.startsWith("lyrics " )){
            String song = StringUtils.substringAfter(command,"lyrics ");
            Genius.grabLyrics(song);
            waitForCommand();
        }

    }

    static {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
    }


}
