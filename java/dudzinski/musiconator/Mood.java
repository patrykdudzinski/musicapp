package dudzinski.musiconator;

public class Mood {
    // fields
    private int moodID;
    private String mood;
    // constructors
    public Mood() {}
    public Mood(int id, String mood) {
        this.moodID = id;
        this.mood = mood;
    }
    // properties
    public void setMoodID(int id) {
        this.moodID = id;
    }
    public int getMoodID() {
        return this.moodID;
    }

    public String getMood() {
        return this.mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }

}
