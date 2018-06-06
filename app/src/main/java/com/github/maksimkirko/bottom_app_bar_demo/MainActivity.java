package com.github.maksimkirko.bottom_app_bar_demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CustomToolbar customToolbar;
    private FloatingActionButton fab;
    private Button animateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        customToolbar = findViewById(R.id.toolbar_activity_main);
        fab = findViewById(R.id.fab);

        animateButton = findViewById(R.id.button_activity_main_animate);
        animateButton.setOnClickListener(v -> {
            float from = customToolbar.isExpanded() ? 1f : 0f;
            float to = customToolbar.isExpanded() ? 0f : 1f;

            Animation anim = new ScaleAnimation(
                    from, to,
                    from, to,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setInterpolator(new AccelerateInterpolator());
            anim.setFillAfter(true);

            int duration = getResources()
                    .getDimensionPixelSize(R.dimen.radius_activity_main_toolbar_crop) * 2;
            anim.setDuration(duration);
            fab.startAnimation(anim);

            customToolbar.toggleView();
        });
    }
}
