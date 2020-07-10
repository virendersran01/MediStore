package com.virtualstudios.medistore.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.virtualstudios.medistore.R;

public class AddReminderActivity extends AppCompatActivity {

    private AppCompatSpinner spinner;
    private ImageView imageAddReminderDate;
    private TextView textReminderDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        initViews();
    }

    private void initViews(){
        spinner = findViewById(R.id.spinner);
        imageAddReminderDate = findViewById(R.id.imageAddDate);
        textReminderDate = findViewById(R.id.textReminderDate);

        imageAddReminderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Reminder Date");
                MaterialDatePicker<Long> picker = builder.build();
                picker.show(getSupportFragmentManager(), picker.toString());

                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        textReminderDate.setText(picker.getHeaderText());
                    }
                });
            }
        });

        initDropDownOption();
    }

    private void initDropDownOption(){
        String[] options = new String[]{"One Day Before", "One Week Before", "One Month Before", "Two Month Before", "Three Month Before"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
    }
}