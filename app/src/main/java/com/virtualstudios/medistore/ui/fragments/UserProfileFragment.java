package com.virtualstudios.medistore.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.ui.activities.LoginActivity;
import com.virtualstudios.medistore.utils.Constants;

public class UserProfileFragment extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);
            initViews();
        }
        return rootView;
    }

    private void  initViews(){
        rootView.findViewById(R.id.imageLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.getSPreferences(rootView.getContext()).clearUserData();
                v.getContext().startActivity(new Intent(v.getContext(), LoginActivity.class));
                requireActivity().finish();
            }
        });
    }
}