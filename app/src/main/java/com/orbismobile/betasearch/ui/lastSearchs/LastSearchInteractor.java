package com.orbismobile.betasearch.ui.lastSearchs;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.orbismobile.betasearch.model.db.LastSearch;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.util.List;

/**
 * Created by tohure on 20/04/17.
 */

public class LastSearchInteractor {

    private RuntimeExceptionDao<LastSearch, Integer> daoLastSearch;

    public LastSearchInteractor(DatabaseHelper dbHelper) {
        this.daoLastSearch = dbHelper.getLastSearchRuntimeDAO();
    }

    public void listLastSearchs(final LastSearchCallback lastSearchCallback) {
        List<LastSearch> lastSearchList;
        try {
            lastSearchList = daoLastSearch.queryForAll();
            lastSearchCallback.listLastSearchsSuccess(lastSearchList);
        } catch (Exception e) {
            lastSearchCallback.listLastSearchsError(e.getMessage());
        }
    }

    public void deleteSearch(Integer idLastSearch,final LastSearchCallback lastSearchCallback) {
        try {
            daoLastSearch.deleteById(idLastSearch);
            lastSearchCallback.deleteItemLastSucces();
        } catch (Exception e) {
            e.printStackTrace();
            lastSearchCallback.deleteItemLastError(e.getMessage());
        }
    }
}