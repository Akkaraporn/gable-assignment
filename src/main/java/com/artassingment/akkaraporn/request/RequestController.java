package com.artassingment.akkaraporn.request;

import com.artassingment.akkaraporn.ResponseHandler;
import com.artassingment.akkaraporn.user.UserQueryRepo;
import com.artassingment.akkaraporn.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    private final RequestService requestService;
    private final UserService userService;

    @Autowired
    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }


    @GetMapping("/staff/getAllRequestsByStaff")
    public ResponseEntity<Object> getAllRequestsByStaff(@RequestParam(name = "staffId") Long staffId,
                                                        @RequestParam(name = "filter") String filter,
                                                        @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                        @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                        @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                        @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            UserQueryRepo staffUser = userService.getUserById(staffId,"Staff");
            if (staffUser == null) {
                return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
            }

            Page<RequestViewDTO> requests = requestService.getAllRequestsByStaff(staffId, filter, sortCol, sortDir, offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, requests);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }
    @GetMapping("/staff/getAllRemainingRequestsByStaff")
    public ResponseEntity<Object> getAllRemainingRequestsByStaff(@RequestParam(name = "staffId") Long staffId,
                                                        @RequestParam(name = "filter") String filter,
                                                        @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                        @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                        @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                        @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            UserQueryRepo staffUser = userService.getUserById(staffId,"Staff");
            if (staffUser == null) {
                return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
            }

            Page<RequestViewDTO> requests = requestService.getAllRemainingRequestsByStaff(staffId, filter, sortCol, sortDir, offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, requests);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }
    @GetMapping("/manager/getAllRequestsByManager")
    public ResponseEntity<Object> getAllRequestsByManager(@RequestParam(name = "filter", required = false) String filter,
                                                          @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                          @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                          @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                          @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            Page<RequestViewDTO> requests = requestService.getAllRequestsByManager(filter, sortCol, sortDir, offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, requests);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @GetMapping("/manager/getAllRemainingRequestsByManager")
    public ResponseEntity<Object> getAllRemainingRequestsByManager(@RequestParam(name = "filter", required = false) String filter,
                                                                   @RequestParam(name = "offset", defaultValue = "0") int offset,
                                                                   @RequestParam(name = "pageSize", defaultValue = "25") int pageSize,
                                                                   @RequestParam(name = "sortCol", defaultValue = "title") String sortCol,
                                                                   @RequestParam(name = "sortDir", defaultValue = "ASC") String sortDir) {
        try {
            Page<RequestViewDTO> requests = requestService.getAllRemainingRequestsByManager(filter, sortCol, sortDir, offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, requests);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @PostMapping("/staff/saveDraft")
    public ResponseEntity<Object> saveDraft(        @RequestParam(name = "customerId") Long customerId,
                                                    @RequestParam(name = "requesterId") Long requesterId,
                                                    @RequestParam(name = "managerId") Long managerId,
                                                    @RequestParam(name = "requestTitle") String requestTitle,
                                                    @RequestParam(name = "requestDetail") String requestDetail,
                                                    @RequestPart(name = "requestImages") MultipartFile[] requestImages
    ) {
        try {
            UserQueryRepo customerUser = userService.getUserById(customerId,"Customer");
        if (customerUser == null) {
            return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
        }

            UserQueryRepo staffUser = userService.getUserById(requesterId,"Staff");
        if (staffUser == null) {
            return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
        }

            UserQueryRepo managerUser = userService.getUserById(managerId,"Manager");
        if (managerUser == null) {
            return ResponseHandler.generateResponse("Manager not found", HttpStatus.NOT_FOUND, null);
        }

        requestService.saveDraft(customerId, requesterId, managerId, requestTitle, requestDetail, requestImages);


        return ResponseHandler.generateResponse("Request send Successfully", HttpStatus.CREATED);
    } catch (Exception ex) {
        return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
    }

    @PostMapping("/staff/sendRequest")
    public ResponseEntity<Object> sendRequest(@RequestParam(name = "customerId") Long customerId,
                                              @RequestParam(name = "requesterId") Long requesterId,
                                              @RequestParam(name = "managerId") Long managerId,
                                              @RequestParam(name = "requestTitle") String requestTitle,
                                              @RequestParam(name = "requestDetail") String requestDetail,
                                              @RequestPart(name = "requestImages") MultipartFile[] requestImages
    ) {
        try {

            UserQueryRepo customerUser = userService.getUserById(customerId,"Customer");
            if (customerUser == null) {
                return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
            }

            UserQueryRepo staffUser = userService.getUserById(requesterId,"Staff");
            if (staffUser == null) {
                return ResponseHandler.generateResponse("Staff not found", HttpStatus.NOT_FOUND, null);
            }

            UserQueryRepo  managerUser = userService.getUserById(managerId,"Manager");
            if (managerUser == null) {
                return ResponseHandler.generateResponse("Manager not found", HttpStatus.NOT_FOUND, null);
            }

            requestService.saveDraft(customerId, requesterId, managerId, requestTitle, requestDetail, requestImages);


            return ResponseHandler.generateResponse("Request send Successfully", HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PatchMapping("/staff/sendDraftRequest/{id}")
    public ResponseEntity<Object> sendDraftRequest(@PathVariable Long id) {
        try {
            requestService.sendDraftRequest(id);
            return ResponseHandler.generateResponse("Request send Successfully", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PatchMapping("/manager/approveRequest/{id}")
    public ResponseEntity<Object> approveRequest(@PathVariable Long id) {
        try {
            requestService.approveRequest(id);
            return ResponseHandler.generateResponse("Request approved", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PatchMapping("/manager/rejectRequest")
    public ResponseEntity<Object> rejectRequest(@RequestParam Long id,@RequestParam(name = "reason") String reason) {
        try {
            requestService.rejectRequest(id,reason);

            return ResponseHandler.generateResponse("Request rejected", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PatchMapping("/manager/modifyDetail")
    public ResponseEntity<Object> modifyDetail(@RequestParam(name = "id") Long id, @RequestParam(name = "detail") String detail) {
        try {
            requestService.modifyDetail(id, detail);

            return ResponseHandler.generateResponse("Detail modified", HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    }
