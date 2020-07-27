package com.virtualstudios.medistore.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.data.remote.UserApi;
import com.virtualstudios.medistore.data.volley.VolleyCallBacks;
import com.virtualstudios.medistore.utils.Utils;

import java.util.Map;
import java.util.Objects;

public class AddStaffFragment extends Fragment {

    private View rootView;
    private TextInputLayout inputFullName, inputEmail, inputPhone, inputDesignation;
    private AlertDialog alertDialogProgress;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_add_staff, container, false);
        }
        initView();
        return rootView;
    }

    private void initView(){
        rootView.findViewById(R.id.imageBack).setOnClickListener(view -> requireActivity().onBackPressed());
        inputFullName = rootView.findViewById(R.id.inputLayoutFullName);
        inputEmail = rootView.findViewById(R.id.inputLayoutEmail);
        inputPhone = rootView.findViewById(R.id.inputLayoutPhone);
        inputDesignation = rootView.findViewById(R.id.inputLayoutDesignation);
        MaterialButton buttonAdd = rootView.findViewById(R.id.buttonAdd);
        if (alertDialogProgress == null){
            alertDialogProgress = Utils.createProgressDialog(rootView.getContext());
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()){
                    addStaff();
                }
            }
        });

    }

    private String getFullName(){
        return Objects.requireNonNull(inputFullName.getEditText()).getEditableText().toString();
    }

    private String getEmail(){
        return Objects.requireNonNull(inputEmail.getEditText()).getEditableText().toString();
    }

    private String getPhoneNumber(){
        return Objects.requireNonNull(inputPhone.getEditText()).getEditableText().toString();
    }

    private String getDesignation(){
        return Objects.requireNonNull(inputDesignation.getEditText()).getEditableText().toString();
    }

    private boolean isValidFullName() {
        if (getFullName().isEmpty()) {
            Objects.requireNonNull(inputFullName.getEditText()).setError(getString(R.string.error_username_empty));
            return false;
        }
        return true;
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidEmail() {
        if (!isEmailValid(getEmail())) {
            Objects.requireNonNull(inputEmail.getEditText()).setError(getString(R.string.error_email_invalid));
            return false;
        }
        if (getEmail().isEmpty()) {
            Objects.requireNonNull(inputEmail.getEditText()).setError(getString(R.string.error_username_empty));
            return false;
        }

        return true;
    }

    private boolean isMobileValid(CharSequence phoneNumber) {
        if (phoneNumber.length() != 10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(phoneNumber).matches();
        }
    }

    private boolean isValidPhone() {
        if (!isMobileValid(getPhoneNumber())) {
            Objects.requireNonNull(inputPhone.getEditText()).setError(getString(R.string.error_phone));
            return false;
        }
        return true;
    }

    private boolean isDesignation() {
        if (getDesignation().isEmpty()) {
            Objects.requireNonNull(inputDesignation.getEditText()).setError(getString(R.string.error_username_empty));
            return false;
        }
        return true;
    }

    private boolean validation(){
        return isValidFullName() & isValidEmail() & isValidPhone() & isDesignation();
    }

    private void addStaff(){
        alertDialogProgress.show();
        UserApi userApi = new UserApi(rootView.getContext());
        userApi.addStaffRequest(getFullName(), getEmail(), getPhoneNumber(), getDesignation(),
                new VolleyCallBacks() {
                    @Override
                    public void onSuccess() {
                        alertDialogProgress.dismiss();
                    }

                    @Override
                    public void onError(TYPE type, Map<String, String> errorList) {
                        alertDialogProgress.dismiss();
                    }
                });
    }
}