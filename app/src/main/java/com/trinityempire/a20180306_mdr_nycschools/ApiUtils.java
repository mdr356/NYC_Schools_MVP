package com.trinityempire.a20180306_mdr_nycschools;

import com.trinityempire.a20180306_mdr_nycschools.retrofit_res_client.RetroApiClient;
import com.trinityempire.a20180306_mdr_nycschools.retrofit_res_client.RetroApiInterface;

/**
 * Created by disciplemarc on 3/5/18.
 * This class contains have the base URL as a static
 * variable and also provide the RetroApiInterface interface to
 * the application through the getRetrofitClient() static method.
 *
 * ALso coantsint eh api key
 */

public class ApiUtils {
    public static String BASE_URL = "https://data.cityofnewyork.us/";
    public static String API_KEY = "DxWjlbzfaUJ9QtCXVX9M31MaZ";

    public static RetroApiInterface getRetroApiClient() {
        return RetroApiClient.getRetrofitClient(BASE_URL).create(RetroApiInterface.class);
    }
}

