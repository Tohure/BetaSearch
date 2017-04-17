package com.orbismobile.betasearch.ui.SearchScreen;

import com.orbismobile.betasearch.model.Job;

import java.util.List;

/**
 * Created by crhto on 16/04/2017.
 */

public interface SearchView {

    void getJobsDone(List<Job> jobs);

    void getJobsFail(String message);

    void getJobsError(String message);

    void showProgress();

    void hideProgress();
}
