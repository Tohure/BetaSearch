package com.orbismobile.betasearch.ui.search;

import com.orbismobile.betasearch.model.response.JobsResponse;
import com.orbismobile.betasearch.utils.Presenter;

import java.util.List;

/**
 * Created by tohure on 17/04/17.
 */

public class SearchPresenter implements Presenter<SearchView>, SearchCallback {

    private SearchView searchView;
    private SearchInteractor searchInteractor;

    public void getJobsSearch(String query, String location) {
        searchView.showProgress();
        searchInteractor.listJobs(query,location, this);
    }


    @Override
    public void listJobsSuccess(List<JobsResponse.DataBean> jobs) {
        searchView.listJobsDone(jobs);
        searchView.hideProgress();
    }

    @Override
    public void listJobsError(String message) {
        searchView.hideProgress();
    }

    @Override
    public void listJobsServerError(String message) {
        searchView.hideProgress();
    }

    @Override
    public void attachedView(SearchView view) {
        this.searchView = view;
        searchInteractor = new SearchInteractor();
    }

    @Override
    public void detachView() {
        searchView = null;
    }
}
