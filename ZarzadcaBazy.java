package dudzinski.musiconator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;

public class ZarzadcaBazy extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersDB.db";
    public static final String TABLE_NAME = "Users";
    public static final String COLUMN_ID = "userID";
    public static final String COLUMN_NAME = "UserEmail";
    public static final String COLUMN_NAME2 = "Password";

    //initialize the database


    public ZarzadcaBazy(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + "INTEGER PRIMARYKEY," + COLUMN_NAME + "TEXT," + COLUMN_NAME2 + "TEXT )";
        db.execSQL(CREATE_TABLE);}

        @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public User loadHandler(String useremail) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + useremail +"'";
        SQLiteDatabase db = this.getWritableDatabase();
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

    public void addHandler(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getID());
        values.put(COLUMN_NAME, user.getEmail());
        values.put(COLUMN_NAME2, user.getPassword());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();}

    //public User findHandler(String email) {}
    //public boolean deleteHandler(int ID) {}
    //public boolean updateHandler(int ID, String email, String password) {}
}