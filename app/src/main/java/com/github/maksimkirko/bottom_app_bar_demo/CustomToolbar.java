package com.github.maksimkirko.bottom_app_bar_demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.support.transition.TransitionManager;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.Toolbar;

public class CustomToolbar extends Toolbar {

    private Handler mHandler = new Handler();
    private boolean isExpanded = true;
    private boolean isToggled = false;
    private int counter = 0;
    private int startOffset = 0;
    private int endOffset = 0;

    public boolean isExpanded() {
        return isExpanded;
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initOffsets(context, attrs, 0);
    }

    public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initOffsets(context, attrs, defStyleAttr);
    }

    public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initOffsets(context, attrs, defStyleAttr);
    }

    private void initOffsets(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar, defStyleAttr, 0);

        this.startOffset = a.getDimensionPixelSize(R.styleable.CustomToolbar_startOffset, 0);
        this.endOffset = a.getDimensionPixelSize(R.styleable.CustomToolbar_endOffset, 0);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = (float) getWidth();
        float height = (float) getHeight();

        float cropSize = getResources().getDimensionPixelSize(R.dimen.radius_activity_main_toolbar_crop);
        float cropStart = 0;
        float cropEnd = 0;

        if (startOffset == 0 && endOffset > 0) {
            cropEnd = width - endOffset;
            cropStart = cropEnd - cropSize;
        } else {
            cropStart = startOffset;
            cropEnd = cropStart + cropSize;
        }

        TransitionManager.beginDelayedTransition(this);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(cropStart, 0);

        if (isExpanded) {
            path.addCircle(cropStart, 0, cropSize - counter, Path.Direction.CCW);
        } else {
            path.addCircle(cropStart, 0, counter, Path.Direction.CCW);
        }

        path.moveTo(cropEnd, 0);
        path.lineTo(width, 0);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.lineTo(0, 0);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        canvas.drawPath(path, paint);
        path.reset();
        path.close();

        if (isToggled) {
            mHandler.post(() -> {
                if (counter <= cropSize) {
                    counter += 8;
                    invalidate();
                } else {
                    isExpanded = !isExpanded;
                    counter = 0;
                }
            });
        }
    }

    public void toggleView() {
        isToggled = true;
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isToggled = false;
    }
}
