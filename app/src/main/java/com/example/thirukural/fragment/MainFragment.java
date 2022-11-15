package com.example.thirukural.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thirukural.R;
import com.example.thirukural.activity.MainScreen;

public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {

    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ImageView valluPic = view.findViewById(R.id.valluvar);
        ImageView random = view.findViewById(R.id.random_card);
        CardView read = view.findViewById(R.id.read_card);
        ImageView saved = view.findViewById(R.id.saved);

        valluPic.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_valluvarInformation);
        });

        random.setOnClickListener(v->{
            MainScreen.from_saved = false;
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_random);
        });

        saved.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_savedFragment);
        });

        read.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_read);
        });

        return view;
    }
}