package com.artassingment.akkaraporn.attachment;
import com.artassingment.akkaraporn.ResponseHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping(value = "/api/request")
public class AttachmentController {
    @Autowired
    private AttachmentServiceImpl attachmentService;

    @RequestMapping(value = "/getFile/{name}", method = RequestMethod.GET)
    public void getFile(@PathVariable String name, HttpServletResponse response) throws IOException {
        AttachmentEntity file = attachmentService.getFile(name);
        response.setContentType(file.getType());
        response.getOutputStream().write(file.getContent());
        response.getOutputStream().close();
    }
    @RequestMapping(value = "/getFileDetail/{requestId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFileDetail(@PathVariable Long requestId) {
        try {
            List<AttachmentDTO> files = attachmentService.getDetail(requestId);

            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, files);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

}
