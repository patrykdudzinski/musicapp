package dudzinski.musiconator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void WelcomeScreenButton(View view) {
        switch (view.getId()) {
            case R.id.LogInButton:
                Intent intentLogin = new Intent(MainActivity.this, MainProfile.class);
                startActivity(intentLogin);
                break;
            case R.id.RegisterButton:
                Intent intentRegister = new Intent(MainActivity.this, RegisterView.class);
                startActivity(intentRegister);
                break;
        }
    }


}