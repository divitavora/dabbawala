package com.example.dishankaashvi.dabba.models;

/**
 * Created by MyPC on 4/1/2017.
 */

public class listviewmodel {
    private  String DishName="";
    private  String quantity="";
    private  float totalprice;

    /*********** Set Methods ******************/
    public void setDishName(String DishName)
    {
        this.DishName = DishName;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }

    public void setTotalprice(float totalprice)
    {
        this.totalprice = totalprice;
    }

    /*********** Get Methods ****************/
    public String getDishName()
    {
        return this.DishName;
    }

    public String getQuantity()
    {
        return this.quantity;
    }

    public float getTotalprice()
    {
        return this.totalprice;
    }
}
