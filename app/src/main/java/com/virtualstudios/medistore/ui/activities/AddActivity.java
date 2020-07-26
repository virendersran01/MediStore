package com.virtualstudios.medistore.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.utils.Constants;

import java.util.Objects;

public class AddActivity extends AppCompatActivity {

    private FragmentContainerView fragmentContainerView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        fragmentContainerView = findViewById(R.id.fragmentContainer);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(fragmentContainerView.getId());
        navController = Objects.requireNonNull(navHostFragment).getNavController();

        NavGraph navGraph = navController.getGraph();


        if (getIntent().hasExtra(Constants.INTENT_KEY_ADD_TYPE)){  // 0 for add medicine, 1 for add reminder, 2 for add staff
            if (getIntent().getIntExtra(Constants.INTENT_KEY_ADD_TYPE, 0) == 0){
                navGraph.setStartDestination(R.id.addMedicineFragment);
                navController.setGraph(navGraph);
            }else if (getIntent().getIntExtra(Constants.INTENT_KEY_ADD_TYPE, 0) == 1){
                navGraph.setStartDestination(R.id.addReminderFragment);
                navController.setGraph(navGraph);
            }else if (getIntent().getIntExtra(Constants.INTENT_KEY_ADD_TYPE, 0) == 2){
                navGraph.setStartDestination(R.id.addStaffFragment);
                navController.setGraph(navGraph);
            }

        }
    }

}