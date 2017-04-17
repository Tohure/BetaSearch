package com.orbismobile.betasearch.api;

import com.orbismobile.betasearch.model.request.JobsRequest;
import com.orbismobile.betasearch.model.response.JobsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by crhto on 16/04/2017.
 */

public interface Api {

    @GET("jobs")
    Call<JobsResponse> getJobs(JobsRequest jobsRequest);

    @GET("jobs/search")
    Call<JobsResponse> getJobsSearch(@Query("query") String query);
}
