package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deal {
    private String date;
    private String title;
    private int sum;
    public Deal(String date, String title, int sum){
        this.date = date;
        this.title = title;
        this.sum = sum;
    }
    public LocalDate getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public String getTitle() {
        return title;
    }

    public int getSum() {
        return sum;
    }
}
