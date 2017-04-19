package com.orbismobile.betasearch.ui.search;

import com.orbismobile.betasearch.api.ApiManager;
import com.orbismobile.betasearch.model.response.JobSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tohure on 17/04/17.
 */

public class SearchInteractor {

    public void listJobs(String query, String location, final SearchCallback callback){

        Call<JobSearchResponse> call = ApiManager.apiManager().getJobsSearch(query,location);
        call.enqueue(new Callback<JobSearchResponse>() {
            @Override
            public void onResponse(Call<JobSearchResponse> call, Response<JobSearchResponse> response) {
                if(response.isSuccessful()){
                    JobSearchResponse jobSearchResponse = response.body();
                    List<JobSearchResponse.DataBean> jobList = jobSearchResponse.getData();
                    callback.listJobsSuccess(jobList);
                }else{
                    callback.listJobsError(response.message());
                }
            }
            @Override
            public void onFailure(Call<JobSearchResponse> call, Throwable t) {
                callback.listJobsServerError(t.getMessage());
            }
        });

    }


}
