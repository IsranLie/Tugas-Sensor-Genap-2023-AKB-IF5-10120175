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
import com.example.tokoro_10120175.activity.NearbyPlacesActivity;
/*
NIM     : 10120175
Nama    : I Wayan Widi Pastika
Kelas   : IF-5
22 Juni 2023
*/
public class NearbyPlacesFragment extends Fragment {

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        return inflater.inflate(R.layout.fragment_nearby_places, container, false);
    }

    public void onStart(){
        super.onStart();
        Button btn= (Button) context.findViewById(R.id.nearby_places);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NearbyPlacesActivity.class);
                startActivity(intent);
            }
        });
    }
}
