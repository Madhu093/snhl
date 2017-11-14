package com.example.kurapma.snhl.model.youTubePojo;

/**
 * Created by kurapma on 1/24/17.
 */

public class PageInfo {
    private String totalResults;

    private String resultsPerPage;

    public String getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (String totalResults)
    {
        this.totalResults = totalResults;
    }

    public String getResultsPerPage ()
    {
        return resultsPerPage;
    }

    public void setResultsPerPage (String resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [totalResults = "+totalResults+", resultsPerPage = "+resultsPerPage+"]";
    }
}
