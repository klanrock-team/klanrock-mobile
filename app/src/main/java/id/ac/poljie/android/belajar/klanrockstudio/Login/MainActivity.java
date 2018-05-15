package id.ac.poljie.android.belajar.klanrockstudio.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.ac.poljie.android.belajar.klanrockstudio.BottomBar.SecondActivity;
import id.ac.poljie.android.belajar.klanrockstudio.NavDrawer.Drawer;
import id.ac.poljie.android.belajar.klanrockstudio.R;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.text_username);
        Password = (EditText)findViewById(R.id.text_password);
//        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.button);

//        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void validate(String userName, String userPassword){
        if((userName.equals("User")) && (userPassword.equals("user"))){
            Intent intent = new Intent(MainActivity.this, Drawer.class);
            startActivity(intent);
        }else{
            counter--;
//
//            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if(counter == 0){
                Login.setEnabled(false);
            }
        }
    }

}
