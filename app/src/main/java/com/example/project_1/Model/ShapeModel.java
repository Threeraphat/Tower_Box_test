package com.example.project_1.Model;

public class ShapeModel {
    int color;
    String key_Color;

    public ShapeModel(){
    }

    public ShapeModel(int color, String key_Color) {
        this.color = color;
        this.key_Color = key_Color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getKey_Color() {
        return key_Color;
    }

    public void setKey_Color(String key_Color) {
        this.key_Color = key_Color;
    }
}