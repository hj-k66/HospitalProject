package com.line;

import com.line.parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileController<T>{
    Parser<T> parser;
    boolean isRemoveColumnName = true;

    public FileController(Parser<T> parser) {
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
    
    public void createAFile(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
    }

    public void write(String queryFile, List<String> strlist) {
        File file = new File(queryFile);

        try{
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            for (String str : strlist){
                br.write(str);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
