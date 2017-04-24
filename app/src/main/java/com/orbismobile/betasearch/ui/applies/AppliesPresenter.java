package com.orbismobile.betasearch.ui.applies;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.orbismobile.betasearch.model.db.ApplyJob;
import com.orbismobile.betasearch.utils.Presenter;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.util.List;

/**
 * Created by crhto on 23/04/2017.
 */

public class AppliesPresenter implements Presenter<AppliesView>, AppliesCallback{

    private AppliesView appliesView;
    private AppliesInteractor appliesInteractor;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public void getApplies() {
        appliesView.showProgress();
        appliesInteractor.listApplies(this);
    }

    @Override
    public void attachedView(AppliesView view, Context context) {
        this.appliesView = view;
        this.mContext = context;
        appliesInteractor = new AppliesInteractor(getHelper());
    }

    @Override
    public void listAppliesSuccess(List<ApplyJob> applyJobList) {
        appliesView.listAppliesDone(applyJobList);
        appliesView.hideProgress();
    }

    @Override
    public void listAppliesError(String message) {

    }

    @Override
    public void detachView() {
        appliesView = null;
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
