package com.artassingment.akkaraporn.attachment;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    AttachmentEntity getFile(String name);
    List<AttachmentDTO> getDetail(Long requestId);
    void upload(Long requestId, MultipartFile[] multipartFiles) throws IOException;

}
