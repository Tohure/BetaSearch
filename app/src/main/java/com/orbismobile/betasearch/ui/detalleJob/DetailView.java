package com.orbismobile.betasearch.ui.detalleJob;

import com.orbismobile.betasearch.model.response.JobSearchResponse;

import java.util.List;

/**
 * Created by crhto on 18/04/2017.
 */

public interface DetailView {

    void detailJobsDone(JobSearchResponse.DataBean job);

    void detailJobsFail(String message);

    void detailJobsError(String message);

    void showProgress();

    void hideProgress();
}
