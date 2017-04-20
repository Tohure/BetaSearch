package com.orbismobile.betasearch.ui.search;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.orbismobile.betasearch.model.db.LastSearch;
import com.orbismobile.betasearch.model.response.JobSearchResponse;
import com.orbismobile.betasearch.utils.Presenter;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by tohure on 17/04/17.
 */

public class SearchPresenter implements Presenter<SearchView>, SearchCallback {

    private SearchView searchView;
    private SearchInteractor searchInteractor;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public void getJobsSearch(String query, String location) {
        searchView.showProgress();
        searchInteractor.listJobs(query,location, this);
    }

    public void saveQueryinDB(String query, String location) {

        String f_query, f_location;

        f_query = query.trim().toLowerCase();
        f_location = location.trim().toLowerCase();

        RuntimeExceptionDao<LastSearch,Integer> daoLastSearch = getHelper().getLastSearchRuntimeDAO();

        int total_searhs = 0;

        try {
            List<LastSearch> lastSearchList = daoLastSearch.queryBuilder()
                    .where()
                    .eq(LastSearch.C_QUERY, f_query)
                    .and()
                    .eq(LastSearch.C_PLACE, f_location)
                    .query();
            total_searhs = lastSearchList.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (total_searhs == 0){
            LastSearch newSearchQuery = new LastSearch(f_query,f_location,false,false);
            daoLastSearch.create(newSearchQuery);
        }

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
    public void attachedView(SearchView view, Context context) {
        this.searchView = view;
        this.mContext = context;
        searchInteractor = new SearchInteractor();
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
