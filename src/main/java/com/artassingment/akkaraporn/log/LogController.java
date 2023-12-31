package com.artassingment.akkaraporn.log;

import com.artassingment.akkaraporn.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/request")
public class LogController {
    @Autowired
    private LogServiceImpl logService;


    @RequestMapping(value = "/getLog", method = RequestMethod.GET)
    public ResponseEntity<Object> getLogInfo(@RequestParam(name = "requestId") Long requestId,
                                             @RequestParam(name = "offset", defaultValue = "0") int offset,
                                             @RequestParam(name = "pageSize", defaultValue = "25") int pageSize) {
        try {
            Page<LogDTO> logs = logService.findLogByRequestId(requestId, offset, pageSize);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, logs);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }
}
