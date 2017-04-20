package com.orbismobile.betasearch.model.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by tohure on 20/04/17.
 */

@DatabaseTable(tableName = "LastSearch")
public class LastSearch {

    public static final String C_PLACE = "place";
    public static final String C_QUERY = "query";
    public static final String C_ALERT = "alert";
    public static final String C_FILTROS = "filtro";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = C_QUERY)
    private String query;

    @DatabaseField(columnName = C_PLACE)
    private String place;

    @DatabaseField(columnName = C_ALERT)
    private Boolean alerta;

    @DatabaseField(columnName = C_FILTROS)
    private Boolean filtros;

    public LastSearch() { }

    public LastSearch(String query, String place, Boolean alerta, Boolean filtros) {
        this.query = query;
        this.place = place;
        this.alerta = alerta;
        this.filtros = filtros;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Boolean getAlerta() {
        return alerta;
    }

    public void setAlerta(Boolean alerta) {
        this.alerta = alerta;
    }

    public Boolean getFiltros() {
        return filtros;
    }

    public void setFiltros(Boolean filtros) {
        this.filtros = filtros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LastSearch that = (LastSearch) o;

        if (id != that.id) return false;
        if (query != null ? !query.equals(that.query) : that.query != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (alerta != null ? !alerta.equals(that.alerta) : that.alerta != null) return false;
        return filtros != null ? filtros.equals(that.filtros) : that.filtros == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (query != null ? query.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (alerta != null ? alerta.hashCode() : 0);
        result = 31 * result + (filtros != null ? filtros.hashCode() : 0);
        return result;
    }
}
