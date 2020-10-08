package com.virtualstudios.medistore.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.virtualstudios.medistore.R;

public class AddProductFragment extends Fragment {

    private View rootView;
    private TextView textHeader;
    private MaterialButton buttonAddMedicated, buttonAddCosmetic;
    private ScrollView scrollView;
    private View viewStep1, viewStep2, viewStep3, viewStep4, viewStep5, viewProductDetail;
    private MaterialButton buttonStep2, buttonStep3, buttonStep4, buttonStep5;
    private MaterialButton buttonPrevStep1, buttonPrevStep2, buttonPrevStep3, buttonPrevStep4, buttonDone;
    private TextView viewTextTitleName, viewTextName;
    private TextView viewTextTitleIsAthical, viewTextIsAthical;
    private TextView viewTextTitleMedicineCategory, viewTextMedicineCategory;
    private TextView viewTextTitleMedicineType, viewTextMedicineType;
    private LinearLayout linearLayoutSalt;
    private TextView viewTextTitleManufactureBy, viewTextManufactureBy;
    private TextView viewTextTitleMarketingBy, viewTextMarketingBy;
    private TextView viewTextTitleQty, viewTextQty;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_product, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        textHeader = rootView.findViewById(R.id.textHeader);
        buttonAddMedicated = rootView.findViewById(R.id.buttonAddMedicine);
        buttonAddCosmetic = rootView.findViewById(R.id.buttonAddCosmetic);
        scrollView = rootView.findViewById(R.id.scrollView);
        viewStep1 = rootView.findViewById(R.id.viewStep1);
        viewStep2 = rootView.findViewById(R.id.viewStep2);
        viewStep3 = rootView.findViewById(R.id.viewStep3);
        viewStep4 = rootView.findViewById(R.id.viewStep4);
        viewStep5 = rootView.findViewById(R.id.viewStep5);
        viewProductDetail = rootView.findViewById(R.id.viewProductDetail);
        initDetailViews(viewProductDetail);
        buttonStep2 = viewStep1.findViewById(R.id.buttonNext);
        buttonStep3 = viewStep2.findViewById(R.id.buttonNext);
        buttonStep4 = viewStep3.findViewById(R.id.buttonNext);
        buttonStep5 = viewStep4.findViewById(R.id.buttonNext);
        buttonPrevStep1 = viewStep2.findViewById(R.id.buttonPrevious);
        buttonPrevStep2 = viewStep3.findViewById(R.id.buttonPrevious);
        buttonPrevStep3 = viewStep4.findViewById(R.id.buttonPrevious);
        buttonPrevStep4 = viewStep5.findViewById(R.id.buttonPrevious);
        buttonDone = viewStep5.findViewById(R.id.buttonDone);


        buttonAddMedicated.setOnClickListener(v -> {
            showHideButtonMedicated(false);
            showHideButtonCosmetic(false);
            scrollView.setVisibility(View.VISIBLE);
            viewStep1.setVisibility(View.VISIBLE);
            textHeader.setText("Add Medicine");
        });

        buttonStep2.setOnClickListener(v -> {
            viewStep1.setVisibility(View.GONE);
            viewStep2.setVisibility(View.VISIBLE);
            viewProductDetail.setVisibility(View.VISIBLE);
        });

        buttonStep3.setOnClickListener(v -> {
            viewStep2.setVisibility(View.GONE);
            viewStep3.setVisibility(View.VISIBLE);
            showHideDetailViews2(true);
        });

        buttonStep4.setOnClickListener(v -> {
            viewStep3.setVisibility(View.GONE);
            viewStep4.setVisibility(View.VISIBLE);
            showHideDetailViews3(true);
        });

        buttonStep5.setOnClickListener(v -> {
            viewStep4.setVisibility(View.GONE);
            viewStep5.setVisibility(View.VISIBLE);
            showHideDetailViews4(true);
        });

        buttonPrevStep1.setOnClickListener(v -> {
            viewStep2.setVisibility(View.GONE);
            viewStep1.setVisibility(View.VISIBLE);
        });

        buttonPrevStep2.setOnClickListener(v -> {
            viewStep3.setVisibility(View.GONE);
            viewStep2.setVisibility(View.VISIBLE);
        });

