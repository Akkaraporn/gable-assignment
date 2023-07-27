package com.artassingment.akkaraporn.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public AttachmentEntity getFile(String name) {
        return attachmentRepository.findByName(name);
    }

    @Override
    public List<AttachmentDTO> getDetail(Long requestId) {
        return attachmentRepository.findByRequestId(requestId);
    }

    @Override
    public void upload(Long requestId, MultipartFile[] requestImages) throws IOException {
        List<AttachmentEntity> attachments = new ArrayList<>();
        for (MultipartFile requestImage : requestImages) {
            String[] filename = requestImage.getOriginalFilename().split("\\.");
            String name = UUID.randomUUID() + (filename.length > 0 ? "." + filename[1] : "");

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/request/getFile/")
                    .path(name)
                    .toUriString();

            AttachmentEntity attachment = new AttachmentEntity();
            attachment.setRequestId(requestId);
            attachment.setName(name);
            attachment.setContent(requestImage.getBytes());
            attachment.setType(requestImage.getContentType());
            attachment.setUri(fileUri);
            attachments.add(attachment);
        }
        attachmentRepository.saveAll(attachments);
    }

}
