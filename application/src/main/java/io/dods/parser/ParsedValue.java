package io.dods.parser;

import io.dods.model.attribute.misc.*;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
class ParsedValue {

    private String name;
    private String regel;
    private String wirkung;
    private int kapKosten;
    private int aspKosten;


    private Dauer dauer;
    private Kostentabelle kostentabelle;
    private Probe probe;
    private Reichweite reichweite;
    private Wirkungsdauer wirkungsdauer;
    private List<Zielkategorie> zielkategorie;
    private List<Aspekt> aspekt;

    public String getRegel() {
        return regel;
    }

    public void setRegel(String regel) {
        this.regel = regel;
    }

    public String getWirkung() {
        return wirkung;
    }

    public void setWirkung(String wirkung) {
        this.wirkung = wirkung;
    }

    public Dauer getDauer() {
        return dauer;
    }

    public void setDauer(Dauer dauer) {
        this.dauer = dauer;
    }

    public Kostentabelle getKostentabelle() {
        return kostentabelle;
    }

    public void setKostentabelle(Kostentabelle kostentabelle) {
        this.kostentabelle = kostentabelle;
    }

    public Probe getProbe() {
        return probe;
    }

    public void setProbe(Probe probe) {
        this.probe = probe;
    }

    public Wirkungsdauer getWirkungsdauer() {
        return wirkungsdauer;
    }

    public void setWirkungsdauer(Wirkungsdauer wirkungsdauer) {
        this.wirkungsdauer = wirkungsdauer;
    }

    public List<Zielkategorie> getZielkategorie() {
        return zielkategorie;
    }

    public void setZielkategorie(List<Zielkategorie> zielkategorie) {
        this.zielkategorie = zielkategorie;
    }

    public Reichweite getReichweite() {
        return reichweite;
    }

    public void setReichweite(Reichweite reichweite) {
        this.reichweite = reichweite;
    }

    public int getKapKosten() {
        return kapKosten;
    }

    public void setKapKosten(int kapKosten) {
        this.kapKosten = kapKosten;
    }

    public int getAspKosten() {
        return aspKosten;
    }

    public void setAspKosten(int aspKosten) {
        this.aspKosten = aspKosten;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAspekt(List<Aspekt> aspekt) {
        this.aspekt = aspekt;
    }

    public List<Aspekt> getAspekt() {
        return aspekt;
    }
}
