package dudzinski.musiconator;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.IOException;


public class PbHandler extends AppCompatActivity {
    MediaPlayer mp = new MediaPlayer();
    Integer currPos = null;
    ZarzadcaBazy db = new ZarzadcaBazy(this);
    String SongName;
    UserStory story;

    public void playMusic(String filePath) throws IOException {
        Log.d("kom", "odpalam się" + String.valueOf(currPos));
        if (currPos != null){
            playFromPos(currPos);
        }
        else{
            mp.setDataSource(filePath);
            mp.prepare();
            mp.start();
            Log.d("kom", filePath);
            //story = new UserStory(filePath, filePath);
            //db.addStory(story);
            currPos = null;
        }
    }

    public void playFromPos(Integer currPos){
        Log.d("qp", "Odtwarzam z qurrpos");
        mp.seekTo(currPos);
        mp.start();
    }

    public void stopMusic(){
        if (mp.isPlaying()){
            currPos = mp.getCurrentPosition();
            mp.pause();
            Log.d("CurrPs", String.valueOf(currPos));
        }
    }
}
