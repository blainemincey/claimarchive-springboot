package com.mongodb.claimarchive.rest.service;

import com.mongodb.DBObject;
import com.mongodb.claimarchive.rest.db.MyMongoOperations;
import com.mongodb.claimarchive.rest.model.Claim;
import com.mongodb.claimarchive.rest.model.ClaimsByYear;
import com.mongodb.claimarchive.rest.model.TotalClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ClaimArchiveService {

    @Value("${spring.data.mongodb.collection}")
    private String collection;

    @Autowired
    private MyMongoOperations myMongoOperations;

    // Logger component
    private static final Logger log = LoggerFactory.getLogger(ClaimArchiveService.class);

    /**
     *
     * @return
     */
    public TotalClaims totalClaimsArchived() {
        log.info("Get count of total claims archived.");

        MongoOperations mongoOps = myMongoOperations.getMongoOperations();
        long numberClaimsArchived = mongoOps.getCollection(this.collection).estimatedDocumentCount();

        log.info("Number of claims archived: " + numberClaimsArchived);

        TotalClaims totalClaims = new TotalClaims();
        totalClaims.setTotalClaims(numberClaimsArchived);

        return totalClaims;
    }

    /**
     *
     * @param year
     * @return
     */
    public ClaimsByYear claimArchiveByYear(int year) {
        log.info("Get claim archive by year: " + year);

        ProjectionOperation projection
                = Aggregation.project("fullDocument.claimType")
                .and("$fullDocument.dateClaimSubmitted").extractYear().as("year");

        MatchOperation match = Aggregation.match(Criteria.where("year").is(year));

        GroupOperation group = Aggregation.group("claimType").count().as("total");

        SortOperation sort = Aggregation.sort(Sort.Direction.ASC, "_id");


        Aggregation aggregation = Aggregation.newAggregation(projection,match,group,sort);

        // Now, run our aggregation
        MongoOperations mongoOps = myMongoOperations.getMongoOperations();
        AggregationResults<Claim> results = mongoOps.aggregate(aggregation, "claims", Claim.class);

        ClaimsByYear claimsByYear = new ClaimsByYear();
        claimsByYear.setClaims(results.getMappedResults());

        return claimsByYear;
    }
}