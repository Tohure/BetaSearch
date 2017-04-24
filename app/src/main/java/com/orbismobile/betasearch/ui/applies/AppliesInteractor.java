package com.orbismobile.betasearch.ui.applies;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.orbismobile.betasearch.model.db.ApplyJob;
import com.orbismobile.betasearch.utils.db.DatabaseHelper;

import java.util.List;

/**
 * Created by crhto on 23/04/2017.
 */

public class AppliesInteractor {

    private RuntimeExceptionDao<ApplyJob, Integer> daoApplies;

    public AppliesInteractor(DatabaseHelper dbHelper) {
        this.daoApplies = dbHelper.getApplyJobRuntimeDAO();
    }

    public void listApplies(final AppliesCallback callback) {
        List<ApplyJob> listApplies;
        try {
            listApplies = daoApplies.queryForAll();
            callback.listAppliesSuccess(listApplies);
        } catch (Exception e) {
            callback.listAppliesError(e.getMessage());
        }
    }
}
