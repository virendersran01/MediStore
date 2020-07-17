package com.virtualstudios.medistore.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.virtualstudios.medistore.R;

import java.util.Objects;

public class SignUpFragment extends Fragment {

    private View rootView;
    private TextInputLayout inputFullName, inputEmail, inputPhone, inputUsername, inputPassword, inputConfirmPassword;
    private MaterialButton buttonRegister;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
            initViews();
        }
        return rootView;
    }

    private void initViews(){
        inputFullName = rootView.findViewById(R.id.inputLayoutFullName);
        inputEmail = rootView.findViewById(R.id.inputLayoutEmail);
        inputPhone = rootView.findViewById(R.id.inputLayoutPhone);
        inputUsername = rootView.findViewById(R.id.inputLayoutUsername);
        inputPassword = rootView.findViewById(R.id.inputLayoutPassword);
        inputConfirmPassword = rootView.findViewById(R.id.inputLayoutConfirmPassword);
        buttonRegister = rootView.findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()){

                }else {
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

    private String getUsername(){
        return Objects.requireNonNull(inputUsername.getEditText()).getEditableText().toString();
    }

    private String getPassword(){
        return Objects.requireNonNull(inputPassword.getEditText()).getEditableText().toString();
    }

    private String getConfirmPassword(){
        return Objects.requireNonNull(inputConfirmPassword.getEditText()).getEditableText().toString();
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

    private boolean isValidUserName() {
        if (getUsername().length() < 4) {
            Objects.requireNonNull(inputUsername.getEditText()).setError(getString(R.string.error_username_min_length));
            return false;
        }
        if (getUsername().length() > 25) {
            Objects.requireNonNull(inputUsername.getEditText()).setError(getString(R.string.error_username_max_length));
            return false;
        }
        if (getUsername().isEmpty()) {
            Objects.requireNonNull(inputUsername.getEditText()).setError(getString(R.string.error_username_empty));
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

    private boolean isValidPassword() {
        if (getPassword().length() < 6) {
            Objects.requireNonNull(inputPassword).setError(getString(R.string.error_password_min_length));
            return false;
        }
        return true;
    }

    private boolean isValidConfirmPassword() {
        if (!getConfirmPassword().equals(getPassword())) {
            Objects.requireNonNull(inputConfirmPassword.getEditText()).setError(getString(R.string.error_confirm_password));
            return false;
        }
        return true;
    }

    private boolean validation(){
        return isValidFullName() & isValidEmail() & isValidPhone() & isValidUserName()& isValidPassword() & isValidConfirmPassword();
    }
}