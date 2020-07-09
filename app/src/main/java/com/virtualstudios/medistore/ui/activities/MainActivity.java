package com.virtualstudios.medistore.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.virtualstudios.medistore.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Context mContext = MainActivity.this;
    private FragmentContainerView fragmentContainerView;
    private FloatingActionButton fabAddReminder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentContainerView = findViewById(R.id.fragmentContainer);
        fabAddReminder = findViewById(R.id.fabAddReminder);

        fabAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation.findNavController(fragmentContainerView).navigate(R.id.addReminderFragment);
                startActivity(new Intent(mContext, AddReminderActivity.class));
            }
        });

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(fragmentContainerView.getId());
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getLabel().equals("Add Reminder")){
                    fabAddReminder.setVisibility(View.GONE);
                }else {
                    fabAddReminder.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}