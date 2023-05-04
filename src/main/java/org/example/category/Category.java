package org.example.category;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<String> productList = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public boolean contain(String product) {
        for (String p : productList) {
            if (p.equals(product)) {
                return true;
            }
        }
        return false;
    }
    protected void addProduct(String product){
        productList.add(product);
    }
    public String getName(){
        return name;
    }
}
