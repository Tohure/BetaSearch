package com.orbismobile.betasearch.ui.lastSearchs;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.orbismobile.betasearch.model.db.LastSearch;
import com.orbismobile.betasearch.utils.Presenter;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.util.List;

/**
 * Created by tohure on 20/04/17.
 */

public class LastSearchPresenter implements Presenter<LastSearchView>, LastSearchCallback{

    private LastSearchView lastSearchView;
    private LastSearchInteractor lastSearchInteractor;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public void getLastSearchs() {
        lastSearchView.showProgress();
        lastSearchInteractor.listLastSearchs(this);
    }

    public void deleteLastSearch(Integer idLastSearch) {
        lastSearchView.showProgress();
        lastSearchInteractor.deleteSearch(idLastSearch,this);
    }

    @Override
    public void attachedView(LastSearchView view, Context context) {
        this.lastSearchView = view;
        this.mContext = context;
        lastSearchInteractor = new LastSearchInteractor(getHelper());
    }

    @Override
    public void listLastSearchsSuccess(List<LastSearch> lastSearchList) {
        lastSearchView.listLastSearchsDone(lastSearchList);
        lastSearchView.hideProgress();
    }

    @Override
    public void listLastSearchsError(String message) {
        lastSearchView.listLastSearchsFail(message);
        lastSearchView.hideProgress();
    }

    @Override
    public void deleteItemLastSucces() {
        lastSearchView.deleteDoneSearch();
        lastSearchView.hideProgress();
    }

    @Override
    public void deleteItemLastError(String message) {
        lastSearchView.deleteFailSearch(message);
        lastSearchView.hideProgress();
    }

    @Override
    public void detachView() {
        lastSearchView = null;
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
