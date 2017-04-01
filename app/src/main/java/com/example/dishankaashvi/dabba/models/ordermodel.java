package com.example.dishankaashvi.dabba.models;

/**
 * Created by MyPC on 3/28/2017.
 */

public class ordermodel {
    public String order_item;
    public String quantity;
    public float total_price;

    public ordermodel(String order_item,String quantity,float total_price)
    {
        this.order_item=order_item;
        this.quantity=quantity;
        this.total_price=total_price;
    }
}
