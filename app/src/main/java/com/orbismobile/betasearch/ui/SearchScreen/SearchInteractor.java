package com.orbismobile.betasearch.ui.SearchScreen;

import com.orbismobile.betasearch.api.ApiManager;
import com.orbismobile.betasearch.model.Job;
import com.orbismobile.betasearch.model.request.JobsRequest;
import com.orbismobile.betasearch.model.response.JobsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tohure on 17/04/17.
 */

public class SearchInteractor {

    public void listJobs(String query, final SearchCallback callback){

        Call<JobsResponse> call = ApiManager.apiManager().getJobsSearch(query);
        call.enqueue(new Callback<JobsResponse>() {
            @Override
            public void onResponse(Call<JobsResponse> call, Response<JobsResponse> response) {
                if(response.isSuccessful()){
                    JobsResponse jobsResponse = response.body();
                    List<JobsResponse.DataBean> jobList = jobsResponse.getData();
                    callback.listJobsSuccess(jobList);
                }else{
                    callback.listJobsError(response.message());
                }
            }
            @Override
            public void onFailure(Call<JobsResponse> call, Throwable t) {
                callback.listJobsServerError(t.getMessage());
            }
        });

    }


}
