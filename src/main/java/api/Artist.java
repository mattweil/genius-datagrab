package api;

public class Artist {
    String name;
    String aliases;
    String geniusID;
    int primarySongs;
    int secondarySongs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getGeniusID() {
        return geniusID;
    }

    public void setGeniusID(String geniusID) {
        this.geniusID = geniusID;
    }

    public int getPrimarySongs() {
        return primarySongs;
    }

    public void setPrimarySongs(int primarySongs) {
        this.primarySongs = primarySongs;
    }

    public int getSecondarySongs() {
        return secondarySongs;
    }

    public void setSecondarySongs(int secondarySongs) {
        this.secondarySongs = secondarySongs;
    }
}
