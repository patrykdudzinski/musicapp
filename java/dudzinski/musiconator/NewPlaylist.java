package dudzinski.musiconator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.opengl.Matrix.length;

public class NewPlaylist extends AppCompatActivity {
    Button CreateListButton;
    Button SaveListButton;
    Button PlaylistStartButton;
    Button DeleteButton;
    ZarzadcaBazy dbHandler = new ZarzadcaBazy(this);
    ArrayList<String> SongsChosen = new ArrayList<String >();
    ListView ListWrapper;
    PbHandler pb = new PbHandler();
    Boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_playlist);

        final Spinner moodSpinner = (Spinner) findViewById(R.id.SetMoodSpinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.moods_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moodSpinner.setAdapter(adapter);

        final Spinner timeSpinner = (Spinner) findViewById(R.id.SetTimeSpinner);
        ArrayAdapter <CharSequence> timeadapter = ArrayAdapter.createFromResource
                (this, R.array.time_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeadapter);

        final Spinner activitySpinner = (Spinner) findViewById(R.id.SetActivitySpinner);
        ArrayAdapter <CharSequence> actAdapter = ArrayAdapter.createFromResource
                (this, R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(actAdapter);

        CreateListButton = findViewById(R.id.PlaylistGeneratorButton);

        for (Song s: dbHandler.giveAllSongs()){
            Log.d("D", "wykonuuuje sie");
            Log.d("Dane", s.getName()+" "+ s.getPath() +" "+s.getMood()+" "+s.getActv()+" "+ s.getTime());
        }

        CreateListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actv = moodSpinner.getSelectedItem().toString();
                String time = timeSpinner.getSelectedItem().toString();
                String mood = activitySpinner.getSelectedItem().toString();
                GenerateList(mood, time, actv);
            }
        });

        ListenButtons();

    }

    public void GenerateList(String mood, String time, String actv){
        Log.d("param", mood + time + actv);
        String sChosen;
        for(Song s: dbHandler.giveSongsByParams(mood, time, actv)){
            sChosen = s.getName();
            SongsChosen.add(sChosen);
        }

        ListWrapper = findViewById(R.id.PlaylistWrapper);
        SaveListButton = findViewById(R.id.SavePlaylistButton);
        PlaylistStartButton = findViewById(R.id.PlayButton);
        DeleteButton = findViewById(R.id.DeletePlaylistButton);


        ArrayAdapter<String> PlaylistAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, SongsChosen);
        ListWrapper.setAdapter(PlaylistAdapter);

        ListWrapper.setVisibility(View.VISIBLE);
        //SaveListButton.setVisibility(View.VISIBLE);
        //PlaylistStartButton.setVisibility(View.VISIBLE);
        //DeleteButton.setVisibility(View.VISIBLE);
        final Button Playbtn = findViewById(R.id.ToolbarPlay);

        ListWrapper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isPaused != true){
                    try {
                        String chosen = SongsChosen.get(position);
                        Playbtn.setBackgroundResource(R.drawable.mock5);
                        pb.playMusic(chosen);
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

}
