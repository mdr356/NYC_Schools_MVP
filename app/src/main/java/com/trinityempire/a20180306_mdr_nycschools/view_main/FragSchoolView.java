package com.trinityempire.a20180306_mdr_nycschools.view_main;

import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.List;

/**
 * Created by disciplemarc on 3/6/18.
 */

public interface FragSchoolView {
    void showProgress();
    void setItems(List<Schools> items);
    void hideProgress();
}
