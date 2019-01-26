package dudzinski.musiconator;

public class Song {
    private int id;
    private String name;
    private String path;
    private String mood;
    private String actv;
    private String time;

    public Song(){};

    public Song(String songName, String songPath, String songMood, String songActv, String songTime) {
        this.name = songName;
        this.path = songPath;
        this.mood = songMood;
        this.actv = songActv;
        this.time = songTime;
    }

    public long getID(){return id;}
    public String getName(){return name;}
    public String getMood(){return mood;}
    public String getPath(){return path;}
    public String getTime(){return time;}
    public String getActv(){return actv;}

    public void setID(int songID) { this.id = songID; }
    public void setName(String songName) {
        this.name = songName;
    }
    public void setPath(String songPath) {
        this.path  = songPath;
    }
    public void setMood(String songMood){
        this.mood = songMood;
    }
    public void setActv(String songActv) {
        this.actv = songActv;
    }
    public void setTime(String songTime) {
        this.time = songTime;
    }

}
