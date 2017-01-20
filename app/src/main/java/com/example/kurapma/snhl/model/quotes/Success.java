package com.example.kurapma.snhl.model.quotes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kurapma on 1/20/17.
 */

public class Success
{
    @SerializedName("total")
    private String total;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+"]";
    }
}

