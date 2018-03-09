package com.trinityempire.a20180306_mdr_nycschools.view_favorite.model;

import android.content.Context;

import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.List;

/**
 * Created by disciplemarc on 3/7/18.
 */

public interface GetFavItemsIteratorModel {
    interface OnFinishListener  {
        void onFinishListener(List<Schools> items);
    }
    void getItems(OnFinishListener listener, Context context);
}
