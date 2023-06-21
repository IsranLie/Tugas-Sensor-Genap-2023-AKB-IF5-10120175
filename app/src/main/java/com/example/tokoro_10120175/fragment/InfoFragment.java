package com.example.tokoro_10120175.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.tokoro_10120175.R;
/*
NIM     : 10120175
Nama    : I Wayan Widi Pastika
Kelas   : IF-5
22 Juni 2023
*/
public class InfoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_info, container, false);
        return rootView;
    }
}