package com.trinityempire.a20180306_mdr_nycschools.view_details;

import java.util.HashMap;

/**
 * Created by disciplemarc on 3/6/18.
 */

public interface SchoolDetailsView {
    void showProgress();
    void setItems(String data);
    void setItems(HashMap<String,String> items);
    void setUpFavOn(Boolean isOn);
    void hideProgress();

}
