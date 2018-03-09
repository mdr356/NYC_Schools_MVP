package com.trinityempire.a20180306_mdr_nycschools.view_search.presenter;



import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;
import com.trinityempire.a20180306_mdr_nycschools.view_search.SearchView;
import com.trinityempire.a20180306_mdr_nycschools.view_search.model.GetSearchItemsIteratorModel;

import java.util.List;

/**
 * Created by disciplemarc on 3/6/18.
 */

public class PresenterSearchImp implements PresenterSearch, GetSearchItemsIteratorModel.OnFinishListener {
    private SearchView searchView;
    private GetSearchItemsIteratorModel getSearchItemsIteratorModel;
    private String location;
    private int startVal;
    private int offsetVal;
    public PresenterSearchImp(SearchView searchView, GetSearchItemsIteratorModel getSearchItemsIteratorModel,
                              String location, int startVal, int offsetVal) {
        SchoolsLog.d("PresenterSearchImp() constructor called");
        this.searchView = searchView;
        this.getSearchItemsIteratorModel = getSearchItemsIteratorModel;
        this.location = location;
        this.startVal = startVal;
        this.offsetVal = offsetVal;
    }


    @Override
    public void onFindSchoolsLocation() {
        if (searchView != null) {
            searchView.showProgress();
        }
        getSearchItemsIteratorModel.getItems(this, location, startVal, offsetVal);
    }

    @Override
    public void onFinishListener(List<Schools> items) {
        if (searchView != null) {
            searchView.hideProgress();
            searchView.setSearchItems(items);
        }
    }
}
