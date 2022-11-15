package com.example.thirukural.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thirukural.FavouriteDataBase;
import com.example.thirukural.R;
import com.example.thirukural.RetrofitAPI;
import com.example.thirukural.activity.MainScreen;
import com.example.thirukural.model.ResponseModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.api.client.util.Data;
import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomKural extends Fragment {

    TextToSpeech textToSpeech;

    CheckBox translate;

    TextView kuralNumber, athigaram, iyal, paal, kural, meaning,fav_card_txt;

    ShimmerFrameLayout shimmerFrameLayout;

    LinearLayout kural_layout;

    ImageView speak,fav_icon;

    Button randombtn,sharebtn;

    CardView fav;

    FavouriteDataBase favouriteDataBase;
    String paalstr,athigaramstr,iyalstr,kuralstr,kuralNumberstr,meaningstr,sect_eng,chapgrp_eng,chap_eng,eng,eng_exp;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RandomKural() {

    }

    public static RandomKural newInstance(String param1, String param2) {
        RandomKural fragment = new RandomKural();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_random, container, false);
        ImageView back = view.findViewById(R.id.back);

        setUpUi(view);

        favouriteDataBase = new FavouriteDataBase(getContext());

        back.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_random_to_mainFragment);
        });

        Random random = new Random();

        if (MainScreen.from_saved) {
            String value = MainScreen.kural_number;
            sendRequest(value);
        }
        else{
            sendRequest(String.valueOf(random.nextInt(1331)));
        }

        randombtn.setOnClickListener(v->{
            sendRequest(String.valueOf(random.nextInt(1331)));
        });

        fav.setOnClickListener(v->{
            if (!favouriteDataBase.getFavouriteList().contains(kuralNumber.getText().toString())){
                favouriteDataBase.addFavourite(kuralNumber.getText().toString());
                fav_icon.setImageDrawable(getActivity().getDrawable(R.drawable.heart_small_selected));
                fav_card_txt.setText("Remove from favourites");
            }
            else if (favouriteDataBase.getFavouriteList().contains(kuralNumber.getText().toString())){
                fav_icon.setImageDrawable(getActivity().getDrawable(R.drawable.heart_small));
                favouriteDataBase.removeFavourite(kuralNumber.getText().toString());
                fav_card_txt.setText("Add to favourites");
            }
        });

        sharebtn.setOnClickListener(v->{
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, String.format("குறள்:%s\n\t%s\nபொருள்:\n\t%s",kuralNumber.getText().toString(),kural.getText().toString(),meaning.getText().toString()));
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });

        translate.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                translate.setText("தமிழ்");
                setData(sect_eng,chap_eng,chapgrp_eng,eng,kuralNumberstr,eng_exp);
            }
            else{
                translate.setText("Eng");
                setData(paalstr,athigaramstr,iyalstr,kuralstr,kuralNumberstr,meaningstr);
            }
        });

        return view;
    }

    void sendRequest(String number) {

//        number = "872";

        if (!favouriteDataBase.getFavouriteList().contains(number)){
            fav_icon.setImageDrawable(getActivity().getDrawable(R.drawable.heart_small));
            fav_card_txt.setText("Add to favourites");
        }
        else if (favouriteDataBase.getFavouriteList().contains(number)){
            fav_icon.setImageDrawable(getActivity().getDrawable(R.drawable.heart_small_selected));
            fav_card_txt.setText("Remove from favourites");
        }

        kural_layout.setVisibility(View.GONE);
        shimmerFrameLayout.setVisibility(View.VISIBLE);

        String url = "https://api-thirukkural.vercel.app/";

        System.out.println("Number " + number);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<ResponseModel> call = retrofitAPI.getResponse(number);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                kural_layout.setVisibility(View.VISIBLE);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                kural_layout.setVisibility(View.VISIBLE);
                paalstr = response.body().getSect_tam();
                athigaramstr = response.body().getChap_tam();
                iyalstr = response.body().getChapgrp_tam();
                kuralstr = String.format("%s\n%s", response.body().getLine1(), response.body().getLine2());
                kuralNumberstr = response.body().getNumber();
                meaningstr = response.body().getTam_exp();
                sect_eng = response.body().getSect_eng();
                chapgrp_eng = response.body().getChapgrp_eng();
                chap_eng = response.body().getChap_eng();
                eng = response.body().getEng();
                eng_exp = response.body().getEng_exp();

                setData(paalstr,athigaramstr,iyalstr,kuralstr,kuralNumberstr,meaningstr);

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }

    private void setData(String paalstr,String athigaramstr,String iyalstr,String kuralstr,String kuralNumberstr,String meaningstr) {
        paal.setText(paalstr);
        athigaram.setText(athigaramstr);
        iyal.setText(iyalstr);
        kural.setText(kuralstr);
        kuralNumber.setText(kuralNumberstr);
        meaning.setText(meaningstr);
    }

    private void setUpUi(View view) {
        kuralNumber = view.findViewById(R.id.kural_number);
        athigaram = view.findViewById(R.id.athigaram);
        iyal = view.findViewById(R.id.iyal);
        kural = view.findViewById(R.id.kural);
        kural_layout = view.findViewById(R.id.kural_layout);
        meaning = view.findViewById(R.id.meaning);
        paal = view.findViewById(R.id.paal);
        translate = view.findViewById(R.id.translate);
        shimmerFrameLayout = view.findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();
        randombtn = view.findViewById(R.id.randombtn);
        fav = view.findViewById(R.id.addToFav);
        fav_icon = view.findViewById(R.id.fav_icon);
        sharebtn = view.findViewById(R.id.sharebtn);
        fav_card_txt = view.findViewById(R.id.fav_card_txt);
        translate = view.findViewById(R.id.translate);
    }
}