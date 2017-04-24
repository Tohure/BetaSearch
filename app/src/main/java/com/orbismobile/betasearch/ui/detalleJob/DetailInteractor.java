package com.orbismobile.betasearch.ui.detalleJob;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.orbismobile.betasearch.api.ApiManager;
import com.orbismobile.betasearch.model.db.ApplyJob;
import com.orbismobile.betasearch.model.response.JobDetailResponse;
import com.orbismobile.betasearch.model.response.JobSearchResponse;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by crhto on 18/04/2017.
 */

public class DetailInteractor {

    private RuntimeExceptionDao<ApplyJob, Integer> daoApplyJob;

    public DetailInteractor(DatabaseHelper dbHelper) {
        this.daoApplyJob = dbHelper.getApplyJobRuntimeDAO();
    }

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

    public void recomJob(String tags, String id, final DetailCallback callback){

        Call<JobSearchResponse> call = ApiManager.apiManager().getJobsRecom(tags,id);
        call.enqueue(new Callback<JobSearchResponse>() {
            @Override
            public void onResponse(Call<JobSearchResponse> call, Response<JobSearchResponse> response) {
                if(response.isSuccessful()){
                    JobSearchResponse jobDetailResponse = response.body();
                    callback.recoJobsSuccess(jobDetailResponse.getData());
                }else{
                    callback.recoJobsError(response.message());
                }
            }
            @Override
            public void onFailure(Call<JobSearchResponse> call, Throwable t) {
                callback.recoJobsServerError(t.getMessage());
            }
        });
    }

    public void applyJob(int idJob, String title, String date, String place, final DetailCallback callback) {
        try {
            ApplyJob applyJob = new ApplyJob(idJob,title,date,place,true);
            daoApplyJob.create(applyJob);
            callback.applyJobSucces();
        } catch (Exception e) {
            e.printStackTrace();
            callback.applyJobError(e.getMessage());
        }
    }

    public boolean getStatusJobApply(int id) {
        List<ApplyJob> applyJobs = null;

        try {
            applyJobs = daoApplyJob.queryBuilder()
                    .where()
                    .eq(ApplyJob.C_IDJOB, id)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (applyJobs != null && applyJobs.size() > 0){
            return applyJobs.get(0).getStatusApply();
        }else{
            return false;
        }
    }
}