package com.virtualstudios.medistore.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.virtualstudios.medistore.R;

public class AddReminderActivity extends AppCompatActivity {

    private AppCompatSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        initViews();
    }

    private void initViews(){
        spinner = findViewById(R.id.spinner);

        initDropDownOption();
    }

    private void initDropDownOption(){
        String[] options = new String[]{"One Day Before", "One Week Before", "One Month Before", "Two Month Before", "Three Month Before"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
    }
}