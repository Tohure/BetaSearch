package com.orbismobile.betasearch.model;

/**
 * Created by crhto on 16/04/2017.
 */

public class Job {

    private int id;
    private String title;
    private String description;
    private String company;
    private String date;
    private String added_on;
    private String place;
    private String keywords;
    private String salary;
    private String status;

    public Job(int id, String title, String description, String company, String date, String added_on, String place, String keywords, String salary, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.date = date;
        this.added_on = added_on;
        this.place = place;
        this.keywords = keywords;
        this.salary = salary;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdded_on() {
        return added_on;
    }

    public void setAdded_on(String added_on) {
        this.added_on = added_on;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
