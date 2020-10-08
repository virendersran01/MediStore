package com.virtualstudios.medistore.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.utils.Utils;

import java.util.Random;

public class UserAvatarView extends AppCompatImageView {
    private Context context;

    /*
     * Constants to define shape
     * */
    protected static final int CIRCLE = 0;
    protected static final int RECTANGLE = 1;
    /*
     * Path of them image to be clipped (to be shown)
     * */
    private Path clipPath;
    /*
     * Place holder drawable (with background color and initials)
     * */
    private Drawable drawable;
    /*
     * Contains initials of the member
     * */
    private String text;
    /*
     * Used to set size and color of the member initials
     * */
    private TextPaint textPaint;
    /*
     * Used as background of the initials with user specific color
     * */
    private Paint paint;
    /*
     * Shape to be drawn
     * */
    private int shape;
    /*
     * We will set it as 2dp
     * */
    int cornerRadius;
    /*
     * Bounds of the canvas in float
     * Used to set bounds of member initial and background
     * */
    RectF rectF;
    /*
     * To draw border
     */
    private Paint borderPaint;
    /*
     * Image width and height (both are same and constant, defined in dimens.xml
     * We cache them in this field
     * */
    private int imageSize;
    private float borderWidth;

    private String imageUrl;
    private String backColor;
    Random rnd = new Random();
    private int backgroundColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));



    public UserAvatarView(Context context) {
        super(context);
        this.context = context;
    }

    public UserAvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getAttributes(attrs);
        init();
    }

    public UserAvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        getAttributes(attrs);
        init();
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
        paint.setColor(backgroundColor);
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setFakeBoldText(true);
        textPaint.setTextSize(24f * getResources().getDisplayMetrics().scaledDensity);
        textPaint.setColor(Color.WHITE);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        borderPaint.setColor(ContextCompat.getColor(context, R.color.border_color));
        borderPaint.setStrokeWidth(context.getResources().getDimension(R.dimen.border_width));

        borderWidth = context.getResources().getDimension(R.dimen.border_width);

    }

    public void setUser(String name, String backgroundColor, String imageUrl){
        if (name != null) {
            text = Utils.getShortName(name);
        }
        if (backgroundColor != null){
            backColor = backgroundColor;
        }
        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }
        setValues();
        invalidate();
    }

    private void setValues() {
        if (backColor != null) {
            paint.setColor(Color.parseColor(backColor));
        }else {
            paint.setColor(backgroundColor);
        }

        /*
         * Initials of member
         * */

        setDrawable();

        if (imageUrl != null) {
            Glide.with(getContext())
                    .load(imageUrl)
                    .placeholder(drawable)
                    .centerCrop()
                    .override(imageSize, imageSize)
                    .into(this);
        } else {
            setImageDrawable(drawable);
            invalidate();
        }
    }

    /*
     * Create placeholder drawable
     * */
    private void setDrawable() {
        drawable = new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {

                int centerX = Math.round(getBounds().width() * 0.5f);
                int centerY = Math.round(getBounds().height() * 0.5f);

                /*
                 * To draw text
                 * */
                if (text != null) {
                    float textWidth = textPaint.measureText(text) * 0.5f;
                    float textBaseLineHeight = textPaint.getFontMetrics().ascent * -0.4f;

                    /*
                     * Draw the background color before drawing initials text
                     * */
                    if (shape == RECTANGLE) {
                        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
                    } else {
                        canvas.drawCircle(centerX,
                                centerY,
                                Math.max((getBounds().height() * 0.5f), textWidth / 2),
                                paint);
                    }

                    /*
                     * Draw the text above the background color
                     * */
                    canvas.drawText(text, centerX - textWidth, centerY + textBaseLineHeight, textPaint);
                }
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        };
    }

    /*
     * Set the canvas bounds here
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int screenWidth = MeasureSpec.getSize(widthMeasureSpec);
        int screenHeight = MeasureSpec.getSize(heightMeasureSpec);
        rectF.set(0, 0, screenWidth, screenHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (shape == RECTANGLE) {
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
            clipPath.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CW);
        } else {
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), (rectF.height() / 2) - borderWidth, paint);

            clipPath.addCircle(rectF.centerX(), rectF.centerY(), (rectF.height() / 2), Path.Direction.CW);
        }
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}
