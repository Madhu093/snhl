package com.example.kurapma.snhl.model.quotes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kurapma on 1/20/17.
 */

public class Quotes
{
    @SerializedName("id")
    private String id;

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("category")
    private String category;

    @SerializedName("quote")
    private String quote;

    @SerializedName("background")
    private String background;

    private String date;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String[] getTags ()
    {
        return tags;
    }

    public void setTags (String[] tags)
    {
        this.tags = tags;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getQuote ()
    {
        return quote;
    }

    public void setQuote (String quote)
    {
        this.quote = quote;
    }

    public String getBackground ()
    {
        return background;
    }

    public void setBackground (String background)
    {
        this.background = background;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", tags = "+tags+", author = "+author+", title = "+title+", category = "+category+", quote = "+quote+", background = "+background+",  date = "+date+"]";
    }
}