package org.progmatic.messenger.model;

public class SearchEntity {
    public String searchFrom="";
    public String searchText="";
    public String searchDate="";
    public String searchID="";
    private String order="ascent";
    private String ordBy="searchText";

    public SearchEntity(String searchFrom, String searchText, String searchDate, String searchID) {
        this.searchFrom = searchFrom;
        this.searchText = searchText;
        this.searchDate = searchDate;
        this.searchID = searchID;
    }

    public String getSearchID() {
        return searchID;
    }

    public void setSearchID(String searchID) {
        this.searchID = searchID;
    }

    public String getSearchFrom() {
        return searchFrom;
    }

    public void setSearchFrom(String searchFrom) {
        this.searchFrom = searchFrom;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrdBy() {
        return ordBy;
    }

    public void setOrdBy(String ordBy) {
        this.ordBy = ordBy;
    }

    @Override
    public String toString() {
        return "SearchEntity{" +
                "searchFrom='" + searchFrom + '\'' +
                ", searchText='" + searchText + '\'' +
                ", searchDate='" + searchDate + '\'' +
                '}';
    }
}