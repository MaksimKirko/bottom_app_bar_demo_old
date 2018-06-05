package com.github.maksimkirko.bottom_app_bar_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomToolbar customToolbar = findViewById(R.id.toolbar_activity_main);

        Button animateButton = findViewById(R.id.button_activity_main_animate);
        animateButton.setOnClickListener(v -> {
            customToolbar.toggleView();
        });
    }
}
