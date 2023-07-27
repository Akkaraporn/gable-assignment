package com.artassingment.akkaraporn.request;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


public interface RequestService {
    Page<RequestEntity> getAllRequests(int offset,int pageSize);
    RequestEntity createRequest(RequestEntity request);
    RequestEntity updateRequest(RequestEntity request);
    void saveDraft(Long customerId, Long requesterId, Long managerId, String requestTitle, String requestDetail, MultipartFile[] requestImages) throws IOException;
    void sendRequest(Long customerId, Long requesterId, Long managerId, String requestTitle, String requestDetail, MultipartFile[] requestImages) throws IOException;
    void sendDraftRequest(Long id);
    void approveRequest(Long id);
    void rejectRequest(Long id, String reason);
    void modifyDetail(Long id, String detail);

    Page<RequestViewDTO> getAllRequestsByStaff(Long staffId, String filter, int offset, int pageSize);
}