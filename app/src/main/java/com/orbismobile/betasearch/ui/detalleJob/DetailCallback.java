package com.orbismobile.betasearch.ui.detalleJob;

import com.orbismobile.betasearch.model.response.JobSearchResponse;

import java.util.List;

/**
 * Created by crhto on 18/04/2017.
 */

public interface DetailCallback {
    //<editor-fold desc="DETAIL">
    void detailJobsSuccess(JobSearchResponse.DataBean job);
    void detailJobsError(String message);
    void detailJobsServerError(String message);
    //</editor-fold>

    //<editor-fold desc="RECOMEND">
    void recoJobsSuccess(List<JobSearchResponse.DataBean> jobs);
    void recoJobsError(String message);
    void recoJobsServerError(String message);
    //</editor-fold>

    void applyJobError(String message);
    void applyJobSucces();

}