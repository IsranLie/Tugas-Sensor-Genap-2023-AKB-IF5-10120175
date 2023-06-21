package com.example.tokoro_10120175.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.tokoro_10120175.R;
import com.example.tokoro_10120175.databinding.ActivityMainBinding;
import com.example.tokoro_10120175.fragment.InfoFragment;
import com.example.tokoro_10120175.fragment.LocationFragment;
import com.example.tokoro_10120175.fragment.NearbyPlacesFragment;
import com.example.tokoro_10120175.fragment.ProfileFragment;
/*
NIM     : 10120175
Nama    : I Wayan Widi Pastika
Kelas   : IF-5
22 Juni 2023
*/
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new LocationFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.location:
                    replaceFragment(new LocationFragment());
                    break;
                case R.id.resto:
                    replaceFragment(new NearbyPlacesFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.info:
                    replaceFragment(new InfoFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}