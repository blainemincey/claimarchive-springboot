package com.mongodb.claimarchive.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalClaims {
    private long totalClaims;

    @JsonProperty("total")
    public long getTotalClaims() {
        return totalClaims;
    }

    public void setTotalClaims(long totalClaims) {
        this.totalClaims = totalClaims;
    }

    @Override
    public String toString() {
        return "TotalClaims{" +
                "totalClaims=" + totalClaims +
                '}';
    }
}
