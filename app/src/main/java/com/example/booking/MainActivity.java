package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {


        private static final long SPLASH_DISPLAY_LENGTH = 3000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            setTheme(R.style.activity_main);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(MainActivity.this,
                            LstPelicula2Activity.class);
                    startActivity(mainIntent);
                    MainActivity.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }

