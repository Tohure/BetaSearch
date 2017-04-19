package com.orbismobile.betasearch.ui.detalleJob;

import com.orbismobile.betasearch.model.response.JobSearchResponse;
import com.orbismobile.betasearch.utils.Presenter;

import java.util.List;

/**
 * Created by crhto on 18/04/2017.
 */

public class DetailPresenter implements Presenter<DetailView>, DetailCallback {

    private DetailView detailView;
    private DetailInteractor detailInteractor;

    public void getJobDetail(String idjob) {
        detailView.showProgress();
        detailInteractor.detailJob(idjob,this);
    }

    public void getJobsRecomend(String tag,String idjob) {
        detailView.showProgress();
        detailInteractor.recomJob(tag,idjob,this);
    }

    @Override
    public void attachedView(DetailView view) {
        this.detailView = view;
        detailInteractor = new DetailInteractor();
    }

    @Override
    public void detachView() {
        detailView = null;
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
}
