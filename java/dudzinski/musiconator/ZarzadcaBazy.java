package dudzinski.musiconator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class ZarzadcaBazy extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersDB.db";

    //initialize the database


    public ZarzadcaBazy(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table songs("+"songID integer primary key autoincrement, "
                + "songName text,"
                + "songpath text, "
                + "songMood text,"
                + "songActv text, "
                + "songTime text);"
                + "");

        db.execSQL("create table usersTable("+" userID integer primary key autoincrement," +
                "userEmail text, " +
                "userPassword text);"
        +"");

        db.execSQL("create table userStory("+"storyID integer primary key autoincrement, "
                + "songName text,"
                + "songpath text);"
                + "");

    }

        @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public void addHandler(User user) {
        ContentValues values = new ContentValues();
        values.put("userEmail", user.getEmail());
        values.put("userPassword", user.getPassword());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow("usersTable", null, values);
        db.close();
    }

    public void addSong(Song song){
        ContentValues values = new ContentValues();
        values.put("songName", song.getName());
        values.put("songPath", song.getPath());
        values.put("songMood", song.getMood());
        values.put("songActv", song.getActv());
        values.put("SongTime", song.getTime());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow("songs", null, values);
        db.close();
    }
    public void addStory(UserStory story){
        ContentValues values = new ContentValues();
        values.put("storyName", story.getName());
        values.put("songPath", story.getPath());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertOrThrow("userStory", null, values);
        db.close();
    }

    public List<User> getUser(String email){
        List<User> UserInfo = new LinkedList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.rawQuery("select userEmail from usersTable where userEmail = '"
                + email +
                "' order by userEmail asc", null);

        while(kursor.moveToNext()){
            User user = new User();
            user.setID(kursor.getInt(0));
            UserInfo.add(user);
        }
        Log.d("Users", String.valueOf(UserInfo));
        return UserInfo;
    }

    public List<Song> giveAllSongs(){
        List<Song> AllSongs = new LinkedList<>();
        String[] columns = {"songName", "songPath", "songMood", "songActv", "songTime"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.query("songs", columns, null, null, null, null, null );
        while(kursor.moveToNext()){
            Log.d("info", "wykonuje sie");
            Song song = new Song();
            song.setName(kursor.getString(0));
            song.setPath(kursor.getString(1));
            song.setMood(kursor.getString(2));
            song.setActv(kursor.getString(3));
            song.setTime(kursor.getString(4));
            AllSongs.add(song);
        }

        return AllSongs;
    }

    public List<UserStory> giveAllStory(){
        List<UserStory> UserAllStroy = new LinkedList<>();
        String[] columns = {"songName"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.query("userStory", columns, null, null, null, null, null );
        while(kursor.moveToNext()){
            UserStory story = new UserStory();
            story.setSong(kursor.getString(0));
            UserAllStroy.add(story);
        }

        return UserAllStroy;
    }

    public List<Song> giveSongsByParams(String mood, String time, String actv) {
        List<Song> SongList = new LinkedList<Song>();
        String[] columns = {"name", "path"};
        SQLiteDatabase db = getReadableDatabase();

        Cursor cMood = db.rawQuery("select songName, songPath from songs where songTime = '"
                + mood +
                "' order by songName asc", null);

        while (cMood.moveToNext()) {
            Song song = new Song();
            song.setName(cMood.getString(0));
            song.setPath(cMood.getString(1));
            SongList.add(song);
        }

        Cursor cTime = db.rawQuery("select songName, songPath from songs where songTime = '"
                + time +
                "' order by songName asc", null);

        while (cTime.moveToNext()) {
            Song song = new Song();
            song.setName(cTime.getString(0));
            song.setPath(cTime.getString(1));
            SongList.add(song);
        }

        Cursor cAct = db.rawQuery("select songName, songPath from songs where songActv = '"
                + actv +
                "' order by songName asc", null);

        while (cAct.moveToNext()) {
            Song song = new Song();
            song.setName(cAct.getString(0));
            song.setPath(cAct.getString(1));
            SongList.add(song);
        }

        return SongList;
    }

    public List<Song> giveSongName(String filePath) {
        List<Song> SongList = new LinkedList<Song>();
        String[] columns = {"name", "path"};
        SQLiteDatabase db = getReadableDatabase();

        Cursor cMood = db.rawQuery("select songName from songs where songPath = '"
                + filePath +
                "' order by songName asc", null);

        while (cMood.moveToNext()) {
            Song song = new Song();
            song.setName(cMood.getString(0));
            SongList.add(song);
        }
        return SongList;
    }

    //public User findHandler(String email) {}
    //public boolean deleteHandler(int ID) {}
}