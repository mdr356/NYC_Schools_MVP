package com.trinityempire.a20180306_mdr_nycschools.view_details.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by disciplemarc on 3/7/18.
 * @SerializedName annotation is needed for Gson to map the JSON keys with our fields
 */

public class SchoolSATscore implements Serializable {
    @SerializedName("dbn")
    private String dbn;
    @SerializedName("num_of_sat_test_takers")
    private String num_of_sat_test_takers;
    @SerializedName("sat_critical_reading_avg_score")
    private String sat_critical_reading_avg_score;
    @SerializedName("sat_math_avg_score")
    private String sat_math_avg_score;
    @SerializedName("sat_writing_avg_score")
    private String sat_writing_avg_score;
    @SerializedName("school_name")
    private String school_name;

    public SchoolSATscore(String dbn, String num_of_sat_test_takers,
                          String sat_critical_reading_avg_score,
                          String sat_math_avg_score, String sat_writing_avg_score,
                          String school_name) {
        this.dbn = dbn;
        this.num_of_sat_test_takers = num_of_sat_test_takers;
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
        this.sat_math_avg_score = sat_math_avg_score;
        this.sat_writing_avg_score = sat_writing_avg_score;
        this.school_name = school_name;
    }


    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getNum_of_sat_test_takers() {
        return num_of_sat_test_takers;
    }

    public void setNum_of_sat_test_takers(String num_of_sat_test_takers) {
        this.num_of_sat_test_takers = num_of_sat_test_takers;
    }

    public String getSat_critical_reading_avg_score() {
        return sat_critical_reading_avg_score;
    }

    public void setSat_critical_reading_avg_score(String sat_critical_reading_avg_score) {
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
    }

    public String getSat_math_avg_score() {
        return sat_math_avg_score;
    }

    public void setSat_math_avg_score(String sat_math_avg_score) {
        this.sat_math_avg_score = sat_math_avg_score;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSat_writing_avg_score() {
        return sat_writing_avg_score;
    }

    public void setSat_writing_avg_score(String sat_writing_avg_score) {
        this.sat_writing_avg_score = sat_writing_avg_score;
    }
}
