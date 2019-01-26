package dudzinski.musiconator;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.database.Cursor;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static String[] genresProjection = {
            MediaStore.Audio.Genres.NAME,
            MediaStore.Audio.Genres._ID
    };

    private static Cursor mediaCursor;
    private static Cursor genresCursor;

    private static String[] mediaProjection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.TITLE
    };


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