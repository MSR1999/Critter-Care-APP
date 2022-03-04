package com.example.petapp20;

import java.util.ArrayList;

public class menu_form {


    private ArrayList<Items_Form> Food_list;
    private ArrayList<Items_Form> Toy_list;
    private ArrayList<Items_Form> Essential_list;

    public menu_form() {
        Food_list = new ArrayList<Items_Form>();
        Toy_list = new ArrayList<Items_Form>();
        Essential_list = new ArrayList<Items_Form>();

    }

    public void setFood_list(ArrayList<Items_Form> food_list) {
        Food_list = food_list;
    }

    public void setToy_list(ArrayList<Items_Form> toy_list) {
        Toy_list = toy_list;
    }

    public void setEssential_list(ArrayList<Items_Form> essential_list) {
        Essential_list = essential_list;
    }

    public ArrayList<Items_Form> getFood_list() {
        return Food_list;
    }

    public ArrayList<Items_Form> getToy_list() {
        return Toy_list;
    }

    public ArrayList<Items_Form> getEssential_list() {
        return Essential_list;
    }

    public boolean exist(Items_Form dish){
        for (int i = 0; i < this.Food_list.size(); i++){
            if (Food_list.get(i).check_equal(dish)){
                return true;
            }
        }
        for (int i = 0; i < Toy_list.size(); i++){
            if(Toy_list.get(i).check_equal(dish)){
                return true;
            }
        }
        for (int i = 0; i < Essential_list.size(); i++){
            if (Essential_list.get(i).check_equal(dish)){
                return true;
            }
        }


        return false;

    }

    public boolean add_Food(Items_Form dish){
        return Food_list.add(dish);
    }

    public boolean add_TOYS(Items_Form desert){
        return Toy_list.add(desert);

    }

    public boolean add_Essentials(Items_Form drink){
        return Essential_list.add(drink);
    }

    public int get_numof_dishes(){
        int sum = Food_list.size() + Toy_list.size() + Essential_list.size();
        return sum;
    }

    public boolean remove_dish(String name, String type){
        if (type == "Food"){
            for (int i = 0; i < Food_list.size(); i++){
                String str = Food_list.get(i).getItem_name();
                if (str.equals(name)){
                    Items_Form temp = Food_list.get(i);
                    Food_list.remove(temp);
                    return true;
                }
            }
            return false;
        }
        else if (type == "Toys"){
            for (int i = 0; i < Toy_list.size(); i++){
                String str = Toy_list.get(i).getItem_name();
                if (str.equals(name)){
                    Items_Form temp = Toy_list.get(i);
                    Toy_list.remove(temp);
                    return true;
                }
            }
            return false;
        }
        else if (type == "Essentials"){
            for (int i = 0; i < Essential_list.size(); i++){
                String str = Essential_list.get(i).getItem_name();
                if (str.equals(name)){
                    Items_Form temp = Essential_list.get(i);
                    Essential_list.remove(temp);
                    return true;
                }
            }
            return false;
        }
        else{
            return false;
        }
    }

    public void clear_Food(){
        Food_list.clear();
    }

    public void clear_Toys(){
        Toy_list.clear();
    }

    public void clear_Essentials(){
        Essential_list.clear();
    }

    public void replace_dish(String name ,Items_Form dish){
        if (Food_list.contains(dish)){
            for (int i = 0; i < Food_list.size(); i++){
                if (Food_list.get(i).getItem_name().equals(name)){
                    Food_list.set(i, dish);
                }
            }
        }
        else if (Toy_list.contains(dish)){
            for (int i = 0; i < Toy_list.size(); i++){
                if (Toy_list.get(i).getItem_name().equals(name)){
                    Toy_list.set(i, dish);
                }
            }
        }
        else if (Essential_list.contains(dish)){
            for (int i = 0; i < Essential_list.size(); i++){
                if (Essential_list.get(i).getItem_name().equals(name)){
                    Essential_list.set(i, dish);
                }
            }
        }
    }


}
