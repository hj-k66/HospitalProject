package com.line.domain;

public class Hospital {
    private String id;
    private String address;
    private String district;
    private String category;
    private int emergencyRoom;
    private String name;
    private String subdivision;

    public Hospital(String id, String address){
        this.id = id;
        this.address = address;
    }

    public Hospital(String id, String address, String category, int emergencyRoom, String name, String subdivision) {
        this.id = id;
        this.address = address;
        this.category = category;
        this.emergencyRoom = emergencyRoom;
        this.name = name;
        this.subdivision = subdivision;
        this.setDistrict();
    }

    public void setDistrict() {
        String[] splitted = this.address.split(" ");
        this.district = String.format("%s %s", splitted[0], splitted[1]);
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
        String[] sublist = { "외과","내과", "소아", "피부", "성형", "정형외과", "척추", "교정", "산부인과",
                "관절", "봉합", "화상", "골절", "영유아", "안과", "가정의학과", "비뇨기과", "치과"};
        for(String s : sublist){
            if(this.subdivision.contains(s)){
                this.subdivision = s;
            }
        }
        return subdivision;
    }

    public String getAddress() {
        return address;
    }
}
