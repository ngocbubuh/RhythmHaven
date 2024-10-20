package com.sap.rhythmhaven.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseURL = "https://rhythmhavenapi.azurewebsites.net";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            synchronized (APIClient.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
