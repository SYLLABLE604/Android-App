package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button aaa;
    private EditText ID;
    private EditText password;
    String identicalnumber, passing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aaa =findViewById(R.id.btn_MainMenu);
        ID=findViewById(R.id.space1);
        password = findViewById(R.id.space2);
        aaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identicalnumber = ID.getText().toString();
                passing = password.getText().toString();
                if(identicalnumber.length() == 0  ){
                    Toast.makeText(getApplicationContext(),"please input id number",Toast.LENGTH_SHORT).show();
                }else if(passing.length() ==0){
                    Toast.makeText(getApplicationContext(),"please input password",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"login successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(intent);
                }
            }
        });
    }
}