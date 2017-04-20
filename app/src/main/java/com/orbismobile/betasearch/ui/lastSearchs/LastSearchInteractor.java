package com.orbismobile.betasearch.ui.lastSearchs;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.orbismobile.betasearch.model.db.LastSearch;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.util.List;

/**
 * Created by tohure on 20/04/17.
 */

public class LastSearchInteractor {

    private DatabaseHelper dbHelper;

    public LastSearchInteractor(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void listLastSearchs(final LastSearchCallback lastSearchCallback) {
        RuntimeExceptionDao<LastSearch, Integer> daoLastSearch = dbHelper.getLastSearchRuntimeDAO();
        List<LastSearch> lastSearchList = null;
        try {
            lastSearchList = daoLastSearch.queryForAll();
            lastSearchCallback.listLastSearchsSuccess(lastSearchList);
        } catch (Exception e) {
            lastSearchCallback.listLastSearchsError(e.getMessage());
        }


    }

}
