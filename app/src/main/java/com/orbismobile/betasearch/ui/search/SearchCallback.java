package com.orbismobile.betasearch.ui.search;

import com.orbismobile.betasearch.model.response.JobsResponse;

import java.util.List;

/**
 * Created by tohure on 17/04/17.
 */

public interface SearchCallback {

    void listJobsSuccess(List<JobsResponse.DataBean> jobs);
    void listJobsError(String message);
    void listJobsServerError(String message);

}
