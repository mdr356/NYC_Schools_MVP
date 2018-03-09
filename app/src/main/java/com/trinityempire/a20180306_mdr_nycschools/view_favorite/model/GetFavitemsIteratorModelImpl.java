package com.trinityempire.a20180306_mdr_nycschools.view_favorite.model;

import android.content.Context;
import android.os.Handler;

import com.trinityempire.a20180306_mdr_nycschools.database.DatabaseHandler;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.List;

/**
 * Created by disciplemarc on 3/7/18.
 */

public class GetFavitemsIteratorModelImpl implements GetFavItemsIteratorModel {

    @Override
    public void getItems(OnFinishListener listener, Context context) {
        DatabaseHandler db = new DatabaseHandler(context);
        List<Schools> list = db.getAllData();
        db.close();
        new Handler().post(() -> listener.onFinishListener(list));
    }
}
