package com.virtualstudios.medistore.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.utils.Constants;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Context mContext = MainActivity.this;
    private FragmentContainerView fragmentContainerView;
    private FloatingActionButton fabAddReminder;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(fragmentContainerView.getId());
        navController = Objects.requireNonNull(navHostFragment).getNavController();

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

            }
        });

    }

    private void initViews(){
        fragmentContainerView = findViewById(R.id.fragmentContainer);
        fabAddReminder = findViewById(R.id.fabAddReminder);
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);

        fabAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // 0 for add medicine, 1 for add reminder, 2 for add staff
                if (Objects.requireNonNull(Objects.requireNonNull(navController.getCurrentDestination()).getLabel()).equals("Home")){
                    startActivity(new Intent(mContext, AddActivity.class).putExtra(Constants.INTENT_KEY_ADD_TYPE, 0));
                }else if (Objects.equals(navController.getCurrentDestination().getLabel(), "Reminders")){
                    startActivity(new Intent(mContext, AddActivity.class).putExtra(Constants.INTENT_KEY_ADD_TYPE, 1));
                }else if (navController.getCurrentDestination().getLabel().equals("Profile")){
                    startActivity(new Intent(mContext, AddActivity.class).putExtra(Constants.INTENT_KEY_ADD_TYPE, 2));
                }

            }
        });

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.homeFragment);
                        break;
                    case R.id.menuReminder:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.remindersFragment);
                        break;
                    case R.id.menuProfile:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.userProfileFragment);
                        break;
                }
                return false;
            }
        });

    }
}