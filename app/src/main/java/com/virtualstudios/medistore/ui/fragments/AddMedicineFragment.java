package com.virtualstudios.medistore.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.ui.customviews.AddSaltView;


public class AddMedicineFragment extends Fragment {

    private View rootView;
    private Spinner spinnerCategory, spinnerIsAthical, spinnerType;
    private String[] categoryOptions= {"Allopathic", "Homeopathic", "Ayurvedic"};
    private String[] isAthicalOptions= {"Yes", "No"};
    private String[] typeOptions= {"Tablet", "Capsule", "Syrup"};
    private LinearLayout layoutAddSalts;
    private AddSaltView addSaltView;
    private ImageView imageAddSalt, imageRemoveSalt;
    private MaterialButton buttonAdd;

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
        spinnerType = rootView.findViewById(R.id.spinnerType);
        spinnerCategory.setAdapter(new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, categoryOptions));
        spinnerIsAthical.setAdapter(new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, isAthicalOptions));
        spinnerType.setAdapter(new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, typeOptions));
        layoutAddSalts = rootView.findViewById(R.id.layoutAddSalt);
        addSaltView = layoutAddSalts.findViewById(R.id.viewAddSalt);
        imageAddSalt = rootView.findViewById(R.id.imageAddSalt);
        imageRemoveSalt = rootView.findViewById(R.id.imageRemoveSalt);
        buttonAdd = rootView.findViewById(R.id.buttonAdd);

        if (layoutAddSalts.getChildCount() < 1){
            imageRemoveSalt.setVisibility(View.GONE);
        }


        imageAddSalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSaltView addSaltView1 = new AddSaltView(rootView.getContext());
                LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 10, 0,0);
                addSaltView1.setLayoutParams(layoutParams);
                layoutAddSalts.addView(addSaltView1);

                if (layoutAddSalts.getChildCount() > 1){
                    imageRemoveSalt.setVisibility(View.VISIBLE
                    );
                }
            }
        });

        imageRemoveSalt.setOnClickListener(view -> {
            if (layoutAddSalts.getChildCount() > 1){
                layoutAddSalts.removeViewAt(layoutAddSalts.getChildCount() - 1);
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}