package br.com.teste.csvquery;

/**
 * Created by fefedo on 27/07/17.
 */
public class Municipio {

    private String uf,name,capital,no_accents,alternative_names,microregion,mesoregion,ibge_id, lon,lat;

    //ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion
    public Municipio(String ibge_id, String uf, String name, String capital, String lon, String lat, String no_accents, String alternative_names, String microregion, String mesoregion) {
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.no_accents = no_accents;
        this.alternative_names = alternative_names;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
        this.ibge_id = ibge_id;
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "uf='" + uf + '\'' +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", no_accents='" + no_accents + '\'' +
                ", alternative_names='" + alternative_names + '\'' +
                ", microregion='" + microregion + '\'' +
                ", mesoregion='" + mesoregion + '\'' +
                ", ibge_id=" + ibge_id +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getNo_accents() {
        return no_accents;
    }

    public void setNo_accents(String no_accents) {
        this.no_accents = no_accents;
    }

    public String getAlternative_names() {
        return alternative_names;
    }

    public void setAlternative_names(String alternative_names) {
        this.alternative_names = alternative_names;
    }

    public String getMicroregion() {
        return microregion;
    }

    public void setMicroregion(String microregion) {
        this.microregion = microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }

    public String getIbge_id() {
        return ibge_id;
    }

    public void setIbge_id(String ibge_id) {
        this.ibge_id = ibge_id;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
