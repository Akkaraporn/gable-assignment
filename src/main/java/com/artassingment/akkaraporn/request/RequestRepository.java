package com.artassingment.akkaraporn.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.last_modified_date as lastUpdate,\n" +
                    "c.id as customerId, c.firstname as customerFirstName, c.lastname as customerLastName,\n" +
                    "s.id as staffId, s.firstname as staffFirstName, s.lastname as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND r.status = ?2 AND ((c.firstname LIKE concat('%', ?3, '%') OR c.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.firstname LIKE concat('%', ?3, '%') OR s.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND r.status = ?2 AND ((c.firstname LIKE concat('%', ?3, '%') OR c.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.firstname LIKE concat('%', ?3, '%') OR s.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRemainingRequestsByStaff(Long staffId, String status, String keyword, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.last_modified_date as lastUpdate,\n" +
                    "c.id as customerId, c.firstname as customerFirstName, c.lastname as customerLastName,\n" +
                    "s.id as staffId, s.firstname as staffFirstName, s.lastname as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND r.status = ?2",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND r.status = ?2",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRemainingRequestsByStaffWithoutKeyword(Long staffId, String status, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.last_modified_date as lastUpdate,\n" +
                    "c.id as customerId, c.firstname as customerFirstName, c.lastname as customerLastName,\n" +
                    "s.id as staffId, s.firstname as staffFirstName, s.lastname as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND ((c.firstname LIKE concat('%', ?2, '%') OR c.lastname LIKE concat('%', ?2, '%')) OR\n" +
                    "(s.firstname LIKE concat('%', ?2, '%') OR s.lastname LIKE concat('%', ?2, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?2, '%') OR r.detail LIKE concat('%', ?2, '%')))",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1 AND ((c.firstname LIKE concat('%', ?2, '%') OR c.lastname LIKE concat('%', ?2, '%')) OR\n" +
                    "(s.firstname LIKE concat('%', ?2, '%') OR s.lastname LIKE concat('%', ?2, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?2, '%') OR r.detail LIKE concat('%', ?2, '%')))",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByStaff(Long staffId, String keyword, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.last_modified_date as lastUpdate,\n" +
                    "c.id as customerId, c.firstname as customerFirstName, c.lastname as customerLastName,\n" +
                    "s.id as staffId, s.firstname as staffFirstName, s.lastname as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "WHERE s.id = ?1",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByStaffId(Long staffId, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.last_modified_date as lastUpdate,\n" +
                    "c.id as customerId, c.firstname as customerFirstName, c.lastname as customerLastName,\n" +
                    "s.id as staffId, s.firstname as staffFirstName, s.lastname as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "LEFT JOIN user as m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status != ?2 AND ((c.firstname LIKE concat('%', ?3, '%') OR c.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.firstname LIKE concat('%', ?3, '%') OR s.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "LEFT JOIN user as m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status != ?2 AND ((c.firstname LIKE concat('%', ?3, '%') OR c.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.firstname LIKE concat('%', ?3, '%') OR s.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByManager(String role, String status, String keyword, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.last_modified_date as lastUpdate,\n" +
                    "c.id as customerId, c.firstname as customerFirstName, c.lastname as customerLastName,\n" +
                    "s.id as staffId, s.firstname as staffFirstName, s.lastname as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "LEFT JOIN user as m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status != ?2",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "LEFT JOIN user as m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status != ?2",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRequestsByManagerId(String role, String status, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.last_modified_date as lastUpdate,\n" +
                    "c.id as customerId, c.firstname as customerFirstName, c.lastname as customerLastName,\n" +
                    "s.id as staffId, s.firstname as staffFirstName, s.lastname as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "LEFT JOIN user as m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status = ?2 AND ((c.firstname LIKE concat('%', ?3, '%') OR c.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.firstname LIKE concat('%', ?3, '%') OR s.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "LEFT JOIN user as m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status = ?2 AND ((c.firstname LIKE concat('%', ?3, '%') OR c.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(s.firstname LIKE concat('%', ?3, '%') OR s.lastname LIKE concat('%', ?3, '%')) OR\n" +
                    "(r.title LIKE concat('%', ?3, '%') OR r.detail LIKE concat('%', ?3, '%')))",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRemainingRequestsByManager(String role, String status, String keyword, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.last_modified_date as lastUpdate,\n" +
                    "c.id as customerId, c.firstname as customerFirstName, c.lastname as customerLastName,\n" +
                    "s.id as staffId, s.firstname as staffFirstName, s.lastname as staffLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "LEFT JOIN user as m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status = ?2",
            countQuery = "SELECT count(*)\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "LEFT JOIN user as m on r.manager_id = m.id\n" +
                    "WHERE m.role = ?1 AND r.status = ?2",
            nativeQuery = true
    )
    Page<RequestViewDTO> findAllRemainingRequestsByManagerWithoutKeyword(String role, String status, Pageable pageable);

    @Query(
            value = "SELECT r.id as requestId, r.title, r.detail, r.status, r.reason,\n" +
                    "c.id as customerId, c.firstname as customerFirstName, c.lastname as customerLastName,\n" +
                    "s.id as staffId, s.firstname as staffFirstName, s.lastname as staffLastName,\n" +
                    "m.id as managerId, m.firstname as managerFirstName, m.lastname as managerLastName\n" +
                    "FROM requests r\n" +
                    "LEFT JOIN user as c on r.customer_id = c.id\n" +
                    "LEFT JOIN user as s on r.requester_id = s.id\n" +
                    "LEFT JOIN user as m on r.manager_id = m.id\n" +
                    "WHERE r.id = ?1",
            nativeQuery = true
    )
    RequestDTO findRequestDetail(Long id);


}