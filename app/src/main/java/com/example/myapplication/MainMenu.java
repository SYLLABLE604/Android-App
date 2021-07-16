package com.example.myapplication;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenu extends AppCompatActivity {
    private Button bbb;
    private SeekBar seekBar;
    private TextView tv;
    private View temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        bbb = findViewById(R.id.button2);
        bbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, chart1.class);
                startActivity(intent);
                init();
            }
        });
        temp = findViewById(R.id.temperature);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, TempActivity.class);
                startActivity(intent);
                init();
            }
        });
    }
    private void init() {
        seekBar = findViewById(R.id.seekBar);
        tv = findViewById(R.id.seekBar_tv);
        //添加事件监听
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 当进度条改变时，改变文本框的显示内容
                tv.setText("value："+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainMenu.this,"start sliding",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainMenu.this,"finish sliding",Toast.LENGTH_SHORT).show();
            }
        });
    }

}