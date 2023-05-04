package org.example.category;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
public class ReadCategories {
    public ArrayList<Category> createList(){
        File file = new File("categories.tsv");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(!emptyFile(file)) {
            ArrayList<Category> list = new ArrayList<>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder read = new StringBuilder();
                boolean flag = true;
                String add;
                while(flag){
                    add = reader.readLine();
                    if(add == null){
                        flag = false;
                    }else{
                        read.append(add);
                        read.append("\n");
                    }
                }
                String readString = read.toString();
                HashSet<String> categoryName = new HashSet<>();
                String[] arrayLine = readString.split("\n");
                String[][] line = new String[arrayLine.length][2];
                for (int i = 0; i < arrayLine.length; i++) {
                    line[i] = arrayLine[i].split("\t");
                    categoryName.add(line[i][1]);
                }
                String[] categoryArray = new String[categoryName.size()];
                categoryArray = categoryName.toArray(categoryArray);
                for (int i = 0; i < categoryArray.length; i++) {
                    Category category = new Category(categoryArray[i]);
                    for (int j = 0; j < arrayLine.length; j++) {
                        if (categoryArray[i].equals(line[j][1])) {
                            category.addProduct(line[j][0]);
                        }
                    }
                    list.add(category);
                }
                list.add(new Category("Другое"));
                return list;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            return null;
        }
    }
    private boolean emptyFile(File file){
        if(file.length() == 0){
            return true;
        }else{
            return false;
        }
    }
}
