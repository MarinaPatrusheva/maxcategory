package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
public class TransactionHistory {
    private ArrayList<Deal> dealList = new ArrayList<>();
    private File file = new File("data.bin");
    public TransactionHistory(){
        checkList();
    }
public void saveJson(Deal deal){
    Gson gson = new Gson();
    dealList.add(deal);
    String textJson = gson.toJson(dealList);
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(textJson);
        writer.close();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
private ArrayList<Deal> load(){
    Gson gson = new Gson();
    try {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String textJson = reader.readLine();
        Type type = new TypeToken<ArrayList<Deal>>(){}.getType();
        ArrayList<Deal> dealList = gson.fromJson(textJson, type);
        return dealList;
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
private boolean emptyFile(File file){
    return file.length() == 0;
}
private void checkList(){
    if(file.exists() & !emptyFile(file)){
        dealList = load();
    }
    if(!file.exists()){
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
public ArrayList<Deal> getDealList(){
        return dealList;
}
}
