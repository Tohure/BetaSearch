package com.orbismobile.betasearch.model.response;

import java.util.List;

/**
 * Created by crhto on 16/04/2017.
 */

public class JobSearchResponse {

    /**
     * error : 0
     * data : [{"id":1,"title":"Desarrollador Android","description":"Android Developer for New York 2012","company":"Orbis SAC","date":"2017-04-16","added_on":"2017-04-16 19:51:55","place":"Lima","keywords":"android,mobile","salary":"2500","status":"1","created_at":"2017-04-16 00:00:00","updated_at":"2017-04-16 00:00:00"},{"id":2,"title":"Desarrollador PHP","description":"Backend Developer for Peru 2013","company":"Only Sac","date":"2017-04-16","added_on":"2017-04-16 19:52:31","place":"Chiclayo","keywords":"backend,php","salary":"2700","status":"1","created_at":"2017-04-16 00:00:00","updated_at":"2017-04-16 00:00:00"}]
     */

    private int error;
    private List<DataBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : Desarrollador Android
         * description : Android Developer for New York 2012
         * company : Orbis SAC
         * date : 2017-04-16
         * added_on : 2017-04-16 19:51:55
         * place : Lima
         * keywords : android,mobile
         * salary : 2500
         * status : 1
         * created_at : 2017-04-16 00:00:00
         * updated_at : 2017-04-16 00:00:00
         */

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
        private String created_at;
        private String updated_at;

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

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
