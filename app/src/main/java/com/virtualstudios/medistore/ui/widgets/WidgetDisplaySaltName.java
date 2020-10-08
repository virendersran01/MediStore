package com.virtualstudios.medistore.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.virtualstudios.medistore.R;

public class WidgetDisplaySaltName extends ConstraintLayout {

    private TextView textSaltName;
    private TextView textPotency;
    private TextView textPotencyType;

    public WidgetDisplaySaltName(@NonNull Context context) {
        super(context);
        init();
    }

    public WidgetDisplaySaltName(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WidgetDisplaySaltName(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_display_salt_name_widget, this,true);

        textSaltName = findViewById(R.id.textSaltName);
        textPotency = findViewById(R.id.textPotency);
        textPotencyType = findViewById(R.id.textPotencyType);
    }

    public void setSaltName(String saltName){
        textSaltName.setText(saltName);
    }

    public void setPotency(String potency){
        textPotency.setText(potency);
    }

    public void setPotencyType(String potencyType){
        textPotencyType.setText(potencyType);
    }
}
