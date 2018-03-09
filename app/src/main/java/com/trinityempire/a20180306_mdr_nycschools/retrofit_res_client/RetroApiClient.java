package com.trinityempire.a20180306_mdr_nycschools.retrofit_res_client;

import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
  /**
   * Created by Marc
   * the Retrofit Instance
 */

public class RetroApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient(String baseUrl) {
        SchoolsLog.d("getRetrofitClient() is called");
        SchoolsLog.d(baseUrl);
        if (retrofit==null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
