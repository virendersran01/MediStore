package com.virtualstudios.medistore.ui.customviews;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.virtualstudios.medistore.R;

import java.util.Objects;

public class AddSaltView extends ConstraintLayout {

    private TextInputLayout inputName;
    private TextInputLayout inputPotency;
    private Spinner spinnerPotencyType;
    private String[] potencyOptions = {"mg", "gm", "mcg"};
    private String potencyTypeValue = null;

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
        setBackgroundColor(Color.parseColor("#f3f3f5"));
        inputName = findViewById(R.id.inputSaltName);
        inputPotency = findViewById(R.id.inputPotency);
        spinnerPotencyType = findViewById(R.id.spinnerPotencyType);
        spinnerPotencyType.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, potencyOptions));

        spinnerPotencyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                potencyTypeValue = potencyOptions[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private String getName(){
        return Objects.requireNonNull(inputName.getEditText()).getText().toString();
    }

    private String getPotency(){
        return Objects.requireNonNull(inputPotency.getEditText()).getText().toString();
    }

    public String getPotencyTypeValue(){
        if(potencyTypeValue != null){
            return potencyTypeValue;
        }
        return null;
    }

    private boolean validSaltName(){
        if (getName().isEmpty()){
            Objects.requireNonNull(inputName.getEditText()).setError(getContext().getString(R.string.error_username_empty));
            return false;
        }else {
            Objects.requireNonNull(inputName.getEditText()).setError(null);
            return true;
        }
    }

    private boolean validProtencyValue(){
        if (getPotency().isEmpty()){
            Objects.requireNonNull(inputPotency.getEditText()).setError(getContext().getString(R.string.error_username_empty));
            return false;
        }else {
            Objects.requireNonNull(inputPotency.getEditText()).setError(null);
            return true;
        }
    }

    public String getSaltName(){
        if (validSaltName()){
            return getName();
        }
        return null;
    }

    public String getProtencyValue(){
        if (validProtencyValue()){
            return getPotency();
        }
        return null;
    }

}
