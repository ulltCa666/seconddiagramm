package com.example.seconddiagramm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String EVENT_DATE_TIME = "2020-12-31 10:30:00";
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    TextView textviewNameOfTeam , textviewQuestTime, nameofteam;
    TextView tv_days, tv_hour, tv_minute, tv_second;
    Handler handler = new Handler();
    Runnable runnable;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        countDownStart();
    }

    private void initUI() {
        textviewNameOfTeam = (TextView)findViewById(R.id.textView_team);
        textviewQuestTime = (TextView)findViewById(R.id.textView_questtime);
        nameofteam = (TextView)findViewById(R.id.nameofteam);
        tv_days = (TextView)findViewById(R.id.tv_days);
        tv_hour = (TextView)findViewById(R.id.tv_hour);
        tv_minute = (TextView)findViewById(R.id.tv_minute);
        tv_second = (TextView)findViewById(R.id.tv_second);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void countDownStart(){
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    Date evet_date  = dateFormat.parse(EVENT_DATE_TIME);
                    Date current_date = new Date();
                    if (!current_date.after(evet_date)) {
                        long diff = evet_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;

                        tv_days.setText(String.format("%02d", Days));
                        tv_hour.setText(String.format("%02d", Hours));
                        tv_minute.setText(String.format("%02d", Minutes));
                        tv_second.setText(String.format("%02d", Seconds));
                    } else {
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }
    protected  void onStop(){
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}