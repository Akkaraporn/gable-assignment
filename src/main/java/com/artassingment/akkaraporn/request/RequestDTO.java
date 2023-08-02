package com.artassingment.akkaraporn.request;

import lombok.Data;

import java.util.Date;

@Data
public class RequestDTO {
    private Long id;
    private Long customerId;
    private Long requesterId;
    private Long managerId;
    private String title;
    private String detail;
    private String status;
    private String reason;
    private Date createdDate;
    private Date lastModifiedDate;
}