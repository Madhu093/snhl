package com.example.kurapma.snhl.model.youTubePojo;

/**
 * Created by kurapma on 1/24/17.
 */

public class ChannelResponse {
    private String etag;

    private String[] items;

    private PageInfo pageInfo;

    private String prevPageToken;

    private String nextPageToken;

    private String kind;

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public String[] getItems ()
    {
        return items;
    }

    public void setItems (String[] items)
    {
        this.items = items;
    }

    public PageInfo getPageInfo ()
    {
        return pageInfo;
    }

    public void setPageInfo (PageInfo pageInfo)
    {
        this.pageInfo = pageInfo;
    }

    public String getPrevPageToken ()
    {
        return prevPageToken;
    }

    public void setPrevPageToken (String prevPageToken)
    {
        this.prevPageToken = prevPageToken;
    }

    public String getNextPageToken ()
    {
        return nextPageToken;
    }

    public void setNextPageToken (String nextPageToken)
    {
        this.nextPageToken = nextPageToken;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [etag = "+etag+", items = "+items+", pageInfo = "+pageInfo+", prevPageToken = "+prevPageToken+", nextPageToken = "+nextPageToken+", kind = "+kind+"]";
    }

}
