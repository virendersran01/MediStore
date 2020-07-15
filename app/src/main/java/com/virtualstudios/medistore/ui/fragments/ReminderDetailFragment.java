package com.virtualstudios.medistore.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.data.models.User;
import com.virtualstudios.medistore.ui.adapters.UsersAdapter;
import com.virtualstudios.medistore.ui.customviews.AvatarView;


public class ReminderDetailFragment extends Fragment {

    private View rootView;
    private AvatarView avatarView;
    private RecyclerView recyclerViewUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_reminder_detail, container, false);
        initViews();
        return rootView;
    }

    private void initViews(){
        avatarView = rootView.findViewById(R.id.avatar);
        recyclerViewUsers = rootView.findViewById(R.id.recyclerViewUsers);
        User user = new User();
        user.setName("Virender Sran");
        user.setColor(R.color.colorPrimaryDark);
        user.setAvatarUrl("https://picsum.photos/200/300");


        avatarView.setUser(user);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerViewUsers.setAdapter(new UsersAdapter());
    }
}