package dudzinski.musiconator;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class SetGenreActivity extends AppCompatActivity {
    ArrayList<String> genresList = new ArrayList<>();
    ArrayList<String> pathList = new ArrayList<>();
    Dialog myDialog;
    String pickedSong;
    String filePath;
    ZarzadcaBazy dbHandler = new ZarzadcaBazy(this);
    PbHandler pb = new PbHandler();
    Boolean isPaused = false;


    private static final int MY_PERMISSION_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_genre);
        myDialog = new Dialog(this);

        if (ContextCompat.checkSelfPermission(SetGenreActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(SetGenreActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(SetGenreActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
            else{
                ActivityCompat.requestPermissions(SetGenreActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        }
        else{
            doStuff();
        }
    }

    public void AssignParams(View v, final String filePath, final String pickedSong){
        myDialog.setContentView(R.layout.popup_assign);
        TextView SongInfo = myDialog.findViewById(R.id.SongWrapper);
        Button btnAssign = myDialog.findViewById(R.id.AssignBtn);

        SongInfo.setText("Wybrano utwór " + pickedSong);

        final Spinner spinner = (Spinner) myDialog.findViewById(R.id.SetMood);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.moods_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner timeSpinner = (Spinner) myDialog.findViewById(R.id.SetTime);
        ArrayAdapter <CharSequence> timeadapter = ArrayAdapter.createFromResource
                (this, R.array.time_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeadapter);

        final Spinner activitySpinner = (Spinner) myDialog.findViewById(R.id.SetActivity);
        ArrayAdapter <CharSequence> actAdapter = ArrayAdapter.createFromResource
                (this, R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(actAdapter);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();


        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actv = activitySpinner.getSelectedItem().toString();
                String time = timeSpinner.getSelectedItem().toString();
                String mood = spinner.getSelectedItem().toString();
                Song song = new Song(pickedSong, filePath, mood, time, actv);
                dbHandler.addSong(song);
                Snackbar snackbar = Snackbar
                        .make(v, "Pomyślnie zapisano", Snackbar.LENGTH_LONG);
                snackbar.show();
                myDialog.dismiss();
            }
        });

    }

    public void doStuff(){
        ListenButtons();

        ListView genresListWrapper = findViewById(R.id.SetGenresContainer);
        Button btn = findViewById(R.id.btnAssgin);
        getSongs();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                genresList);
        genresListWrapper.setAdapter(arrayAdapter);

        genresListWrapper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pickedSong = genresList.get(position);
                filePath = pathList.get(position);
            }
            });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pickedSong != null){
                    AssignParams(v, pickedSong, filePath);
                }
                else{
                    Snackbar snackbar = Snackbar
                            .make(v, "Nie wybrano parametrów", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

    }


     ArrayList<String> getSongs(){
        ArrayList<HashMap<String,String>> songList = SongsList("/sdcard/Music/");
         Log.d("d", valueOf(songList));
         for (int i = 0; i < songList.size();i++){
            genresList.add(valueOf(songList.get(i).get("file_name")));
            pathList.add(valueOf(songList.get(i).get("file_path")));
        }
        Log.d("t", valueOf(genresList));

        return genresList;
    }

    ArrayList<HashMap<String,String>> SongsList (String rootPath){
        ArrayList<HashMap<String,String>> fileList = new ArrayList<>();
        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (file.isDirectory()) {
                    if (SongsList(file.getAbsolutePath()) != null) {
                        fileList.addAll(SongsList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    HashMap<String, String> song = new HashMap<>();
                    song.put("file_path", file.getAbsolutePath());
                    song.put("file_name", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(SetGenreActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"PermissionGranted", Toast.LENGTH_SHORT).show();
                        doStuff();
                    }
                    else{
                        Toast.makeText(this, "Nopermission", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                }
            }

        }
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



