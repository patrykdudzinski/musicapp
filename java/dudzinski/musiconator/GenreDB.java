package dudzinski.musiconator;

public class GenreDB {
        // fields
        private int genreID;
        private String genre;
        private String mood;
        private String time;
        private String activity;

    // constructors
        public GenreDB () {}
        public GenreDB (int id, String genre, String mood, String time, String activity) {
            this.genreID = id;
            this.genre = genre;
            this.mood = mood;
            this.time = time;
            this.activity = activity;
        }
        // properties
        public Integer getGenreID() {return this.genreID; }
        public String getGenreName() {
            return this.genre;
        }
        public String getGenreMood() {
        return this.mood;
    }
        public String getGenreTime() { return this.time; }
        public String getGenreActivity() { return this.activity; }

        public void setGenMood(String mood) {
        this.mood = mood;
    }
        public void setGenTime(String time) {
        this.time = time;
    }
        public void setGenActivity(String activity) {
        this.activity = activity;
    }
    
}
