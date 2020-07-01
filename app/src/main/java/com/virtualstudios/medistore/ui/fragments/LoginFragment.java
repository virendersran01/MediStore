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
import com.virtualstudios.medistore.ui.activities.MainActivity;

public class LoginFragment extends Fragment {

    private View rootView;
    private TextInputLayout inputLayoutUsername;
    private TextInputLayout inputLayoutPassword;

    private MaterialButton buttonRegister, buttonLogin;
    private TextView textForgotPassword;
    private AlertDialog dialogForgotPassword;


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
        buttonRegister = rootView.findViewById(R.id.buttonRegister);
        buttonLogin = rootView.findViewById(R.id.buttonLogin);
        textForgotPassword = rootView.findViewById(R.id.textForgotPassword);


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
               startActivity(new Intent(v.getContext(), MainActivity.class));
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

}