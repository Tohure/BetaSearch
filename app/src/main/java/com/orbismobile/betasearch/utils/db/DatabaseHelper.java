package com.orbismobile.betasearch.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.orbismobile.betasearch.R;
import com.orbismobile.betasearch.model.db.LastSearch;

import java.sql.SQLException;

/**
 * Created by tohure on 20/04/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "search_beta.db";
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<LastSearch, Integer> LastSearchRuntimeDAO = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormliteconf);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, LastSearch.class);
        } catch (SQLException e) { e.printStackTrace(); }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,LastSearch.class,true);
            onCreate(database, connectionSource);
        } catch (SQLException e) { throw new RuntimeException(e); }

    }

    public RuntimeExceptionDao<LastSearch, Integer> getLastSearchRuntimeDAO(){
        if (LastSearchRuntimeDAO == null) LastSearchRuntimeDAO = getRuntimeExceptionDao(LastSearch.class);
        return LastSearchRuntimeDAO;
    }

    @Override
    public void close() {
        super.close();

        LastSearchRuntimeDAO = null;

    }


}
