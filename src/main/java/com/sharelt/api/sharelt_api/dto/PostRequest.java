package com.sharelt.api.sharelt_api.dto;

import java.time.Instant;

import com.sharelt.api.sharelt_api.response.UserProfileSummary;

public class PostRequest {
    private Long id;
    private String description;
    private String imagePath;
    private String location;
    private UserProfileSummary createdBy;
    private Instant creationDateTime;
}
