package com.virtualstudios.medistore.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.data.models.User;
import com.virtualstudios.medistore.ui.widgets.UserAvatarView;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {
    private List<User> staffList;

    public StaffAdapter(List<User> staffList) {
        this.staffList = staffList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(staffList.get(position));
    }
    @Override
    public int getItemCount() {
        return staffList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        private UserAvatarView userAvatarView;
        private TextView textName, textEmail, textPhone, textDesignation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatarView = itemView.findViewById(R.id.userAvatarView);
            textName = itemView.findViewById(R.id.textName);
            textEmail = itemView.findViewById(R.id.textEmail);
            textPhone = itemView.findViewById(R.id.textPhone);
            textDesignation = itemView.findViewById(R.id.textDesignation);
        }

        public void setData(User user){
            userAvatarView.setUser(user.getName(), null, null);
            textName.setText(user.getName());
            textEmail.setText(user.getEmail());
            textPhone.setText(user.getPhoneNumber());
            textDesignation.setText(user.getDesignation());
        }
    }
}
