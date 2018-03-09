package com.trinityempire.a20180306_mdr_nycschools.view_favorite;

import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.List;

/**
 * Created by disciplemarc on 3/7/18.
 */

public interface FavView {
    void showProgress();
    void setItems(List<Schools> items);
    void hideProgress();
}
