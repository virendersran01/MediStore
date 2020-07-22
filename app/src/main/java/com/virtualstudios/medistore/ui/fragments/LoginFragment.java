package com.virtualstudios.medistore.ui.fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.data.remote.UserApi;
import com.virtualstudios.medistore.data.volley.VolleyCallBacks;
import com.virtualstudios.medistore.ui.activities.MainActivity;
import com.virtualstudios.medistore.utils.Utils;

import java.util.Map;
import java.util.Objects;

public class LoginFragment extends Fragment {

    private View rootView;
    private TextInputLayout inputLayoutUsername;
    private TextInputLayout inputLayoutPassword;

    private MaterialButton buttonRegister, buttonLogin;
    private TextView textForgotPassword;
    private AlertDialog dialogForgotPassword, alertDialogProgress;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_login, container, false);
            initViews();
        }
        return rootView;
    }

    private void initViews() {
        inputLayoutUsername = rootView.findViewById(R.id.text_layout_username);
        inputLayoutPassword = rootView.findViewById(R.id.text_layout_password);
        buttonRegister = rootView.findViewById(R.id.buttonRegister);
        buttonLogin = rootView.findViewById(R.id.buttonLogin);
        textForgotPassword = rootView.findViewById(R.id.textForgotPassword);
        alertDialogProgress = Utils.createProgressDialog(rootView.getContext());


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.signUpFragment);
            }
        });

        textForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgotPasswordDialog();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signIn();
            }
        });
    }

    private void showForgotPasswordDialog() {
        if (dialogForgotPassword == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());
            View view = LayoutInflater.from(rootView.getContext()).inflate(
                    R.layout.layout_forgot_password,
                    null
            );
            builder.setView(view);
            dialogForgotPassword = builder.create();
            if (dialogForgotPassword.getWindow() != null) {
                dialogForgotPassword.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            view.findViewById(R.id.textReset).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            view.findViewById(R.id.textCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogForgotPassword.dismiss();
                }
            });
        }

        dialogForgotPassword.show();
    }

    private String getUserName() {
        return Objects.requireNonNull(inputLayoutUsername.getEditText()).getEditableText().toString();
    }

    private String getUserPassword() {
        return Objects.requireNonNull(inputLayoutPassword.getEditText()).getEditableText().toString();
    }

    private  boolean isValidUsernameEmail(){
        if (isEmail()){

            return isEmailValid(getUserName());
        }else {
            return isValidUserName();
        }
    }


    private boolean isValidUserName() {
        if (getUserName().length() < 4) {
            inputLayoutUsername.setError(getString(R.string.error_username_min_length));
            return false;
        }
        if (getUserName().length() > 25) {
            inputLayoutUsername.setError(getString(R.string.error_username_max_length));
            return false;
        }
        if (getUserName().isEmpty()) {
            inputLayoutUsername.setError(getString(R.string.error_username_empty));
            return false;
        }
        return true;
    }

    private boolean isEmail(){
        return getUserName().contains("@");
    }




    private boolean isEmailValid(CharSequence email) {
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputLayoutUsername.setError(getString(R.string.error_email_invalid));
            return false;
        }
        return true;
    }


    private boolean isValidPassword() {
        if (getUserPassword().length() < 6) {
            inputLayoutPassword.setError(getString(R.string.error_password_min_length));
            return false;
        }
        return true;
    }

    private void signIn(){
        if (isValidUsernameEmail() && isValidPassword()){
            requestLogin();
        }
    }

    private void requestLogin(){
        alertDialogProgress.show();
        UserApi userApi = new UserApi(rootView.getContext());
        userApi.loginWithUsernamePassword(getUserName(), getUserPassword(),
                new VolleyCallBacks() {
                    @Override
                    public void onSuccess() {
                        alertDialogProgress.hide();
                        startActivity(new Intent(rootView.getContext(), MainActivity.class));
                        requireActivity().finish();
                    }

                    @Override
                    public void onError(TYPE type, Map<String, String> errorList) {
                        alertDialogProgress.hide();
                    }
                });
    }

}