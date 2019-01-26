package dudzinski.musiconator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.List;

public class Stats extends AppCompatActivity {
    ListView HistoryList;
    ZarzadcaBazy db = new ZarzadcaBazy(this);
    List<String> UserStoryTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        HistoryList = findViewById(R.id.StatContainer);
        for(UserStory u:db.giveAllStory()){
            String storyName = u.getName();
            UserStoryTable.add(storyName);
        }
        ArrayAdapter<String> StoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, UserStoryTable);

        HistoryList.setAdapter(StoryAdapter);
    }
}
