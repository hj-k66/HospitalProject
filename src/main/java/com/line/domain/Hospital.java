package com.line.domain;

public class Hospital {
    private String id;
    private String address;
    private String district;
    private String category;
    private int emergencyRoom;
    private String name;
    private String subdivision;

    public Hospital(String id, String address) {
        this.id = id;
        this.address = address;
    }

    public Hospital(String id) {
        this.id = id.replaceAll("\"", "");
    }

    public String getId() {
        return id;
    }

    public String getDistrict() {
        return district;
    }

    public String getCategory() {
        return category;
    }

    public int getEmergencyRoom() {
        return emergencyRoom;
    }

    public String getName() {
        return name;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public String getAddress() {
        return address;
    }
}
