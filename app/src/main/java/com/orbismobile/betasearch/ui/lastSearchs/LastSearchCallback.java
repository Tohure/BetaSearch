package com.orbismobile.betasearch.ui.lastSearchs;

import com.orbismobile.betasearch.model.db.LastSearch;

import java.util.List;

/**
 * Created by tohure on 20/04/17.
 */

public interface LastSearchCallback {
    void listLastSearchsSuccess(List<LastSearch> lastSearchList);
    void listLastSearchsError(String message);

    void deleteItemLastSucces();
    void deleteItemLastError(String message);
}
