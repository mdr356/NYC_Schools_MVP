package com.trinityempire.a20180306_mdr_nycschools.view_main.model;

import java.util.List;

/**
 * Created by disciplemarc on 3/6/18.
 */

public interface GetItemsIteratorModel {

    interface OnFinishListener{
        void onFinishListener(List<Schools> items);
    }
    void getItems(OnFinishListener listener, int startVal, int offsetVal);
}
