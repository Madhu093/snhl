package com.example.kurapma.snhl.model.quotes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kurapma on 1/20/17.
 */

public class QuoteOfTheDay
{
    @SerializedName("contents")
    private Contents contents;

    @SerializedName("success")
    private Success success;

    public Contents getContents ()
    {
        return contents;
    }

    public void setContents (Contents contents)
    {
        this.contents = contents;
    }

    public Success getSuccess ()
    {
        return success;
    }

    public void setSuccess (Success success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [contents = "+contents+", success = "+success+"]";
    }
}

/*
{
        "success": {
        "total": 1
        },
        "contents": {
        "quotes": [
        {
        "quote": "Predicting Rain Doesn't Count. Building Arks Does.",
        "length": null,
        "author": "Warren Buffett",
        "tags": [
        "action",
        "execution",
        "inspire"
        ],
        "category": "inspire",
        "date": "2017-01-20",
        "title": "Inspiring Quote of the day",
        "background": "https://theysaidso.com/img/bgs/man_on_the_mountain.jpg",
        "id": "f8OgfJ6bJErYE620jTNSyweF"
        }
        ]
        }
        }
*/

