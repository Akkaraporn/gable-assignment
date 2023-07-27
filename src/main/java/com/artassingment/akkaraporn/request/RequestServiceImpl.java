package com.artassingment.akkaraporn.request;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;


    @Override
    public Page<RequestEntity> getAllRequests(int offset, int pageSize) {
        return requestRepository.findAll(PageRequest.of(offset, pageSize));
    }


    @Override
    public RequestEntity createRequest(RequestEntity request) {

        return requestRepository.save(request);

    }

    @Override
    public RequestEntity updateRequest(RequestEntity request) {

        return requestRepository.save(request);
    }

    @Override
    public void saveDraft(Long customerId, Long requesterId, Long managerId, String requestTitle, String requestDetail, MultipartFile[] requestImages) throws IOException {

    }

    @Override
    public void sendRequest(Long customerId, Long requesterId, Long managerId, String requestTitle, String requestDetail, MultipartFile[] requestImages) throws IOException {

    }

    @Override
    public void sendDraftRequest(Long id) {

    }

    @Override
    public void approveRequest(Long id) {

    }

    @Override
    public void rejectRequest(Long id, String reason) {

    }

    @Override
    public void modifyDetail(Long id, String detail) {

    }

    @Override
    public Page<RequestViewDTO> getAllRequestsByStaff(Long staffId, String filter, int offset, int pageSize) {
        Page<RequestViewDTO> requestList = requestRepository.findAllRequestsByStaffId( staffId);
        return requestList;
    }
}
