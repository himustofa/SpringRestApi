package com.mk.springrestapi.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mk.springrestapi.utils.ConstantKey;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient mInstance;
    private static Retrofit retrofit;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantKey.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    //For Singleton
    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }

}
