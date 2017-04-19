package com.orbismobile.betasearch.ui.search;

import com.orbismobile.betasearch.model.response.JobSearchResponse;

import java.util.List;

/**
 * Created by crhto on 16/04/2017.
 */

public interface SearchView {

    void listJobsDone(List<JobSearchResponse.DataBean> jobs);

    void listJobsFail(String message);

    void listJobsError(String message);

    void showProgress();

    void hideProgress();
}
