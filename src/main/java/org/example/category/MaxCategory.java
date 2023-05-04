package org.example.category;

import com.google.gson.Gson;
import org.example.Deal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class MaxCategory {
    public static final String MAX_DAY_CATEGORY = "maxDayCategory";
    public static final String MAX_MONTH_CATEGORY = "maxMonthCategory";
    public static final String MAX_YEAR_CATEGORY = "maxYearCategory";
    public static final String MAX_CATEGORY = "maxCategory";
    ArrayList<Category> categories;
    public MaxCategory(){
        ReadCategories read = new ReadCategories();
        categories = read.createList();
    }
    public MaxCategoryByDate getMaxCategoryByDate(ArrayList<Deal> deals, String period){
        ArrayList<Deal> sortingDateList = sortingByDates(deals, period);
        int maxSum = 0;
        String maxName = null;
        for(int i = 0; i < categories.size(); i++){
            int sum = 0;
            String name = categories.get(i).getName();
            ArrayList<Deal> sortingCategoryList;
            if(!categories.get(i).getName().equals("Другое")) {
                sortingCategoryList = sortingByCategories(sortingDateList, categories.get(i));
                sortingDateList = sortingAnother(sortingDateList, sortingCategoryList);
            }else{
                sortingCategoryList = sortingDateList;
            }
            for(int j = 0; j < sortingCategoryList.size(); j ++){
                sum += sortingCategoryList.get(j).getSum();
            }
            if(sum > maxSum){
                maxSum = sum;
                maxName = name;
            }
        }
        return new MaxCategoryByDate(period, maxSum, maxName);
    }
    private ArrayList<Deal> sortingByDates(ArrayList<Deal> deals, String period){
        ArrayList<Deal> dealsList = new ArrayList<>();
        LocalDate date = LocalDate.now();
        if(period.equals(MAX_DAY_CATEGORY)){
            for(int i = 0; i < deals.size(); i++){
                if(deals.get(i).getDate().equals(date)){
                    dealsList.add(deals.get(i));
                }
            }
            return dealsList;
        }if(period.equals(MAX_MONTH_CATEGORY)){
            LocalDate dateMonth = date.minusMonths(1);
            for(int i = 0; i < deals.size(); i++){
                if(deals.get(i).getDate().isAfter(dateMonth)){
                    dealsList.add(deals.get(i));
                }
            }
            return dealsList;
        }if(period.equals(MAX_YEAR_CATEGORY)){
            LocalDate dateYear = date.minusYears(1);
            for(int i = 0; i < deals.size(); i++){
                if(deals.get(i).getDate().isAfter(dateYear)){
                    dealsList.add(deals.get(i));
                }
            }
            return dealsList;
        }if(period.equals(MAX_CATEGORY)){
            return deals;
        }
        return null;
    }
    public String getJsonAllMaxCategory(ArrayList<Deal> deals){
        ArrayList<Deal> list = deals;
        Gson gson = new Gson();
        StringBuilder builder = new StringBuilder();
        builder.append(gson.toJson(getMaxCategoryByDate(deals, MAX_DAY_CATEGORY)));
        builder.append(gson.toJson(getMaxCategoryByDate(deals, MAX_MONTH_CATEGORY)));
        builder.append(gson.toJson(getMaxCategoryByDate(deals, MAX_YEAR_CATEGORY)));
        builder.append(gson.toJson(getMaxCategoryByDate(deals, MAX_CATEGORY)));
        return builder.toString();
    }
    private ArrayList<Deal> sortingByCategories(ArrayList<Deal> list, Category category){
        ArrayList<Deal> dealList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(category.contain(list.get(i).getTitle())){
               dealList.add(list.get(i));
            }
        }
        return dealList;
    }
    private ArrayList<Deal> sortingAnother(ArrayList<Deal> list, ArrayList<Deal> sortingCategory){
        for(int i = 0; i < sortingCategory.size(); i++){
            list.remove(sortingCategory.get(i));
        }
        return list;
    }
}
