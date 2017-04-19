package com.orbismobile.betasearch.ui.detalleJob;

import com.orbismobile.betasearch.model.response.JobSearchResponse;

/**
 * Created by crhto on 18/04/2017.
 */

public interface DetailCallback {
    void detailJobsSuccess(JobSearchResponse.DataBean job);
    void detailJobsError(String message);
    void detailJobsServerError(String message);
}
