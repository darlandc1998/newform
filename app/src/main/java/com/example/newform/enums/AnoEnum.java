package com.example.newform.enums;

public enum AnoEnum {

    A2020("2020"),
    A2021("2021");

    private String description;

    AnoEnum(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description ;
    }
}
