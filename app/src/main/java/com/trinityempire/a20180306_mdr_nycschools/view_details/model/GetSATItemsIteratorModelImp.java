package com.trinityempire.a20180306_mdr_nycschools.view_details.model;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.trinityempire.a20180306_mdr_nycschools.ApiUtils;
import com.trinityempire.a20180306_mdr_nycschools.database.DatabaseHandler;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;
 import com.trinityempire.a20180306_mdr_nycschools.retrofit_res_client.RetroApiInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by disciplemarc on 3/7/18.
 */

public class GetSATItemsIteratorModelImp implements GetSATItemsIteratorModel{

    private OnFinishListener listener;
    @Override
    public void getItems(OnFinishListener listener, String dbn) {
        SchoolsLog.d("getItems() for SATs");
        this.listener = listener;
         loadData(dbn);
    }

    @Override
    public void setUpItems(OnFinishListener listener, Schools data) {
        SchoolsLog.d("setUpItems() is called to setup the data to be sent back to detials activity");
        HashMap<String, String> hm = new HashMap<>();

        hm.put(hmKeys.school_name, data.getSchool_name());
        hm.put(hmKeys.overview_paragraph, data.getOverview_paragraph());

        String eligibility_str;
        if (data.getEligibility1() == null || data.getEligibility1().isEmpty()) {
            eligibility_str = "<b>Eligibility: " + "</b> none";
            hm.put(hmKeys.eligibility1, eligibility_str);
        } else {
            eligibility_str = "<b>Eligibility: " + "</b>" + data.getEligibility1();
            hm.put(hmKeys.eligibility1, eligibility_str);
        }

        String opportunity =
                "<ol type=\"a\"><li>" + data.getAcademicopportunities1()
                        + "<li> " + data.getAcademicopportunities2()
                        + "<li>"  + data.getAcademicopportunities3()
                        +" <li>"  + "English Language Learner Programs:" + data.getEll_programs()
                        + "<li>"  + "Opportunity: " + data.getAcademicopportunities1()
                        + "</ol>";
        hm.put(hmKeys.academicopportunities1,opportunity);

        if (data.getSchool_sports() == null || data.getSchool_sports().isEmpty())
            hm.put(hmKeys.school_sports, "none");
        else
            hm.put(hmKeys.school_sports, data.getSchool_sports());


        String admin =
                "<ol type=\"a\"><li>" + data.getAdmissionspriority11()
                        +"</ol>";
        hm.put(hmKeys.admissionspriority11, admin);

        hm.put(hmKeys.longitude, data.getLongitude());
        hm.put(hmKeys.latitude, data.getLatitude());


        String loc = "<b>Address: </b>"+data.getLocation();
        hm.put(hmKeys.location, loc);

        String trans = "<b>Buses: </b>"+data.getBus();
        hm.put(hmKeys.bus, trans);

        new Handler().post(() -> {
            listener.onFinishListener(hm);
        });
    }

    @Override
    public void getIfFavOnItem(OnFinishListener listener, Context context, String school_name) {
        SchoolsLog.d("getIfFavOnItem() is called, calling the data base");
        DatabaseHandler db = new DatabaseHandler(context);
        boolean rtn = false;
        try {
            if (db.isThere(school_name)) {
                rtn = true;
            } else {
                rtn = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean finalRtn = rtn;
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                listener.onFinisheListener(finalRtn);
            }
        });

    }

    private void loadData(String search_dm) {
        SchoolsLog.d("loadData() is called, loading data from server for SATs score.");
        // initialize an instance of the RetroApiInterface interface
        RetroApiInterface apiInterface = ApiUtils.getRetroApiClient();
        // Retrofit helps us to parse the JSON response to a list of Java objects
        Call<List<SchoolSATscore>> call = apiInterface.getSchoolsSATscore(ApiUtils.API_KEY, search_dm);
        SchoolsLog.d("loadData() is called");
        call.enqueue(new Callback<List<SchoolSATscore>>() {
            @Override
            public void onResponse(@NonNull Call<List<SchoolSATscore>> call, @NonNull Response<List<SchoolSATscore>> response) {
                SchoolsLog.d("Successfully loaded the requested data "+response.body());
                List<SchoolSATscore> list = response.body();
                String data = "none";
                assert list != null;
                if (list.size() != 0) {
                    SchoolSATscore item = list.get(0);
                    data = "SATs test takers: " + item.getNum_of_sat_test_takers()
                            + "<ol type=\"a\">"
                            + "<li>Avg. Reading Score: " + item.getSat_critical_reading_avg_score()
                            + "<li>Avg. Writing Score: " + item.getSat_writing_avg_score()
                            + "<li>Avg. Math Score: " + item.getSat_math_avg_score()
                            + "</ol>";


                }
                String finalData = data;
                new Handler().post(() -> {
                    SchoolsLog.d("Data send to Presenter");
                    listener.onFinishListener(finalData);
                });

            }

             @Override
            public void onFailure(Call<List<SchoolSATscore>> call, Throwable t) {
                SchoolsLog.d("onFailure: "+ t.toString());
            }
        });
    }
}
