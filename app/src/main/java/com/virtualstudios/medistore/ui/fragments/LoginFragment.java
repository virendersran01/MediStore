package com.virtualstudios.medistore.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.virtualstudios.medistore.R;

public class LoginFragment extends Fragment {

    private View rootView;
    private TextInputLayout inputLayoutUsername;
    private TextInputLayout inputLayoutPassword;

    private MaterialButton buttonRegister;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_login, container, false);
            initViews();
        }
        return rootView;
    }

    private void initViews(){
        buttonRegister = rootView.findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.signUpFragment);
            }
        });
    }
}