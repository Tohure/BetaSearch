package com.orbismobile.betasearch.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by crhto on 23/04/2017.
 */

@DatabaseTable(tableName = "ApplyJob")
public class ApplyJob {

    public static final String C_IDJOB = "idJob";
    public static final String C_TITLEJOB = "titleJob";
    public static final String C_DATEAPPLY = "dateApply";
    public static final String C_PLACEAPPLY = "placeApply";
    public static final String C_STATUSAPPLY = "statusApply";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = C_IDJOB)
    private int idJob;

    @DatabaseField(columnName = C_TITLEJOB)
    private String titleJob;

    @DatabaseField(columnName = C_DATEAPPLY)
    private String dateApply;

    @DatabaseField(columnName = C_PLACEAPPLY)
    private String placeApply;

    @DatabaseField(columnName = C_STATUSAPPLY)
    private Boolean statusApply;

    public ApplyJob() { }

    public ApplyJob(int idJob, String titleJob, String dateApply, String placeApply, Boolean statusApply) {
        this.idJob = idJob;
        this.titleJob = titleJob;
        this.dateApply = dateApply;
        this.placeApply = placeApply;
        this.statusApply = statusApply;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;
    }

    public String getTitleJob() {
        return titleJob;
    }

    public void setTitleJob(String titleJob) {
        this.titleJob = titleJob;
    }

    public String getDateApply() {
        return dateApply;
    }

    public void setDateApply(String dateApply) {
        this.dateApply = dateApply;
    }

    public String getPlaceApply() {
        return placeApply;
    }

    public void setPlaceApply(String placeApply) {
        this.placeApply = placeApply;
    }

    public Boolean getStatusApply() {
        return statusApply;
    }

    public void setStatusApply(Boolean statusApply) {
        this.statusApply = statusApply;
    }
}