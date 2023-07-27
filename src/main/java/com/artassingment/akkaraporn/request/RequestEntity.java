package com.artassingment.akkaraporn.request;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Entity
@Getter
@Setter
@Table(name = "Request")
public class RequestEntity {
    public enum Status {
        Draft,
        Wait_For_Manager_Review,
        Approved,
        Rejected_By_Manager
    }
    @Id
    private Long id;

    @Column(name = "CustomerId")
    private String customerId;

    @Column(name = "requester_id")
    private String requesterId;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    private String detail;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "LastModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // Getters and Setters (Omitted for brevity)

    // Additional methods and constructors can be added as per your requirements
}