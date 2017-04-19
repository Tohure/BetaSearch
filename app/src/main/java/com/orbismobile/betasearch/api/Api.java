package com.orbismobile.betasearch.api;

import com.orbismobile.betasearch.model.request.JobsRequest;
import com.orbismobile.betasearch.model.response.JobDetailResponse;
import com.orbismobile.betasearch.model.response.JobSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by crhto on 16/04/2017.
 */

public interface Api {

    @GET("jobs")
    Call<JobSearchResponse> getJobs(JobsRequest jobsRequest);

    @GET("jobs/search")
    Call<JobSearchResponse> getJobsSearch(@Query("query") String query, @Query("location") String location);


    @GET("jobs/detail")
    Call<JobDetailResponse> getJobsDetail(@Query("idjob") String idjob);
}
