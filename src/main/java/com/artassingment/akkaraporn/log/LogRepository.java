package com.artassingment.akkaraporn.log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
    @Query(
            value = "SELECT l.id as logId, l.status, l.updated_at as lastUpdate,\n" +
                    "u.id as staffId, u.firstname as staffFirstName, u.lastname as staffLastName\n" +
                    "FROM logs l\n" +
                    "LEFT JOIN requests r on r.id = l.request_id\n" +
                    "LEFT JOIN user u on r.requester_id = u.id\n" +
                    "WHERE r.id = ?1 ORDER BY l.created_at ASC",
            countQuery = "SELECT count(*) FROM logs l\n" +
                    "LEFT JOIN requests r on r.id = l.request_id\n" +
                    "LEFT JOIN user u on r.requester_id = u.id\n" +
                    "WHERE r.id = ?1 ORDER BY l.created_at ASC",
            nativeQuery = true
    )
    Page<LogDTO> findLogByRequestId(Long requestId, Pageable pageable);
}

