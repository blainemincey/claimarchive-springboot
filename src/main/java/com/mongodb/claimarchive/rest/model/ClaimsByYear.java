package com.mongodb.claimarchive.rest.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("claims")
public class ClaimsByYear {
    List<Claim> claims;

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

    @Override
    public String toString() {
        return "ClaimsByYear{" +
                "claims=" + claims +
                '}';
    }
}
