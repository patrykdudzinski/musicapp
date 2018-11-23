package dudzinski.musiconator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_view);

        final Button button = findViewById(R.id.RegisterApprove);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText pass = findViewById(R.id.AccountPassword);
                String password = pass.getText().toString();
                EditText passcheckText = findViewById(R.id.PassCheck);
                String passcheck = passcheckText.getText().toString();
                TextView RegisterPasswordAlert = (TextView) findViewById(R.id.RegisterHeaderAlert);
                Boolean passChecker = password.equals(passcheck);
                if (!passChecker)
                {
                    RegisterPasswordAlert.setVisibility(View.VISIBLE);

                }
                else
                    {
                    Intent intent = new Intent(RegisterView.this, MainProfile.class);
                    startActivity(intent);
                    }
            }


        });



    }


}

