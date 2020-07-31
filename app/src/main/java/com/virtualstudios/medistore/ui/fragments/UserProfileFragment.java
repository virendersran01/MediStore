package com.virtualstudios.medistore.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.data.models.User;
import com.virtualstudios.medistore.data.remote.UserApi;
import com.virtualstudios.medistore.data.volley.VolleyCallBacks;
import com.virtualstudios.medistore.ui.activities.LoginActivity;
import com.virtualstudios.medistore.ui.customviews.AvatarView;
import com.virtualstudios.medistore.utils.Constants;

import java.util.Map;

public class UserProfileFragment extends Fragment {
    private View rootView;
    private AvatarView avatarView;
    private TextView textUsername;

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
        avatarView = rootView.findViewById(R.id.avatarView);
        textUsername = rootView.findViewById(R.id.textUsername);

        avatarView.setUser(Constants.getSPreferences(rootView.getContext()).getLoggedInUser());
        textUsername.setText(Constants.getSPreferences(rootView.getContext()).getLoggedInUser().getName());

        getStaffList();
    }

    private void getStaffList(){
        UserApi userApi = new UserApi(rootView.getContext());
        userApi.getStaffUsers(new VolleyCallBacks() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(TYPE type, Map<String, String> errorList) {

            }
        });
    }
}