package com.trinityempire.a20180306_mdr_nycschools.view_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.trinityempire.a20180306_mdr_nycschools.R;
import com.trinityempire.a20180306_mdr_nycschools.database.DatabaseHandler;
import com.trinityempire.a20180306_mdr_nycschools.view_details.model.GetSATItemsIteratorModelImp;
import com.trinityempire.a20180306_mdr_nycschools.view_details.model.hmKeys;
import com.trinityempire.a20180306_mdr_nycschools.view_details.presenter.PresenterDetails;
import com.trinityempire.a20180306_mdr_nycschools.view_details.presenter.PresenterDetailsImp;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;
import com.trinityempire.a20180306_mdr_nycschools.view_favorite.FavoriteActivity;

import java.util.HashMap;

/**
 * Created by disciplemarc on 3/6/18.
 */

public class SchoolsDetailsActivity extends AppCompatActivity implements SchoolDetailsView, OnMapReadyCallback {

    private TextView tv_performance_text;
    private TextView tv_overview;
    private TextView tv_eligibility;
    private TextView tv_academics_text;
    private TextView tv_activities_text;
    private TextView tv_admission_text;
    private TextView tv_location;
    private TextView tv_transportion;
    private ProgressBar progressBar;
    private String schoolName;
    private Double longitude, latitude;
    private ImageButton favorite_button;
    @Override
    public void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.activity_details);
        SchoolsLog.d("onCreate() in Details Activity");

        initView();
    }
    @Override
    public void onStart() {
        super.onStart();
        SchoolsLog.d("onStart() in Details Activity");

        loadData();
    }
    @Override
    public void onResume() {
        super.onResume();

        SchoolsLog.d("onResume() in Details Activity");
        initGoogleMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SchoolsLog.d("onCreateOptionsMenu() is called");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.favorite) {
            Intent intent = new Intent(SchoolsDetailsActivity.this, FavoriteActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initGoogleMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initView() {
        SchoolsLog.d("initView() initialize the views");

        tv_overview = (TextView) findViewById(R.id.overview);
        tv_eligibility = (TextView) findViewById(R.id.eligibility);
        tv_academics_text = (TextView) findViewById(R.id.academics_text);
        tv_activities_text = (TextView) findViewById(R.id.activities_text);
        tv_admission_text = (TextView) findViewById(R.id.admission_text);
        tv_location = (TextView) findViewById(R.id.location);
        tv_transportion = (TextView) findViewById(R.id.transportation);
        tv_performance_text = (TextView) findViewById(R.id.performance_text);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_details);
        favorite_button = (ImageButton) findViewById(R.id.favorite_button);

    }

    @Override
    public void setItems(HashMap<String, String> hm) {
        SchoolsLog.d("setItems for schools details");

        schoolName = hm.get(hmKeys.school_name);
        tv_overview.setText(hm.get(hmKeys.overview_paragraph));
        tv_eligibility.setText(Html.fromHtml(hm.get(hmKeys.eligibility1)));
        tv_academics_text.setText(Html.fromHtml(hm.get(hmKeys.academicopportunities1)));
        tv_activities_text.setText(hm.get(hmKeys.school_sports));
        tv_admission_text.setText(Html.fromHtml(hm.get(hmKeys.admissionspriority11)));
        longitude = Double.parseDouble(hm.get(hmKeys.longitude));
        latitude = Double.parseDouble(hm.get(hmKeys.latitude));
        tv_location.setText(Html.fromHtml(hm.get(hmKeys.location)));
        tv_transportion.setText(Html.fromHtml(hm.get(hmKeys.bus)));
        setTitle(schoolName);

    }

    @Override
    public void setUpFavOn(Boolean isOn) {
        SchoolsLog.d("setUpFavOn() is called to setup favorite button ui");
        if (isOn) {
            favorite_button.setBackground(getResources().getDrawable(R.drawable.favorite_on));
        } else {
            favorite_button.setBackground(getResources().getDrawable(R.drawable.favorite_off));
        }
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        SchoolsLog.d("onMapReady() setting up map");
        LatLng loc = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(loc);
        markerOptions.title(schoolName);
        googleMap.addMarker(markerOptions);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(loc.latitude, loc.longitude), 16.0f));

    }

    public void AddFavorite(View view) {
        SchoolsLog.d("AddFavorite() passing data to database");

        DatabaseHandler db = new DatabaseHandler(this);
        try {
            db.insertData((Schools) getIntent().getSerializableExtra("SCHOOL_OBJ"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        Toast.makeText(this, "School is saved in Favorite", Toast.LENGTH_SHORT).show();

    }

    private void loadData() {
        SchoolsLog.d("loadData() is called, to pass data to presenter.");

        Schools data =  (Schools) getIntent().getSerializableExtra("SCHOOL_OBJ");
        PresenterDetails presenter = new PresenterDetailsImp(this, new GetSATItemsIteratorModelImp(),schoolName);
        presenter.onSetupDataHash(data);
        presenter.onFindSchoolSAT(data.getDbn());
        presenter.favOn(this,schoolName);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(String data) {
        SchoolsLog.d("setItems from SATs info");
        if (!data.isEmpty()) {
            tv_performance_text.setText(Html.fromHtml(data));
        }

        SchoolsLog.d("performace Data is empty");
    }


}