        buttonPrevStep3.setOnClickListener(v -> {
            viewStep4.setVisibility(View.GONE);
            viewStep3.setVisibility(View.VISIBLE);
        });

        buttonPrevStep4.setOnClickListener(v -> {
            viewStep5.setVisibility(View.GONE);
            viewStep4.setVisibility(View.VISIBLE);
        });

        buttonDone.setOnClickListener(v -> {
            viewStep5.setVisibility(View.GONE);
            showHideDetailViews5(true);
        });
    }

    private void initDetailViews(View viewDetail){
        viewTextTitleName = viewDetail.findViewById(R.id.textTitleName);
        viewTextName = viewDetail.findViewById(R.id.textName);
        viewTextTitleIsAthical = viewDetail.findViewById(R.id.textTitleIsAthical);
        viewTextIsAthical = viewDetail.findViewById(R.id.textIsAthical);
        viewTextTitleMedicineCategory = viewDetail.findViewById(R.id.textTitleMedicineCategory);
        viewTextMedicineCategory = viewDetail.findViewById(R.id.textMedicineCategory);
        viewTextTitleMedicineType = viewDetail.findViewById(R.id.textTitleMedicineType);
        viewTextMedicineType = viewDetail.findViewById(R.id.textMedicineType);
        linearLayoutSalt = viewDetail.findViewById(R.id.linearLayoutSalt);
        viewTextTitleManufactureBy = viewDetail.findViewById(R.id.textTitleManufactureBy);
        viewTextManufactureBy = viewDetail.findViewById(R.id.textManufactureBy);
        viewTextTitleMarketingBy = viewDetail.findViewById(R.id.textTitleMarketingBy);
        viewTextMarketingBy = viewDetail.findViewById(R.id.textMarketingBy);
        viewTextTitleQty = viewDetail.findViewById(R.id.textTitleQty);
        viewTextQty = viewDetail.findViewById(R.id.textQty);
        showHideDetailViews2(false);
        showHideDetailViews3(false);
        showHideDetailViews4(false);
        showHideDetailViews5(false);
    }

    private void showHideButtonMedicated(boolean status) {
        if (status) {
            buttonAddMedicated.setVisibility(View.VISIBLE);
        } else {
            buttonAddMedicated.setVisibility(View.GONE);
        }
    }

    private void showHideButtonCosmetic(boolean status) {
        if (status) {
            buttonAddCosmetic.setVisibility(View.VISIBLE);
        } else {
            buttonAddCosmetic.setVisibility(View.GONE);
        }
    }

    private void showHideDetailViews2(boolean status){
        if (status){
            viewTextTitleMedicineCategory.setVisibility(View.VISIBLE);
            viewTextMedicineCategory.setVisibility(View.VISIBLE);
            viewTextTitleMedicineType.setVisibility(View.VISIBLE);
            viewTextMedicineType.setVisibility(View.VISIBLE);
        }else {
            viewTextTitleMedicineCategory.setVisibility(View.GONE);
            viewTextMedicineCategory.setVisibility(View.GONE);
            viewTextTitleMedicineType.setVisibility(View.GONE);
            viewTextMedicineType.setVisibility(View.GONE);
        }
    }

    private void showHideDetailViews3(boolean status){
        if (status){
            linearLayoutSalt.setVisibility(View.VISIBLE);
        }else {
            linearLayoutSalt.setVisibility(View.GONE);
        }
    }

    private void showHideDetailViews4(boolean status){
        if (status){
            viewTextTitleManufactureBy.setVisibility(View.VISIBLE);
            viewTextManufactureBy.setVisibility(View.VISIBLE);
            viewTextTitleMarketingBy.setVisibility(View.VISIBLE);
            viewTextMarketingBy.setVisibility(View.VISIBLE);
        }else {
            viewTextTitleManufactureBy.setVisibility(View.GONE);
            viewTextManufactureBy.setVisibility(View.GONE);
            viewTextTitleMarketingBy.setVisibility(View.GONE);
            viewTextMarketingBy.setVisibility(View.GONE);
        }
    }

    private void showHideDetailViews5(boolean status){
        if (status){
            viewTextTitleQty.setVisibility(View.VISIBLE);
            viewTextQty.setVisibility(View.VISIBLE);
        }else {
            viewTextTitleQty.setVisibility(View.GONE);
            viewTextQty.setVisibility(View.GONE);
        }
    }

}