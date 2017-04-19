package com.orbismobile.betasearch.ui.detalleJob;

import com.orbismobile.betasearch.api.ApiManager;
import com.orbismobile.betasearch.model.response.JobDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by crhto on 18/04/2017.
 */

public class DetailInteractor {

    public void detailJob(String idjob, final DetailCallback callback){

        Call<JobDetailResponse> call = ApiManager.apiManager().getJobsDetail(idjob);
        call.enqueue(new Callback<JobDetailResponse>() {
            @Override
            public void onResponse(Call<JobDetailResponse> call, Response<JobDetailResponse> response) {
                if(response.isSuccessful()){
                    JobDetailResponse jobDetailResponse = response.body();
                    callback.detailJobsSuccess(jobDetailResponse.getData());
                }else{
                    callback.detailJobsError(response.message());
                }
            }
            @Override
            public void onFailure(Call<JobDetailResponse> call, Throwable t) {
                callback.detailJobsServerError(t.getMessage());
            }
        });

    }
}
