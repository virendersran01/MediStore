package com.virtualstudios.medistore.ui.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.data.models.User;
import com.virtualstudios.medistore.utils.Utils;

public class UserAvatarView extends View {
    private Context context;

    protected static final int CIRCLE = 0;
    protected static final int RECTANGLE = 1;

    Path clipPath;
    Drawable drawable;
    String text;
    TextPaint textPaint;
    Paint paint;
    int shape;
    RectF rectF;
    String name;
    String backColor;



    public UserAvatarView(Context context) {
        super(context);
        this.context = context;
    }

    public UserAvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getAttributes(attrs);
    }

    public UserAvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        getAttributes(attrs);
    }


    private void getAttributes(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.UserAvatarView,
                0, 0);

        try {

            int avatarShape = a.getInt(R.styleable.UserAvatarView_shape, CIRCLE);
            if (avatarShape == CIRCLE) {
                shape = CIRCLE;
            } else {
                shape = RECTANGLE;
            }
        } finally {
            a.recycle();
        }
    }

    protected void init() {
        rectF = new RectF();
        clipPath = new Path();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setFakeBoldText(true);
        textPaint.setTextSize(24f * getResources().getDisplayMetrics().scaledDensity);
        textPaint.setColor(Color.WHITE);

    }

    public void setName(String name){
        this.name = name;
    }

    public void setBackColor(String backColor){
        this.backColor = backColor;
    }

}
