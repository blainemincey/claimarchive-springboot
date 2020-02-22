package com.mongodb.claimarchive.rest.service;

import com.mongodb.claimarchive.rest.model.TotalClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api")
public class MongoRestController {

    @Autowired
    private ClaimArchiveService claimArchiveService;

    // Logger component
    private static final Logger log = LoggerFactory.getLogger(MongoRestController.class);

    @GetMapping (value = "/claim/total", produces = "application/json")
    public ResponseEntity getClaimTotal() {
        log.info("Received request for claim total.");

        return ResponseEntity.ok().body(claimArchiveService.totalClaimsArchived());
    }

    @GetMapping (value = "/claim/{year}", produces = "application/json")
    public ResponseEntity getClaimArchiveByYear(@PathVariable int year) {
        log.info("Received request for claim archive by year: " + year);

        return ResponseEntity.ok().body(claimArchiveService.claimArchiveByYear(year));
    }
}