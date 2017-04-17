package com.orbismobile.betasearch.utils;

/**
 * Created by tohure on 17/04/17.
 */

public interface Presenter<V>{

    void attachedView(V view);

    void detachView();
}

