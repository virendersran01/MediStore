package com.virtualstudios.medistore.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.virtualstudios.medistore.R;


public class AddReminderFragment extends Fragment {

    private View rootView;
    private ImageView imageAddReminderDate;
    private TextView textReminderDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_reminder, container, false);
        initViews();
        return rootView;
    }

    private void initViews(){
        imageAddReminderDate = rootView.findViewById(R.id.imageAddDate);
        textReminderDate = rootView.findViewById(R.id.textReminderDate);

        imageAddReminderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Reminder Date");
                MaterialDatePicker<Long> picker = builder.build();
                picker.show(getChildFragmentManager(), picker.toString());

                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        textReminderDate.setText(picker.getHeaderText());
                    }
                });
            }
        });


    }
}