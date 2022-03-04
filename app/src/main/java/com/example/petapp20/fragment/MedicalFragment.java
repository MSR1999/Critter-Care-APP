package com.example.petapp20.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.petapp20.Post_pet;
import com.example.petapp20.R;


public class MedicalFragment extends Fragment {

    public ImageView home_paw;
    public MedicalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_medical, container, false);


        home_paw = view.findViewById(R.id.Home_btn);
        home_paw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Post_pet.class);
                startActivity(intent);
            }
        });
        return view;
    }

}