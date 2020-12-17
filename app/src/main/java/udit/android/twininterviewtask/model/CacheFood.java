package udit.android.twininterviewtask.model;

import java.util.List;

public class CacheFood {

    private String categoryName;
    private List<Food> foodList;

    public CacheFood(String categoryName, List<Food> foodList) {
        this.categoryName = categoryName;
        this.foodList = foodList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        return "CacheFood{" +
                "categoryName='" + categoryName + '\'' +
                ", foodList=" + foodList +
                '}';
    }
}
