package com.artassingment.akkaraporn.request;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


public interface RequestService {
    Page<RequestViewDTO> getAllRemainingRequestsByStaff(Long staffId, String keyword, String sortCol, String sortDir, int offset, int pageSize);
    Page<RequestViewDTO> getAllRequestsByStaff(Long staffId, String keyword, String sortCol, String sortDir, int offset, int pageSize);
    Page<RequestViewDTO> getAllRemainingRequestsByManager(String keyword, String sortCol, String sortDir, int offset, int pageSize);
    Page<RequestViewDTO> getAllRequestsByManager(String keyword, String sortCol, String sortDir, int offset, int pageSize);
    RequestDTO getRequestDetail(Long id);
    void saveDraft(Long customerId, Long requesterId, Long managerId, String requestTitle, String requestDetail, MultipartFile[] requestImages) throws IOException;
    void sendRequest(Long customerId, Long requesterId, Long managerId, String requestTitle, String requestDetail, MultipartFile[] requestImages) throws IOException;
    void sendDraftRequest(Long id);
    void approveRequest(Long id);
    void rejectRequest(Long id, String reason);
    void modifyDetail(Long id, String detail);

}