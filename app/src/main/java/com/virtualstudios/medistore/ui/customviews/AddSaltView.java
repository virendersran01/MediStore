package com.virtualstudios.medistore.ui.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.virtualstudios.medistore.R;

public class AddSaltView extends ConstraintLayout {

    private TextInputLayout inputName;
    private TextInputLayout inputPotency;
    private Spinner spinnerPotencyType;
    private String[] potencyOptions = {"mg", "gm", "mcg"};

    public AddSaltView(Context context) {
        super(context);
        init();
    }

    public AddSaltView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AddSaltView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_add_salt, this,true);
        inputName = findViewById(R.id.inputSaltName);
        inputPotency = findViewById(R.id.inputPotency);
        spinnerPotencyType = findViewById(R.id.spinnerPotencyType);
        spinnerPotencyType.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, potencyOptions));
    }
}
