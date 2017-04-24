package com.orbismobile.betasearch.model.db;

/**
 * Created by crhto on 24/04/2017.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "Filter")
public class Filter implements Serializable {

    public static final String C_IDSEARCH = "idSearch";
    public static final String C_SALARYM = "salaryMin";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = C_IDSEARCH)
    private int idSearch;

    @DatabaseField(columnName = C_SALARYM)
    private String salaryMin;

    public Filter() { }

    public Filter(int idSearch, String salaryMin) {
        this.idSearch = idSearch;
        this.salaryMin = salaryMin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(int idSearch) {
        this.idSearch = idSearch;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin;
    }
}
