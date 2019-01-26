package dudzinski.musiconator;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class UserStory {
    private String name;
    private String path;

    // constructors
    public UserStory() {}
    public UserStory(String storySong, String storyPath) {
        this.name = storySong;
        this.path = storyPath;
    }

    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }

    // properties
    public void setSong (String storySong) {
        this.name = storySong;
    }
    public void setPath (String storyPath) {
        this.path = storyPath;
    }


}
