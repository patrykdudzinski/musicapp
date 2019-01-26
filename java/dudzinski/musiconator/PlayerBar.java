package dudzinski.musiconator;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import java.io.IOException;

public class PlayerBar extends AppCompatActivity {
    MediaPlayer mp;
    String songName;
    Button PlayBtn;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_bar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.PlayerToolbar);
        setSupportActionBar(myToolbar);
        PlayBtn = findViewById(R.id.ToolbarPlay);
        }

    public void playMusic(){

    }


}
