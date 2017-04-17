package com.orbismobile.betasearch.api;

import com.orbismobile.betasearch.model.response.JobsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by crhto on 16/04/2017.
 */

public interface Api {

    @GET("jobs")
    Call<JobsResponse> getJobs();
}
