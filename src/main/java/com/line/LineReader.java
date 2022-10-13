package com.line;

import com.line.parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineReader<T>{
    Parser<T> parser;
    boolean isRemoveColumnName = true;

    public LineReader(Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readAndParse(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        List<T> result = new ArrayList<>();
        String str;
        if(isRemoveColumnName){
            br.readLine();
        }
        while((str = br.readLine()) != null){
            result.add(parser.parse(str));
        }
        return result;
    }




}
