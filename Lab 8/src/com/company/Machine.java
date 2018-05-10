package com.company;

import java.io.Serializable;

public class Machine implements Serializable {
    private final String marka;
    private final String surname;
    private final String adress;
    private final String licPlate;
    private final int parkingNumber;
    private boolean onParking;



    public Machine(String marka, String licPlate, String surname, String adress, int parkingNumber){
        this.marka = marka;
        this.surname = surname;
        this.adress = adress;
        this.licPlate = licPlate;
        this.parkingNumber = parkingNumber;
        this.onParking = false;
    }

    public Machine(String marka, String surname, String adress, String licPlate, int parkingNumber, boolean onParking) {
        this(marka,surname,adress,licPlate,parkingNumber);
        this.onParking = false;
    }

    public String getMarka() { return marka;
    }

    public String getSurname() {
        return surname;
    }

    public String getAdress() {
        return adress;
    }

    public String getLicPlate() {
        return licPlate;
    }

    public int getParkingNumber() {
        return parkingNumber;
    }

    public boolean isOnParking() {
        return onParking;
    }

    public void setOnParking(boolean onParking) {
        this.onParking = onParking;
    }

    @Override
    public String toString(){
        return marka + " " + licPlate + " " + surname + " " + adress + " " + parkingNumber;
    }

}
