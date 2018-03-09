package com.trinityempire.a20180306_mdr_nycschools;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_main.FragmentSchools;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;
import com.trinityempire.a20180306_mdr_nycschools.view_search.FragmentSearch;
import com.trinityempire.a20180306_mdr_nycschools.view_search.presenter.PresenterSearch;
import com.trinityempire.a20180306_mdr_nycschools.view_search.presenter.PresenterSearchImp;

import java.util.HashMap;

/**
 * Created by disciplemarc on 3/9/18.
 */

public class MainBaseActivity extends AppCompatActivity {
    public static String searchQuery = "";

    public void setupSearchBar() {
        FloatingSearchView mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                if (newQuery.isEmpty()) {
                    replaceSchoolFragment();
                }
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(final SearchSuggestion searchSuggestion) {
            }
            @Override
            public void onSearchAction(String query) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                int bk = query.compareToIgnoreCase("BROOKLYN");
                int q = query.compareToIgnoreCase("QUEENS");
                int m = query.compareToIgnoreCase("MANHATTAN");
                int br = query.compareToIgnoreCase("BRONX");
                int s = query.compareToIgnoreCase("STATEN ISLAND");
                String arr1[] = {"BROOKLYN", "QUEENS", "MANHATTAN","BRONX", "STATEN IS"};
                int i = 0;
                int cnt=0;
                for (String item: arr1) {
                    if (item.equalsIgnoreCase(query)) {
                        searchQuery = item;
                    }
                }
                if (searchQuery.isEmpty()) {
                    searchQuery = arr1[arr1.length-1];
                }

                searchData();

            }
        });
        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
            }

            @Override
            public void onFocusCleared() {

            }
        });
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {

            }
        });
        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {
            }
        });


    }

    private void replaceSchoolFragment() {
        SchoolsLog.d("Back to regular view is called");

        /*android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, new FragmentSchools(), "FragmentSchools");
        ft.commit();*/
        Toast.makeText(this, "To Be Implemented", Toast.LENGTH_SHORT).show();
    }

    private void searchData() {
        SchoolsLog.d("SearchView is called");
        Toast.makeText(this, "To Be Implemented", Toast.LENGTH_SHORT).show();

        /*android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeholder, new FragmentSearch(), "FragmentSearch");
        ft.addToBackStack(null);
        ft.commit();*/
    }
}
