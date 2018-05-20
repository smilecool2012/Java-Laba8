package com.ntu.MyLab.Entity;

public class Flight extends AllEntitysParent{

    private long id;
    private long flightnumber;
    private String otpravlenie;
    private String naznachenie;
    private long kolichestvomest;
    private long kolichestvoprodanuh;
    private long stoimost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(long flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getOtpravlenie() {
        return otpravlenie;
    }

    public void setOtpravlenie(String otpravlenie) {
        this.otpravlenie = otpravlenie;
    }

    public String getNaznachenie() {
        return naznachenie;
    }

    public void setNaznachenie(String naznachenie) {
        this.naznachenie = naznachenie;
    }

    public long getKolichestvomest() {
        return kolichestvomest;
    }

    public void setKolichestvomest(long kolichestvomest) {
        this.kolichestvomest = kolichestvomest;
    }

    public long getKolichestvoprodanuh() {
        return kolichestvoprodanuh;
    }

    public void setKolichestvoprodanuh(long kolichestvoprodanuh) {
        this.kolichestvoprodanuh = kolichestvoprodanuh;
    }

    public long getStoimost() {
        return stoimost;
    }

    public void setStoimost(long stoimost) {
        this.stoimost = stoimost;
    }

    public Flight() {
    }

    public Flight(long id, long flightnumber, String otpravlenie, String naznachenie, long kolichestvomest, long kolichestvoprodanuh, long stoimost) {
        this.id = id;
        this.flightnumber = flightnumber;
        this.otpravlenie = otpravlenie;
        this.naznachenie = naznachenie;
        this.kolichestvomest = kolichestvomest;
        this.kolichestvoprodanuh = kolichestvoprodanuh;
        this.stoimost = stoimost;

    }
}
