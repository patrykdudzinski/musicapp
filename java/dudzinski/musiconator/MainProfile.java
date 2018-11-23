package dudzinski.musiconator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

    }

    public void MainScreenButtons (View view){
        switch(view.getId()){

            case R.id.NewPlaylistButton:
                Intent NewPlaylistIntent = Intent(MainProfile.this, NewPlaylist.class);
                startActivity(NewPlaylistIntent);
                break;

            case R.id.SavedButton:
                Intent SavedIntent = Intent(MainProfile.this, SavedPlaylists.class);
                startActivity(SavedIntent);
                break;

            case R.id.StatButton:
                Intent StatIntent = Intent(MainProfile.this, Stats.class);
                startActivity(StatIntent);
                break;

            case R.id.ProfileButton:
                Intent ProfileIntent = Intent(MainProfile.this, Profile.class);
                startActivity(ProfileIntent);
                break;

        }
    }

}
