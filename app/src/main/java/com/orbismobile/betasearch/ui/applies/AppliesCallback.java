package com.orbismobile.betasearch.ui.applies;

import com.orbismobile.betasearch.model.db.ApplyJob;

import java.util.List;

/**
 * Created by crhto on 23/04/2017.
 */

public interface AppliesCallback {
    void listAppliesSuccess(List<ApplyJob> applyJobList);
    void listAppliesError(String message);
}
