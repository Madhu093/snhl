package com.example.kurapma.snhl.model;

/**
 * Created by kurapma on 1/23/17.
 */

public class LocationResponse {

    private Location location;

    private String accuracy;

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getAccuracy ()
    {
        return accuracy;
    }

    public void setAccuracy (String accuracy)
    {
        this.accuracy = accuracy;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [location = "+location+", accuracy = "+accuracy+"]";
    }

    /*{
        "location": {
        "lat": 51.0,
                "lng": -0.1
    },
        "accuracy": 1200.4
    }*/
}
