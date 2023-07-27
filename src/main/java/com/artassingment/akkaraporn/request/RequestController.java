package com.artassingment.akkaraporn.request;

import com.artassingment.akkaraporn.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @Autowired
    private UserAPI userAPI;

    @GetMapping("/staff/getAllRequestsByStaff")
    public ResponseEntity<Object> getAllRequestsByStaff(@RequestParam(name = "staffId") Long staffId,
                                                        @RequestParam(name = "filter") String filter,
                                                        @RequestParam(name = "offset") int offset,
                                                        @RequestParam(name = "pageSize") int pageSize) {
        UserModel staffUser = userAPI.getStaffById(staffId);
        if (staffUser == null) {
            return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
        }

        Page<RequestViewDTO> requests = requestService.getAllRequestsByStaff(staffId, filter, offset, pageSize);

        return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, requests);
    }
    @PostMapping("/staff/saveDraft")
    public ResponseEntity<Object> saveDraft(        @RequestParam(name = "customerId") Long customerId,
                                                    @RequestParam(name = "requesterId") Long requesterId,
                                                    @RequestParam(name = "managerId") Long managerId,
                                                    @RequestParam(name = "requestTitle") String requestTitle,
                                                    @RequestParam(name = "requestDetail") String requestDetail,
                                                    @RequestPart(name = "requestImages") MultipartFile[] requestImages
    ) throws IOException {
        UserModel customerUser = userAPI.getCustomerById(customerId);
        if (customerUser == null) {
            return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
        }

        UserModel staffUser = userAPI.getStaffById(requesterId);
        if (staffUser == null) {
            return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
        }

        UserModel managerUser = userAPI.getManagerById(managerId);
        if (managerUser == null) {
            return ResponseHandler.generateResponse("Manager not found", HttpStatus.NOT_FOUND, null);
        }

        requestService.saveDraft(customerId, requesterId, managerId, requestTitle, requestDetail, requestImages);

        return ResponseHandler.generateResponse("Draft saved Successfully", HttpStatus.CREATED);

    }

    @PostMapping("/staff/sendRequest")
    public ResponseEntity<Object> sendRequest(                    @RequestParam(name = "customerId") Long customerId,
                                                                  @RequestParam(name = "requesterId") Long requesterId,
                                                                  @RequestParam(name = "managerId") Long managerId,
                                                                  @RequestParam(name = "requestTitle") String requestTitle,
                                                                  @RequestParam(name = "requestDetail") String requestDetail,
                                                                  @RequestPart(name = "requestImages") MultipartFile[] requestImages
    ) throws IOException {
        UserModel customerUser = userAPI.getCustomerById(customerId);
        if (customerUser == null) {
            return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
        }

        UserModel staffUser = userAPI.getStaffById(requesterId);
        if (staffUser == null) {
            return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
        }

        UserModel managerUser = userAPI.getManagerById(managerId);
        if (managerUser == null) {
            return ResponseHandler.generateResponse("Manager not found", HttpStatus.NOT_FOUND, null);
        }

        requestService.sendRequest(customerId, requesterId, managerId, requestTitle, requestDetail, requestImages);

        return ResponseHandler.generateResponse("Request send Successfully", HttpStatus.CREATED);

    }


    @PostMapping
    public ResponseEntity<Object> createRequest(@RequestBody RequestDTO requestDTO) {
        RequestEntity request = new RequestEntity();
        // Map DTO properties to the entity
        // You can use a library like ModelMapper to simplify this process
        // For this example, we'll do it manually
        request.setId(requestDTO.getId());
        request.setCustomerId(requestDTO.getCustomerId());
        request.setRequesterId(requestDTO.getRequesterId());
        request.setManagerId(requestDTO.getManagerId());
        request.setTitle(requestDTO.getTitle());
        request.setDetail(requestDTO.getDetail());
        request.setStatus(requestDTO.getStatus());
        request.setReason(requestDTO.getReason());
        request.setCreatedDate(requestDTO.getCreatedDate());
        request.setLastModifiedDate(requestDTO.getLastModifiedDate());

        RequestEntity createdRequest = requestService.createRequest(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRequest(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        RequestEntity existingRequest = new RequestEntity();
            // Update the existing request with DTO properties
            existingRequest.setCustomerId(requestDTO.getCustomerId());
            existingRequest.setRequesterId(requestDTO.getRequesterId());
            existingRequest.setManagerId(requestDTO.getManagerId());
            existingRequest.setTitle(requestDTO.getTitle());
            existingRequest.setDetail(requestDTO.getDetail());
            existingRequest.setStatus(requestDTO.getStatus());
            existingRequest.setReason(requestDTO.getReason());
            existingRequest.setCreatedDate(requestDTO.getCreatedDate());
            existingRequest.setLastModifiedDate(requestDTO.getLastModifiedDate());
            RequestEntity updatedRequest = requestService.updateRequest(existingRequest);
            return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }
}
