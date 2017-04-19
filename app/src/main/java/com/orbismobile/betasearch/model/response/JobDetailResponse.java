package com.orbismobile.betasearch.model.response;

/**
 * Created by crhto on 18/04/2017.
 */

public class JobDetailResponse {
    private int error;
    private JobSearchResponse.DataBean data;

    public JobDetailResponse(int error, JobSearchResponse.DataBean data) {
        this.error = error;
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public JobSearchResponse.DataBean getData() {
        return data;
    }

    public void setData(JobSearchResponse.DataBean data) {
        this.data = data;
    }
}
