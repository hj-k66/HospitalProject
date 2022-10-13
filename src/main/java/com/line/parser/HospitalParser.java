package com.line.parser;

import com.line.domain.Hospital;

public class HospitalParser implements Parser<Hospital>{

    private String getSubdivision(String name){
        String[] sublist = { "외과","내과", "소아", "피부", "성형", "정형외과", "척추", "교정", "산부인과",
                "관절", "봉합", "화상", "골절", "영유아", "안과", "가정의학과", "비뇨기과", "치과"};
        for(String s : sublist){
            if(name.contains(s)){
                return s;
            }
        }
        return "";
    }
    @Override
    public Hospital parse(String str) {
        str = str.replaceAll("\"", "");
        String[] splitted = str.split(",");

        String subdivision = getSubdivision(splitted[7]);

        return new Hospital(splitted[0], splitted[1],splitted[2],Integer.parseInt(splitted[6]),splitted[10],subdivision);
    }
}
