package ru.ScheduleOfEvents.util;

public class InputTextExtractor {
    private String name;
    private String category;
    public InputTextExtractor(String name,String category) {
        this.name = name;
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public InputTextExtractor(){}
}
