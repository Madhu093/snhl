package com.example.kurapma.snhl.model.quotes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kurapma on 1/20/17.
 */

public class Contents
{
    @SerializedName("quotes")
    private Quotes[] quotes;

    public Quotes[] getQuotes ()
    {
        return quotes;
    }

    public void setQuotes (Quotes[] quotes)
    {
        this.quotes = quotes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [quotes = "+quotes+"]";
    }
}
