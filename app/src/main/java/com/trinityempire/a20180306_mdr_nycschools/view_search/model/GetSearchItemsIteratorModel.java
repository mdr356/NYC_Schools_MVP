package com.trinityempire.a20180306_mdr_nycschools.view_search.model;

import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.List;

/**
 * Created by disciplemarc on 3/6/18.
 */

public interface GetSearchItemsIteratorModel {

    interface OnFinishListener{
        void onFinishListener(List<Schools> items);
    }
    void getItems(OnFinishListener listener, String location, int startVal, int offsetVal);
}
