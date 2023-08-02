package com.artassingment.akkaraporn.request;


import com.artassingment.akkaraporn.attachment.AttachmentServiceImpl;
import com.artassingment.akkaraporn.log.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AttachmentServiceImpl attachmentServiceImpl;

    @Autowired
    private LogServiceImpl logService;

    @Override
    public Page<RequestViewDTO> getAllRemainingRequestsByStaff(Long staffId, String keyword, String sortCol, String sortDir, int offset, int pageSize) {
        Page<RequestViewDTO> requestViews = (keyword == null || keyword.isEmpty())
                ? requestRepository.findAllRemainingRequestsByStaffWithoutKeyword(staffId, RequestEntity.Status.Draft.toString(), PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortCol)))
                : requestRepository.findAllRemainingRequestsByStaff(staffId, RequestEntity.Status.Draft.toString(), keyword, PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortCol)));
        return requestViews;
    }

    @Override
    public Page<RequestViewDTO> getAllRequestsByStaff(Long staffId, String keyword, String sortCol, String sortDir, int offset, int pageSize) {
        Page<RequestViewDTO> requestViews = (keyword == null || keyword.isEmpty())
                ? requestRepository.findAllRequestsByStaffId(staffId, PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortCol)))
                : requestRepository.findAllRequestsByStaff(staffId, keyword, PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortCol)));
        return requestViews;
    }

    @Override
    public Page<RequestViewDTO> getAllRemainingRequestsByManager(String keyword, String sortCol, String sortDir, int offset, int pageSize) {
        Page<RequestViewDTO> requestViews = (keyword == null || keyword.isEmpty())
                ? requestRepository.findAllRemainingRequestsByManagerWithoutKeyword("Manager", RequestEntity.Status.Wait_For_Manager_Review.toString(), PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortCol)))
                : requestRepository.findAllRemainingRequestsByManager("Manager", RequestEntity.Status.Wait_For_Manager_Review.toString(), keyword, PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortCol)));
        return requestViews;
    }

    @Override
    public Page<RequestViewDTO> getAllRequestsByManager(String keyword, String sortCol, String sortDir, int offset, int pageSize) {
        Page<RequestViewDTO> requestViews = (keyword == null || keyword.isEmpty())
                ? requestRepository.findAllRequestsByManagerId("Manager", RequestEntity.Status.Draft.toString(), PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortCol)))
                : requestRepository.findAllRequestsByManager("Manager", RequestEntity.Status.Draft.toString(), keyword, PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortCol)));
        return requestViews;
    }

    @Override
    public RequestDTO getRequestDetail(Long id) {
        return requestRepository.findRequestDetail(id);
    }

    @Override
    public void saveDraft(Long customerId, Long requesterId, Long managerId, String requestTitle, String requestDetail, MultipartFile[] requestImages) throws IOException {
        RequestEntity request = new RequestEntity();
        request.setCustomerId(customerId);
        request.setRequesterId(requesterId);
        request.setManagerId(managerId);
        request.setTitle(requestTitle);
        request.setDetail(requestDetail);
        request.setStatus(String.valueOf(RequestEntity.Status.Draft));
        RequestEntity savedRequest = requestRepository.save(request);

        attachmentServiceImpl.upload(savedRequest.getId(), requestImages);
        logService.save(savedRequest.getId(), RequestEntity.Status.Draft);
    }

    @Override
    public void sendRequest(Long customerId, Long requesterId, Long managerId, String requestTitle, String requestDetail, MultipartFile[] requestImages) throws IOException {
        RequestEntity request = new RequestEntity();
        request.setCustomerId(customerId);
        request.setRequesterId(requesterId);
        request.setManagerId(managerId);
        request.setTitle(requestTitle);
        request.setDetail(requestDetail);
        request.setStatus(String.valueOf(RequestEntity.Status.Wait_For_Manager_Review));
        RequestEntity savedRequest = requestRepository.save(request);

        attachmentServiceImpl.upload(savedRequest.getId(), requestImages);
        logService.save(savedRequest.getId(), RequestEntity.Status.Wait_For_Manager_Review);
    }

    @Override
    public void sendDraftRequest(Long id) {
        RequestEntity request = requestRepository.findById(id).orElseThrow();
        request.setStatus(String.valueOf(RequestEntity.Status.Wait_For_Manager_Review));
        requestRepository.save(request);

        logService.save(request.getId(), RequestEntity.Status.Wait_For_Manager_Review);
    }

    @Override
    public void approveRequest(Long id) {
        RequestEntity request = requestRepository.findById(id).orElseThrow();
        request.setStatus(String.valueOf(RequestEntity.Status.Approved));
        requestRepository.save(request);

        logService.save(request.getId(), RequestEntity.Status.Approved);
    }

    @Override
    public void rejectRequest(Long id, String reason) {
        RequestEntity request = requestRepository.findById(id).orElseThrow();
        request.setStatus(String.valueOf(RequestEntity.Status.Rejected_By_Manager));
        request.setReason(reason);
        requestRepository.save(request);

        logService.save(request.getId(), RequestEntity.Status.Rejected_By_Manager);
    }

    @Override
    public void modifyDetail(Long id, String detail) {
        RequestEntity request = requestRepository.findById(id).orElseThrow();
        request.setDetail(detail);
        requestRepository.save(request);
    }
}
