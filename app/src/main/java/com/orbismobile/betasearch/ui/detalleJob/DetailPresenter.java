package com.orbismobile.betasearch.ui.detalleJob;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.orbismobile.betasearch.model.response.JobSearchResponse;
import com.orbismobile.betasearch.utils.Presenter;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.util.List;

/**
 * Created by crhto on 18/04/2017.
 */

public class DetailPresenter implements Presenter<DetailView>, DetailCallback {

    private DetailView detailView;
    private DetailInteractor detailInteractor;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public boolean getStatusJobApply(int id) {
        return detailInteractor.getStatusJobApply(id);
    }

    public void applyJob(int idJob, String title, String date, String place) {
        detailView.showProgress();
        detailInteractor.applyJob(idJob,title,date,place,this);
    }

    public void getJobDetail(String idjob) {
        detailView.showProgress();
        detailInteractor.detailJob(idjob,this);
    }

    public void getJobsRecomend(String tag,String idjob) {
        detailView.showProgress();
        detailInteractor.recomJob(tag,idjob,this);
    }

    @Override
    public void attachedView(DetailView view, Context context) {
        this.detailView = view;
        this.mContext = context;
        detailInteractor = new DetailInteractor(getHelper());
    }

    @Override
    public void detachView() {
        detailView = null;
        quitHelper();
    }

    //<editor-fold desc="DETAIL FUNCTIONS">
    @Override
    public void detailJobsSuccess(JobSearchResponse.DataBean job) {
        detailView.detailJobsDone(job);
        detailView.hideProgress();
    }

    @Override
    public void detailJobsError(String message) {
        detailView.hideProgress();
    }

    @Override
    public void detailJobsServerError(String message) {
        detailView.hideProgress();
    }
    //</editor-fold>

    //<editor-fold desc="RECOMENDS FUNCTIONS">
    @Override
    public void recoJobsSuccess(List<JobSearchResponse.DataBean> jobs) {
        detailView.recoJobsDone(jobs);
        detailView.hideProgress();
    }

    @Override
    public void recoJobsError(String message) {
        detailView.hideProgress();
    }

    @Override
    public void recoJobsServerError(String message) {
        detailView.hideProgress();
    }
    //</editor-fold>

    @Override
    public void applyJobError(String message) {
        detailView.applyJobFail();
        detailView.hideProgress();
    }

    @Override
    public void applyJobSucces() {
        detailView.applyJobDone();
        detailView.hideProgress();
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
