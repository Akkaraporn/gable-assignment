package com.artassingment.akkaraporn.log;

import com.artassingment.akkaraporn.request.RequestEntity;
import org.springframework.data.domain.Page;

public interface LogService {
    void save(Long requestId, RequestEntity.Status status);
    Page<LogDTO> findLogByRequestId(Long requestId, int offset, int pageSize);
}

