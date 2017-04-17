package com.orbismobile.betasearch.model.request;

/**
 * Created by tohure on 17/04/17.
 */

public class JobsRequest {
    String query;

    public JobsRequest(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
