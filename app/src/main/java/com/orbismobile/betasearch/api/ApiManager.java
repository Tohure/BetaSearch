package com.orbismobile.betasearch.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by crhto on 16/04/2017.
 */

public class ApiManager {

    private static Api api;

    public static Api apiManager(){
        if(api == null){

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl("http://172.18.60.113:8000/api/")
                    /*.baseUrl("http://192.168.1.7:8000/api/")*/
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = client.create(Api.class);
        }

        return api;
    }
}