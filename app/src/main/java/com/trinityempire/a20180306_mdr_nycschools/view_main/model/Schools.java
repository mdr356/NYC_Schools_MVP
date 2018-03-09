package com.trinityempire.a20180306_mdr_nycschools.view_main.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by disciplemarc on 3/5/18.
 *
 * @SerializedName annotation is needed for Gson to map the JSON keys with our fields
 */

public class Schools implements Serializable{
    @SerializedName("dbn")
    private String dbn;
    @SerializedName("academicopportunities1")
    private String academicopportunities1;
    @SerializedName("academicopportunities2")
    private String academicopportunities2;
    @SerializedName("academicopportunities3")
    private String academicopportunities3;
    @SerializedName("admissionspriority11")
    private String admissionspriority11;
    @SerializedName("admissionspriority21")
    private String admissionspriority21;
    @SerializedName("admissionspriority31")
    private String admissionspriority31;
    @SerializedName("attendance_rate")
    private String attendance_rate;
    @SerializedName("bus")
    private String bus;
    @SerializedName("subway")
    private String subway;
    @SerializedName("city")
    private String  city;
    @SerializedName("ell_programs")
    private String ell_programs;
    @SerializedName("extracurricular_activities")
    private String extracurricular_activities;
    @SerializedName("phone_number")
    private String phone_number;
    @SerializedName("fax_number")
    private String fax_number;
    @SerializedName("school_email")
    private String school_email;
    @SerializedName("school_name")
    private String school_name;
    @SerializedName("finalgrades")
    private String finalgrades;
    @SerializedName("interest1")
    private String interest1;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("location")
    private String location;
    @SerializedName("neighborhood")
    private String neighborhood;
    @SerializedName("overview_paragraph")
    private String overview_paragraph;
    @SerializedName("school_sports")
    private String school_sports;
    @SerializedName("total_students")
    private String total_students;
    @SerializedName("website")
    private String website;
    @SerializedName("eligibility1")
    private String eligibility1;

    public Schools(String dbn, String academicopportunities1, String academicopportunities2,
                   String academicopportunities3, String admissionspriority11, String admissionspriority21,
                   String admissionspriority31, String attendance_rate, String bus,
                   String subway, String city, String ell_programs,
                   String extracurricular_activities, String phone_number, String fax_number,
                   String school_email, String school_name, String finalgrades, String interest1, String latitude,
                   String longitude, String location, String neighborhood, String overview_paragraph,
                   String school_sports, String total_students, String website, String eligibility1) {
        this.dbn = dbn;
        this.academicopportunities1 = academicopportunities1;
        this.academicopportunities2 = academicopportunities2;
        this.academicopportunities3 = academicopportunities3;
        this.admissionspriority11 = admissionspriority11;
        this.admissionspriority21 = admissionspriority21;
        this.admissionspriority31 = admissionspriority31;
        this.attendance_rate = attendance_rate;
        this.bus = bus;
        this.subway = subway;
        this.city = city;
        this.ell_programs = ell_programs;
        this.extracurricular_activities = extracurricular_activities;
        this.phone_number = phone_number;
        this.fax_number = fax_number;
        this.school_email = school_email;
        this.school_name = school_name;
        this.finalgrades = finalgrades;
        this.interest1 = interest1;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.neighborhood = neighborhood;
        this.overview_paragraph = overview_paragraph;
        this.school_sports = school_sports;
        this.total_students = total_students;
        this.website = website;
        this.eligibility1 = eligibility1;
    }
    public String getAcademicopportunities1() {
        return academicopportunities1;
    }

    public void setAcademicopportunities1(String academicopportunities1) {
        this.academicopportunities1 = academicopportunities1;
    }

    public String getAcademicopportunities2() {
        return academicopportunities2;
    }

    public void setAcademicopportunities2(String academicopportunities2) {
        this.academicopportunities2 = academicopportunities2;
    }

    public String getAdmissionspriority11() {
        return admissionspriority11;
    }

    public void setAdmissionspriority11(String admissionspriority11) {
        this.admissionspriority11 = admissionspriority11;
    }

    public String getAdmissionspriority21() {
        return admissionspriority21;
    }

    public void setAdmissionspriority21(String admissionspriority21) {
        this.admissionspriority21 = admissionspriority21;
    }

    public String getAdmissionspriority31() {
        return admissionspriority31;
    }

    public void setAdmissionspriority31(String admissionspriority31) {
        this.admissionspriority31 = admissionspriority31;
    }

    public String getAttendance_rate() {
        return attendance_rate;
    }

    public void setAttendance_rate(String attendance_rate) {
        this.attendance_rate = attendance_rate;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getSubway() {
        return subway;
    }

    public void setSubway(String subway) {
        this.subway = subway;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEll_programs() {
        return ell_programs;
    }

    public void setEll_programs(String ell_programs) {
        this.ell_programs = ell_programs;
    }

    public String getExtracurricular_activities() {
        return extracurricular_activities;
    }

    public void setExtracurricular_activities(String extracurricular_activities) {
        this.extracurricular_activities = extracurricular_activities;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFax_number() {
        return fax_number;
    }

    public void setFax_number(String fax_number) {
        this.fax_number = fax_number;
    }

    public String getSchool_email() {
        return school_email;
    }

    public void setSchool_email(String school_email) {
        this.school_email = school_email;
    }

    public String getFinalgrades() {
        return finalgrades;
    }

    public void setFinalgrades(String finalgrades) {
        this.finalgrades = finalgrades;
    }

    public String getInterest1() {
        return interest1;
    }

    public void setInterest1(String interest1) {
        this.interest1 = interest1;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getOverview_paragraph() {
        return overview_paragraph;
    }

    public void setOverview_paragraph(String overview_paragraph) {
        this.overview_paragraph = overview_paragraph;
    }

    public String getSchool_sports() {
        return school_sports;
    }

    public void setSchool_sports(String school_sports) {
        this.school_sports = school_sports;
    }

    public String getTotal_students() {
        return total_students;
    }

    public void setTotal_students(String total_students) {
        this.total_students = total_students;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getEligibility1() {
        return eligibility1;
    }

    public void setEligibility1(String eligibility1) {
        this.eligibility1 = eligibility1;
    }
    public String getAcademicopportunities3() {
        return academicopportunities3;
    }

    public void setAcademicopportunities3(String academicopportunities3) {
        this.academicopportunities3 = academicopportunities3;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }
}
