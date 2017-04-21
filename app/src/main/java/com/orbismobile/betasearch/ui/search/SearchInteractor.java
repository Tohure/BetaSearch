package com.orbismobile.betasearch.ui.search;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.orbismobile.betasearch.api.ApiManager;
import com.orbismobile.betasearch.model.db.LastSearch;
import com.orbismobile.betasearch.model.response.JobSearchResponse;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tohure on 17/04/17.
 */

public class SearchInteractor {

    RuntimeExceptionDao<LastSearch, Integer> daoLastSearch;

    public SearchInteractor(DatabaseHelper dbHelper) {
        this.daoLastSearch = dbHelper.getLastSearchRuntimeDAO();
    }


    public void listJobs(String query, String location, final SearchCallback callback){

        Call<JobSearchResponse> call = ApiManager.apiManager().getJobsSearch(query,location);
        call.enqueue(new Callback<JobSearchResponse>() {
            @Override
            public void onResponse(Call<JobSearchResponse> call, Response<JobSearchResponse> response) {
                if(response.isSuccessful()){
                    JobSearchResponse jobSearchResponse = response.body();
                    List<JobSearchResponse.DataBean> jobList = jobSearchResponse.getData();
                    callback.listJobsSuccess(jobList);
                }else{
                    callback.listJobsError(response.message());
                }
            }
            @Override
            public void onFailure(Call<JobSearchResponse> call, Throwable t) {
                callback.listJobsServerError(t.getMessage());
            }
        });

    }

    public void saveQueryInDB(String query, String location, final SearchCallback searchCallback){
        String f_query, f_location;

        f_query = query.trim().toLowerCase();
        f_location = location.trim().toLowerCase();

        int total_searhs = 0;

        List<LastSearch> lastSearchList = null;

        try {
            lastSearchList = daoLastSearch.queryBuilder()
                    .where()
                    .eq(LastSearch.C_QUERY, f_query)
                    .and()
                    .eq(LastSearch.C_PLACE, f_location)
                    .query();
            total_searhs = lastSearchList.size();
        } catch (SQLException e) {
            searchCallback.saveSearchFail();
            e.printStackTrace();
        }

        if (total_searhs == 0){
            LastSearch newSearchQuery = new LastSearch(f_query,f_location,false,false);
            daoLastSearch.create(newSearchQuery);
            searchCallback.saveSearchDone(newSearchQuery.getId());
        }else{
            searchCallback.saveSearchDone(lastSearchList.get(0).getId());
            searchCallback.saveSearchFail();
        }
    }

    public int getIdQuery(String query, String location){
        String f_query, f_location;

        f_query = query.trim().toLowerCase();
        f_location = location.trim().toLowerCase();
        List<LastSearch> lastSearchList = null;
        try {
            lastSearchList = daoLastSearch.queryBuilder()
                    .where()
                    .eq(LastSearch.C_QUERY, f_query)
                    .and()
                    .eq(LastSearch.C_PLACE, f_location)
                    .query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastSearchList.get(0).getId();
    }

    public boolean getStatusAlarm(int idLastSearch) {
        LastSearch lastSearch = daoLastSearch.queryForId(idLastSearch);
        return lastSearch.getAlerta();
    }

    public void setAlarm(int idLastSearch, boolean b) {
        LastSearch lastSearch = daoLastSearch.queryForId(idLastSearch);
        lastSearch.setAlerta(b);
        daoLastSearch.update(lastSearch);
    }

}
