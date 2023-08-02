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
@Table(name = "Requests")
public class RequestEntity {
    public enum Status {
        Draft,
        Wait_For_Manager_Review,
        Approved,
        Rejected_By_Manager
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;

    @Column(name = "CustomerId")
    private Long customerId;

    @Column(name = "requester_id")
    private Long requesterId;

    @Column(name = "manager_id")
    private Long managerId;

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