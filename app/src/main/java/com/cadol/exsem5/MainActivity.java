package com.cadol.exsem5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.cadol.exsem5.VistaFragment.PerfilFragmen;
import com.cadol.exsem5.VistaFragment.RecyclerViewFragment;
import com.cadol.exsem5.adapter.PageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


        private static final String KEY_EXTRA_NAME = "name";
        private Toolbar toolbar;
        private TabLayout tabLayout;
        private ViewPager viewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            viewPager = (ViewPager) findViewById(R.id.viewPager);
             setUpViewPager();

            if (toolbar != null){
                setSupportActionBar(toolbar);
            }
        }

       private ArrayList<Fragment> agregarFragments(){
             ArrayList<Fragment> fragments = new ArrayList<>();

            fragments.add(new RecyclerViewFragment());  // Lengueta 1
            fragments.add(new PerfilFragmen());         // Lengueta 2

            return fragments;
       }

       private void setUpViewPager(){
           viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
           tabLayout.setupWithViewPager(viewPager);
           tabLayout.getTabAt(0).setIcon(R.drawable.icons8_dog_paw_50);
           tabLayout.getTabAt(1).setIcon(R.drawable.icons8_hotel_24);
       }
}