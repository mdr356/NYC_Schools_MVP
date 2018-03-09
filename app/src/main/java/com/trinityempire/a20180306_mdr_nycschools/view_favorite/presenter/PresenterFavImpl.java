package com.trinityempire.a20180306_mdr_nycschools.view_favorite.presenter;

import android.content.Context;

import com.trinityempire.a20180306_mdr_nycschools.view_favorite.model.GetFavItemsIteratorModel;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;
import com.trinityempire.a20180306_mdr_nycschools.view_favorite.FavView;

import java.util.List;

/**
 * Created by disciplemarc on 3/7/18.
 */

public class PresenterFavImpl implements PresenterFav, GetFavItemsIteratorModel.OnFinishListener {

    private GetFavItemsIteratorModel getFavItemsIteratorModel;
    private FavView favView;
    private Context context;
    public PresenterFavImpl(FavView favView, GetFavItemsIteratorModel getFavItemsIteratorModel, Context context) {
        this.getFavItemsIteratorModel = getFavItemsIteratorModel;
        this.favView = favView;
        this.context = context;
    }

    @Override
    public void onFindFav() {
        if (favView != null) {
            favView.showProgress();
        }

        getFavItemsIteratorModel.getItems(this,context);
    }

    @Override
    public void onFinishListener(List<Schools> items) {
        if (favView != null) {
            favView.setItems(items);
            favView.hideProgress();
        }
    }
}
