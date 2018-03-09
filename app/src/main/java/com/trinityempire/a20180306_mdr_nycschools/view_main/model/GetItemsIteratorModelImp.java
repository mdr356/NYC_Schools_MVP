package com.trinityempire.a20180306_mdr_nycschools.view_main.model;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.trinityempire.a20180306_mdr_nycschools.ApiUtils;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.retrofit_res_client.RetroApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by disciplemarc on 3/6/18.
 */

public class GetItemsIteratorModelImp implements GetItemsIteratorModel {

    private OnFinishListener listener;

    @Override
    public void getItems(OnFinishListener listener, int startVal, int offsetVal) {
        SchoolsLog.d("getItems() is called startVal: "+startVal +" and offsetVal: "+offsetVal);
        this.listener = listener;
        loadData(startVal,offsetVal);
    }

    private void loadData(int startVal, int offsetVal) {
        SchoolsLog.d("loadData() is called startVal: "+startVal +" and offsetVal: "+offsetVal);

        // initialize an instance of the RetroApiInterface interface
        RetroApiInterface apiInterface = ApiUtils.getRetroApiClient();
        // Retrofit helps us to parse the JSON response to a list of Java objects
        Call<List<Schools>> call = apiInterface.getSchools(ApiUtils.API_KEY, startVal,offsetVal);
        SchoolsLog.d("loadData() is called");
        call.enqueue(new Callback<List<Schools>>() {
            @Override
            public void onResponse(@NonNull Call<List<Schools>> call, @NonNull Response<List<Schools>> response) {
                SchoolsLog.d("Successfully loaded the requested data ");
                List<Schools> list = response.body();
                new Handler().post(() -> {
                    SchoolsLog.d("Data send to Presenter");
                    listener.onFinishListener(list);
                });

            }

            @Override
            public void onFailure(Call<List<Schools>> call, Throwable t) {
                SchoolsLog.d("onFailure: "+ t.toString());
            }
        });
    }
}
