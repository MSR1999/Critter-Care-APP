package com.example.petapp20.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.petapp20.Find_pet;
import com.example.petapp20.R;


public class Find_a_pet_Fragment extends Fragment {


    public ImageView paw;


    public Find_a_pet_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_find_a_pet_, container, false);

        paw = view.findViewById(R.id.Find_paw);
        paw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Find_pet.class);
                startActivity(intent);
            }
        });
        return view;
    }
}