package dudzinski.musiconator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NewPlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_playlist);

        Spinner spinner = (Spinner) findViewById(R.id.SetMoodSpinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.moods_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner timeSpinner = (Spinner) findViewById(R.id.SetTimeSpinner);
        ArrayAdapter <CharSequence> timeadapter = ArrayAdapter.createFromResource
                (this, R.array.time_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeadapter);

        Spinner activitySpinner = (Spinner) findViewById(R.id.SetActivitySpinner);
        ArrayAdapter <CharSequence> actAdapter = ArrayAdapter.createFromResource
                (this, R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(actAdapter);
    }


}
