package com.trinityempire.a20180306_mdr_nycschools.view_details.model;

import android.content.Context;

import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.HashMap;

/**
 * Created by disciplemarc on 3/7/18.
 */

public interface GetSATItemsIteratorModel {
    interface OnFinishListener {
        void onFinishListener(String data);
        void onFinishListener(HashMap<String, String> item);
        void onFinisheListener(Boolean isFavOn);
    }
    void getItems(OnFinishListener listener, String dbn);
    void setUpItems(OnFinishListener listener, Schools data);
    void getIfFavOnItem(OnFinishListener listener, Context context, String school_name);
}
