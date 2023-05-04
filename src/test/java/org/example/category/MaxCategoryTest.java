package org.example.category;

import com.google.gson.Gson;
import org.example.Deal;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MaxCategoryTest {

    @org.junit.jupiter.api.Test
    void getMaxCategoryByDate() {
        ArrayList<Deal> dealArrayList = new ArrayList<Deal>();
        String MAX_DAY_CATEGORY = "maxDayCategory";
        MaxCategory maxCategory = new MaxCategory();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
        String date = LocalDate.now().format(formatter).toString();
        dealArrayList.add(new Deal(date, "булка", 50));
        dealArrayList.add(new Deal(date, "колбаса", 60));
        dealArrayList.add(new Deal(date, "металаллом", 150));
        Category category = new Category("еда");
        Category category1 = new Category("Другое");
        category.addProduct("булка");
        category.addProduct("колбаса");
        int sum = 0;
        int sum1 = 0;
        int sumExpected = 0;
        for(int i = 0; i < dealArrayList.size(); i++){
            if(category.contain(dealArrayList.get(i).getTitle())){
                sum += dealArrayList.get(i).getSum();
            }else{
                sum1 += dealArrayList.get(i).getSum();
            }
        }
        if(sum > sum1){
            sumExpected = sum;
        }else{
            sumExpected = sum1;
        }
        Assertions.assertEquals(sumExpected,maxCategory.getMaxCategoryByDate(dealArrayList,MAX_DAY_CATEGORY).getMax());
    }

    @org.junit.jupiter.api.Test
    void getJsonAllMaxCategory() {
        ArrayList<Deal> dealArrayList = new ArrayList<Deal>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
        String date = LocalDate.now().format(formatter).toString();
        dealArrayList.add(new Deal(date, "булка", 100));
        MaxCategory maxCategory = new MaxCategory();
        StringBuilder builder = new StringBuilder();
        Gson gson1 = new Gson();
            builder.append(gson1.toJson(new MaxCategoryByDate(maxCategory.MAX_DAY_CATEGORY, 100, "еда")));
            builder.append((gson1.toJson(new MaxCategoryByDate(maxCategory.MAX_MONTH_CATEGORY, 100, "еда"))));
        builder.append((gson1.toJson(new MaxCategoryByDate(maxCategory.MAX_YEAR_CATEGORY, 100, "еда"))));
        builder.append((gson1.toJson(new MaxCategoryByDate(maxCategory.MAX_CATEGORY, 100, "еда"))));

        Assertions.assertEquals(builder.toString(), maxCategory.getJsonAllMaxCategory(dealArrayList));
    }
}