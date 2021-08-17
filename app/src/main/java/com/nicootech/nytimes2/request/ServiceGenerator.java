package com.nicootech.nytimes2.request;

import com.nicootech.nytimes2.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static NYTApi nytApi = retrofit.create(NYTApi.class);

    public static NYTApi getNytApi(){
        return nytApi;
    }
}
