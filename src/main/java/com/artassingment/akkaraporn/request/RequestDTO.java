package com.artassingment.akkaraporn.request;

import lombok.Data;

import java.util.Date;

@Data
public class RequestDTO {
    private Long id;
    private String customerId;
    private String requesterId;
    private String managerId;
    private String title;
    private String detail;
    private String status;
    private String reason;
    private Date createdDate;
    private Date lastModifiedDate;
}