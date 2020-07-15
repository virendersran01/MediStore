package com.virtualstudios.medistore.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.data.models.User;
import com.virtualstudios.medistore.ui.customviews.AvatarView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = new User();
        user.setName("Virender Sran");
        user.setColor(R.color.colorPrimaryDark);
        user.setAvatarUrl("https://picsum.photos/200/300");
        user.setDesignation("Android DevOps");
        holder.setData(user);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private AvatarView avatarView;
        private TextView textName, textDesignation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avatarView = itemView.findViewById(R.id.avatar);
            textName = itemView.findViewById(R.id.textName);
            textDesignation = itemView.findViewById(R.id.textDesignation);
        }

        private void setData(User user){
            avatarView.setUser(user);
            textName.setText(user.getName());
            textDesignation.setText(user.getDesignation());
        }
    }
}
