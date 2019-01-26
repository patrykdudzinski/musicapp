package dudzinski.musiconator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterView extends AppCompatActivity {
    ZarzadcaBazy dbHandler = new ZarzadcaBazy(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);
        }

    @SuppressLint("SetTextI18n")
    public void addUser (View view){
        EditText emailvar = findViewById(R.id.AccountEmail);
        final String mail = emailvar.getText().toString();
        EditText phonevar = findViewById(R.id.PhoneRegister);
        String phone = phonevar.getText().toString();
        EditText pass = findViewById(R.id.AccountPassword);
        final String password = pass.getText().toString();
        EditText passcheckText = findViewById(R.id.PassCheck);
        final String passcheck = passcheckText.getText().toString();
        final TextView RegisterPasswordAlert = findViewById(R.id.RegisterHeaderAlert);

        Boolean passChecker = password.equals(passcheck);
        if (!passChecker)
        {
            RegisterPasswordAlert.setText("Podane hasła nie są zgodne");
            RegisterPasswordAlert.setVisibility(View.VISIBLE);

        }
        else {
            int userID = 1;
            String email = mail;
            String password1 = password;
            User user = new User(email, password1);
            dbHandler.addHandler(user);
            Intent goToMenuIntent = new Intent(RegisterView.this, MainProfile.class);
            startActivity(goToMenuIntent);
        }

    }


}

