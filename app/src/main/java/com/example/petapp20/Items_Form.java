package com.example.petapp20;

public class Items_Form {

    private String item_name;
    private String item_description;
    private double price;

    public Items_Form(double price, String item_name, String item_description){
        this.item_name = item_name;
        this.item_description = item_description;
        this.price = price;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        String str =   item_name +" , price: " + price +" " + " , description: " + item_description;
        return  str;
    }

    public boolean check_equal(Items_Form dish){
        if (this.getItem_name().equals(dish.getItem_name())){
            return true;
        }
        else {
            return false;
        }
    }

    public String to_string(){
        String str =   item_name +", price: " + price + ", description: " + item_description;
        return  str;
    }

    public void setDish_( Items_Form temp_dish) {
        this.item_name = temp_dish.getItem_name();
        this.price = temp_dish.getPrice();
        this.item_description = temp_dish.item_description;
    }



}
