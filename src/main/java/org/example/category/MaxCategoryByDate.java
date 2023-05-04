package org.example.category;

public class MaxCategoryByDate {
    private String period;
    private int max;
    private String name;
    public MaxCategoryByDate(String period, int max, String name){
        this.period = period;
        this.max = max;
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public int getMax() {
        return max;
    }

    public String getName() {
        return name;
    }
}
