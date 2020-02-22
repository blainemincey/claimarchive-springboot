package com.mongodb.claimarchive.rest.model;

public class Claim {
    private String _id;
    private int total;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
