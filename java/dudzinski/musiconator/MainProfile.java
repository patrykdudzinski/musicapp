package dudzinski.musiconator;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainProfile extends AppCompatActivity {
    PbHandler pb = new PbHandler();
    Boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        ListenButtons();
    }

    public void ListenButtons(){
        final Button Playbtn = findViewById(R.id.ToolbarPlay);
        Playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaused != true){
                    try {
                        pb.playMusic("/sdcard/Music/song2.mp3");
                        Playbtn.setBackgroundResource(R.drawable.mock5);
                        isPaused = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    pb.stopMusic();
                    Playbtn.setBackgroundResource(R.drawable.mock2);
                    isPaused = false;
                }
            }
        });

    }


    public void MainScreenButtons (View view){
        switch(view.getId()){
            case R.id.NewPlaylistButton:
                Intent PlaylistGeneratorIntent = new Intent(MainProfile.this, NewPlaylist.class);
                startActivity(PlaylistGeneratorIntent);
                break;

            case R.id.SavedButton:
                Intent SavedIntent = new Intent(MainProfile.this, SavedPlaylists.class);
                startActivity(SavedIntent);
                break;

            case R.id.StatButton:
                Intent StatIntent = new Intent (MainProfile.this, Stats.class);
                startActivity(StatIntent);
                break;

            case R.id.ProfileButton:
                Intent ProfileIntent = new Intent(MainProfile.this, SetGenreActivity.class);
                startActivity(ProfileIntent);
                break;

        }
    }


    }


