package com.example.agradeapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate tool bar on the top
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        // set the tool bar
        setSupportActionBar(toolbar);

        // set the drawer
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // set the side navigation bar to change fragment when click
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch(id) {
                    case R.id.nav_paradomo:
                        replaceFragment(new ParadomoFragment());
                        getSupportActionBar().setTitle("Paradomo");
                        break;
                    case R.id.nav_todoList:
                        replaceFragment(new ToDoListFragment());
                        getSupportActionBar().setTitle("To Do List");
                        break;
                    case R.id.nav_studyRoom:
                        replaceFragment(new StudyRoomFragment());
                        getSupportActionBar().setTitle("Study Room");
                        break;
                    case R.id.nav_statistics:
                        replaceFragment(new StatisticsFragment());
                        getSupportActionBar().setTitle("Statistics");
                        break;
                    case R.id.nav_ranking:
                        replaceFragment(new RankingFragment());
                        getSupportActionBar().setTitle("Ranking");
                        break;
                    case R.id.nav_settings:;
                        replaceFragment(new SettingsFragment());
                        getSupportActionBar().setTitle("Settings");
                        break;
                }
                return true;
            }
        });

        // set paradomo fragment as the home page
        replaceFragment(new ParadomoFragment());
    }

    // change fragment for the page
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}