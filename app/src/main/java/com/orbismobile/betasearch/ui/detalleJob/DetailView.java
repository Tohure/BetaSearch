package com.orbismobile.betasearch.ui.detalleJob;

import com.orbismobile.betasearch.model.response.JobSearchResponse;

import java.util.List;

/**
 * Created by crhto on 18/04/2017.
 */

public interface DetailView {

    //<editor-fold desc="DETAIL">
    void detailJobsDone(JobSearchResponse.DataBean job);

    void detailJobsFail(String message);

    void detailJobsError(String message);
    //</editor-fold>

    //<editor-fold desc="RECOMENDS">
    void recoJobsDone(List<JobSearchResponse.DataBean> jobs);

    void recoJobsFail(String message);

    void recoJobsError(String message);
    //</editor-fold>

    void showProgress();

    void hideProgress();
}
