package com.virtualstudios.medistore.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.virtualstudios.medistore.R;


public class AddMedicineFragment extends Fragment {

    private View rootView;
    private Spinner spinnerCategory, spinnerIsAthical;
    private String[] categoryOptions= {"Allopathic", "Homeopathic", "Ayurvedic"};
    private String[] isAthicalOptions= {"Yes", "No"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_add_medicine, container, false);
            initView();
        }
        return rootView;
    }

    private void initView(){
        rootView.findViewById(R.id.imageBack).setOnClickListener(view -> requireActivity().onBackPressed());
        spinnerCategory = rootView.findViewById(R.id.spinnerCategory);
        spinnerIsAthical = rootView.findViewById(R.id.spinnerIsAthical);
        spinnerCategory.setAdapter(new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, categoryOptions));
        spinnerIsAthical.setAdapter(new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, isAthicalOptions));
    }
}