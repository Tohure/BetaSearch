package com.orbismobile.betasearch.ui.lastSearchs;

import com.orbismobile.betasearch.model.db.LastSearch;

import java.util.List;

/**
 * Created by tohure on 20/04/17.
 */

public interface LastSearchView {
    void listLastSearchsDone(List<LastSearch> listLastSearchs);

    void listLastSearchsFail(String message);

    void showProgress();

    void hideProgress();
}
