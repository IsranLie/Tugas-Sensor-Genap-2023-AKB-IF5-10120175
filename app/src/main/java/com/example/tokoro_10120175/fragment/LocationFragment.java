package com.example.tokoro_10120175.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tokoro_10120175.R;
import com.example.tokoro_10120175.activity.CurrentLocationActivity;
import com.example.tokoro_10120175.databinding.ActivityMainBinding;
/*
NIM     : 10120175
Nama    : I Wayan Widi Pastika
Kelas   : IF-5
22 Juni 2023
*/
public class LocationFragment extends Fragment {

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    public void onStart(){
        super.onStart();
        Button btn= (Button) context.findViewById(R.id.current_location);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CurrentLocationActivity.class);
                startActivity(intent);
            }
        });
    }
}
