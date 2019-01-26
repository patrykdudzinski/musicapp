package dudzinski.musiconator;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class MoodDB {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersDB.db";
    public static final String TABLE_NAME = "MoodTable";
    public static final String COLUMN_ID = "MoodID";
    public static final String COLUMN_NAME = "Mood";

    //initialize the database

    public MoodDB(Context context, String Mood, SQLiteDatabase.CursorFactory factory, int version) {
        Mood = null;
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + "INTEGER PRIMARYKEY," + COLUMN_NAME + "TEXT )";
        db.execSQL(CREATE_TABLE);}

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public User loadHandler(String Mood) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + Mood +"'";
        SQLiteDatabase db = null;
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setEmail(cursor.getString(1));
            cursor.close();
        }
        else {
            user = null;
        }
        db.close();
        return user;
    }

    public void addHandler(Mood mood) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, mood.getMoodID());
        values.put(COLUMN_NAME, mood.getMood());
        SQLiteDatabase db = null;
        db.insert(TABLE_NAME, null, values);
        db.close();}

}
