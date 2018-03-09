package com.trinityempire.a20180306_mdr_nycschools.view_search;

import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.List;

/**
 * Created by disciplemarc on 3/9/18.
 */

public interface SearchView {
    void showProgress();
    void setSearchItems(List<Schools> items);
    void hideProgress();
}
