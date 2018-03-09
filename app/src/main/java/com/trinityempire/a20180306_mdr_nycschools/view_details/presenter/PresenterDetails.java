package com.trinityempire.a20180306_mdr_nycschools.view_details.presenter;

import android.content.Context;

import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

/**
 * Created by disciplemarc on 3/7/18.
 */

public interface PresenterDetails {
    void onFindSchoolSAT(String dbn);
    void onSetupDataHash(Schools data);
    void favOn(Context context, String school_name);

}
