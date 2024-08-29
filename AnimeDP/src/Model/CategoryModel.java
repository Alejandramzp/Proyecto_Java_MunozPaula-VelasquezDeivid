/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class CategoryModel {
    private int categoryID;
    private String categoryName;
    private int age;
    private String gender; // 'Hombre', 'Mujer', 'Otros'

    // Constructor
    public CategoryModel(int categoryID, String categoryName, int age, String gender) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.age = age;
        this.gender = gender;
    }

    // Getters y Setters
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "CategoryID: " + categoryID + ", Name: " + categoryName + ", Age: " + age + ", Gender: " + gender;
    }
}

