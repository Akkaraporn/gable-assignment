package com.artassingment.akkaraporn.attachment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity, Long> {
    AttachmentEntity findByName(String name);
    @Query(
            value = "SELECT files.id as fileId, files.uri as fileUri \n" +
                    "FROM files \n" +
                    "LEFT JOIN requests on requests.id = files.request_id\n" +
                    "WHERE requests.id = ?1",
            countQuery = "SELECT count(*)\n" +
                    "FROM files \n" +
                    "LEFT JOIN requests on requests.id = files.request_id\n" +
                    "WHERE requests.id = ?1",
            nativeQuery = true
    )
    List<AttachmentDTO> findByRequestId(Long requestId);
}
