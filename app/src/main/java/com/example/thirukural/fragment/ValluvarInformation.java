package com.example.thirukural.fragment;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thirukural.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ValluvarInformation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ValluvarInformation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ValluvarInformation() {
        // Required empty public constructor
    }

    public static ValluvarInformation newInstance(String param1, String param2) {
        ValluvarInformation fragment = new ValluvarInformation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_valluvar_information, container, false);
        ImageView back = view.findViewById(R.id.back);
        TextView textview= view.findViewById(R.id.valluvar_info);
        textview.setMovementMethod(new ScrollingMovementMethod());
        back.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_valluvarInformation_to_mainFragment);
        });
        return view;
    }
}