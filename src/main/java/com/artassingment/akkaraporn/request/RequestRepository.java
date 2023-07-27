package com.artassingment.akkaraporn.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

    @Query(
            value = "SELECT id,title,detail,status,\n" +
                    "customerId, customer.first_name as customerFirstName, customer.last_name as customerLastName,\n" +
                    "requesterId, staff.first_name as staffFirstName, staff.last_name as staffLastName,\n" +
                    "managerId, manager.first_name as managerFirstName, manager.last_name as managerLastName\n" +
                    "FROM requests \n" +
                    "LEFT JOIN users as customer on customer.id = requests.customerId\n" +
                    "LEFT JOIN users as staff on staff.id = requests.requesterId\n" +
                    "LEFT JOIN users as manager on manager.id = requests.managerId\n" +
                    "WHERE requesterId =?1",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByStaffId(Long staffId);

    @Query(
            value = "SELECT id,title,detail,status,\n" +
                    "customerId, customer.first_name as customerFirstName, customer.last_name as customerLastName,\n" +
                    "requesterId, staff.first_name as staffFirstName, staff.last_name as staffLastName,\n" +
                    "managerId, manager.first_name as managerFirstName, manager.last_name as managerLastName\n" +
                    "FROM requests \n" +
                    "LEFT JOIN users as customer on customer.id = requests.customerId\n" +
                    "LEFT JOIN users as staff on staff.id = requests.requesterId\n" +
                    "LEFT JOIN users as manager on manager.id = requests.managerId\n" +
                    "WHERE managerId =?1",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByManagerId(Long managerId);

}