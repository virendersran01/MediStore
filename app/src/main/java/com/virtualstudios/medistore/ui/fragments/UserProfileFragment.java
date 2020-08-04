package com.virtualstudios.medistore.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.data.models.User;
import com.virtualstudios.medistore.data.remote.UserApi;
import com.virtualstudios.medistore.data.volley.VolleyCallBacks;
import com.virtualstudios.medistore.ui.activities.LoginActivity;
import com.virtualstudios.medistore.ui.adapters.StaffAdapter;
import com.virtualstudios.medistore.ui.customviews.AvatarView;
import com.virtualstudios.medistore.ui.customviews.UserAvatarView;
import com.virtualstudios.medistore.utils.Constants;

import java.util.Map;

public class UserProfileFragment extends Fragment {
    private View rootView;
    private UserAvatarView avatarView;
    private TextView textUsername;
    private TextView textBusinessName;
    private RecyclerView recyclerViewStaff;

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
        textBusinessName = rootView.findViewById(R.id.textBusinessName);

        avatarView.setUser(Constants.getSPreferences(rootView.getContext()).getUserFullName(), null, null);
        textUsername.setText(Constants.getSPreferences(rootView.getContext()).getUserFullName());
        textBusinessName.setText(Constants.getSPreferences(rootView.getContext()).getBusinessName());

        recyclerViewStaff = rootView.findViewById(R.id.recyclerViewStaff);
        recyclerViewStaff.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        getStaffList();
    }

    private void getStaffList(){
        UserApi userApi = new UserApi(rootView.getContext());
        userApi.getStaffUsers(new VolleyCallBacks() {
            @Override
            public void onSuccess() {
                recyclerViewStaff.setAdapter(new StaffAdapter(userApi.staffList));
            }

            @Override
            public void onError(TYPE type, Map<String, String> errorList) {

            }
        });
    }
}