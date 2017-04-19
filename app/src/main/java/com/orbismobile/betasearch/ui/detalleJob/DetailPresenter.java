package com.orbismobile.betasearch.ui.detalleJob;

import com.orbismobile.betasearch.model.response.JobSearchResponse;
import com.orbismobile.betasearch.utils.Presenter;

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

    @Override
    public void attachedView(DetailView view) {
        this.detailView = view;
        detailInteractor = new DetailInteractor();
    }

    @Override
    public void detachView() {
        detailView = null;
    }

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
}
