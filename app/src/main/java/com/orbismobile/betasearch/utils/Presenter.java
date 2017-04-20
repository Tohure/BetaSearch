package com.orbismobile.betasearch.utils;

import android.content.Context;

/**
 * Created by tohure on 17/04/17.
 */

public interface Presenter<V>{

    void attachedView(V view, Context context);

    void detachView();
}

