package com.trinityempire.a20180306_mdr_nycschools.retrofit_res_client;

import com.trinityempire.a20180306_mdr_nycschools.view_details.model.SchoolSATscore;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by Marc Daniel Registre
 *
 * This interface contains methods we are going to use to execute HTTP requests
 * such as GET, POST, PUT, PATCH, and DELETE
 */

public interface RetroApiInterface {
    @GET("/resource/97mf-9njv.json/")
    Call<List<Schools>> getSchools(@Query("$$app_token") String apiKey,
                                   @Query("$limit") int limit,
                                   @Query("$offset") int offset);

    @GET("/resource/734v-jeq5.json/")
    Call<List<SchoolSATscore>> getSchoolsSATscore(
                                    @Query("$$app_token") String apiKey,
                                    @Query("dbn") String dbn);

    @GET("/resource/97mf-9njv.json/")
    Call<List<Schools>> getSearchSchool(@Query("$$app_token") String apiKey,
                                        @Query("borough") String borough,
                                        @Query("$limit") int limit,
                                        @Query("$offset") int offset);;
}
