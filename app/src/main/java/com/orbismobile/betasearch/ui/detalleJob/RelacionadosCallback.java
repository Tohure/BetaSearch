package com.orbismobile.betasearch.ui.detalleJob;

import com.orbismobile.betasearch.model.response.JobSearchResponse;

import java.util.List;

/**
 * Created by crhto on 19/04/2017.
 */

public interface RelacionadosCallback {
    void relJobsSuccess(List<JobSearchResponse.DataBean> jobs);
    void relJobsError(String message);
    void relJobsServerError(String message);
}
