package com.github.maksimkirko.bottom_app_bar_demo;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.WindowManager;

public class Utils {

    @NonNull
    public static Point getScreenSize(@NonNull Context context) {
        WindowManager windowManager = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        Point size = new Point();
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            display.getSize(size);
        }
        return size;
    }
}
