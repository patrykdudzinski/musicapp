package dudzinski.musiconator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    ZarzadcaBazy dbHandler = new ZarzadcaBazy(this);
    ArrayList<String> UserInfoArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String UserEmail;
        ListView UserWrapper = findViewById(R.id.ProfileInfoWrapper);
        for(User u: dbHandler.getUser("pat@pat.pl")){
            Log.d("Komunikat", String.valueOf(u.getEmail()));
            UserEmail = u.getEmail();
            UserInfoArray.add(UserEmail);
        }
        Log.d("UserInfo", String.valueOf(UserInfoArray));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, UserInfoArray);
        UserWrapper.setAdapter(arrayAdapter);
    }

    public void ProfileButtons (View view) {
        switch (view.getId()) {
            case R.id.MatchGenres:
                Intent setGenre = new Intent(Profile.this, SetGenreActivity.class);
                startActivity(setGenre);
                break;
        }
    }
}
