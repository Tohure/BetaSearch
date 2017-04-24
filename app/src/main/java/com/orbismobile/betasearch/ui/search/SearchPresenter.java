package com.orbismobile.betasearch.ui.search;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.orbismobile.betasearch.model.response.JobSearchResponse;
import com.orbismobile.betasearch.utils.Presenter;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.util.List;

/**
 * Created by tohure on 17/04/17.
 */

public class SearchPresenter implements Presenter<SearchView>, SearchCallback {

    private SearchView searchView;
    private SearchInteractor searchInteractor;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public int getId(String query, String location) {
        return searchInteractor.getIdQuery(query,location);
    }

    public void getJobsSearch(String query, String location) {
        searchView.showProgress();
        searchInteractor.listJobs(query,location, this);
    }

    public boolean getStatusAlarm(int idLastSearch) {
        return searchInteractor.getStatusAlarm(idLastSearch);
    }

    public void setAlarm(int idLastSearch, boolean b) {
        searchInteractor.setAlarm(idLastSearch,b);
    }

    public void saveQueryinDB(String query, String location) {
        searchInteractor.saveQueryInDB(query,location,this);
    }

    @Override
    public void listJobsSuccess(List<JobSearchResponse.DataBean> jobs) {
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
    public void saveSearchDone(int id) {
        searchView.saveQueryDone(id);
    }

    @Override
    public void saveSearchFail() {

    }

    @Override
    public void attachedView(SearchView view, Context context) {
        this.searchView = view;
        this.mContext = context;
        searchInteractor = new SearchInteractor(getHelper());
    }

    @Override
    public void detachView() {
        searchView = null;
        quitHelper();
    }

    private DatabaseHelper getHelper() {
        if (dbHelper == null) {dbHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);}
        return dbHelper;
    }

    private void quitHelper() {
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }
}
