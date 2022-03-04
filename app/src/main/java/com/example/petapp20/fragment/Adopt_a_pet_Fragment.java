package com.example.petapp20.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.petapp20.Adopt_pet_search;
import com.example.petapp20.R;


public class Adopt_a_pet_Fragment extends Fragment {

    public ImageView Adopt_paw;

    public Adopt_a_pet_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adopt_a_pet_, container, false);
        Adopt_paw = view.findViewById(R.id.Adopt_paw);
        Adopt_paw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Adopt_pet_search.class);
                startActivity(intent);
            }
        });
        return view;
    }
}