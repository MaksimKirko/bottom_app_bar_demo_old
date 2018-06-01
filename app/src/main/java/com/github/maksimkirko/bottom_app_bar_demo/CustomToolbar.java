package com.github.maksimkirko.bottom_app_bar_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Toolbar;

public class CustomToolbar extends Toolbar {

    private int inflateCounter = 0;

    public CustomToolbar(Context context) {
        super(context);
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = (float) getWidth();
        float height = (float) getHeight();

        float cropSize = getResources().getDimensionPixelSize(R.dimen.radius_activity_main_toolbar_crop);
        float cropStart = (width / 2) - (cropSize / 2);
        float cropEnd = (width / 2) + (cropSize / 2);

        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(cropStart, 0);
        if (inflateCounter < 2 || inflateCounter % 2 == 0) {
            path.addCircle(width / 2, 0, cropSize, Path.Direction.CCW);
        }

        path.moveTo(cropEnd, 0);
        path.lineTo(width, 0);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.lineTo(0, 0);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        canvas.drawPath(path, paint);
        path.reset();
        path.close();

        inflateCounter++;
    }
}
