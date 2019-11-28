package com.example.bottomappbars;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bottomappbars.fragments.BurgerFragment;
import com.example.bottomappbars.fragments.CrossFragment;
import com.example.bottomappbars.fragments.InfoFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private FloatingActionButton floatingActionButton;

    private FragmentManager fragmentManager;

    private Fragment burgerFragment;
    private Fragment infoFragment;
    private Fragment crossFragment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomAppBar = findViewById(R.id.bar);
        floatingActionButton = findViewById(R.id.fab);

        fragmentManager = getSupportFragmentManager();
        burgerFragment = new BurgerFragment();
        infoFragment = new InfoFragment();
        crossFragment = new CrossFragment();

        fragmentManager.beginTransaction().add(R.id.main_content, crossFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_content, infoFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_content, burgerFragment).commit();

        bottomAppBar.setOnMenuItemClickListener(new MenuItemHandler());
        bottomAppBar.setOnClickListener(new NavigationButtonHandler());
        floatingActionButton.setOnClickListener(new FloatingActionButtonHandler());
    }

    private class MenuItemHandler implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(String.valueOf(item).equals(getResources().getString(R.string.info))) {
                hideAllFragments();
                fragmentManager.beginTransaction().show(infoFragment).commit();
            } else {
                Toast.makeText(getApplicationContext(), "Clicked: " + item, Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    private class NavigationButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            hideAllFragments();
            fragmentManager.beginTransaction().show(burgerFragment).commit();
        }
    }

    private class FloatingActionButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            hideAllFragments();
            fragmentManager.beginTransaction().show(crossFragment).commit();
        }
    }

    private void hideAllFragments(){
        fragmentManager.beginTransaction().hide(burgerFragment).commit();
        fragmentManager.beginTransaction().hide(infoFragment).commit();
        fragmentManager.beginTransaction().hide(crossFragment).commit();
    }
}
