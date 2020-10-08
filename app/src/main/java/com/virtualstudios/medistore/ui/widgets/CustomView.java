package com.virtualstudios.medistore.ui.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    RectF rectF;
    private Paint mPaint;
    private TextPaint textPaint;
    private String text;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        rectF = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setFakeBoldText(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(20f * getResources().getDisplayMetrics().scaledDensity);

        text = "VSS";




    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        int screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        rectF.set(0, 0, screenWidth, screenHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("TAG", "onDraw: Height : "+getHeight() + " Width : "+getWidth());
        float centerX = getWidth()/2f;
        float centerY = getHeight()/2f;
        Log.d("TAG", "onDraw: Center X : "+centerX + " Center Y : "+centerY);

        float textWidth = textPaint.measureText(text) * 0.5f;
        Log.d("TAG", "onDraw: TextWidth Real: "+textPaint.measureText(text));
        Log.d("TAG", "onDraw: TextWidth : "+textWidth);
        float textBaseLineHeight = textPaint.getFontMetrics().ascent * -0.4f;
        Log.d("TAG", "onDraw: TextHeight Real: "+textPaint.getFontMetrics().ascent);
        Log.d("TAG", "onDraw: TextHeight : "+textBaseLineHeight);

        canvas.drawCircle(rectF.centerX(),rectF.centerY(), (rectF.height() / 2), mPaint);
        canvas.drawText(text, rectF.centerX()-textWidth, rectF.centerY()+textBaseLineHeight, textPaint);
    }
}
