package com.example.thirukural;

import com.example.thirukural.model.ResponseModel;

import org.json.JSONObject;

import java.util.Random;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/api?")
    Call<ResponseModel> getResponse(@Query("num") String number);

}
