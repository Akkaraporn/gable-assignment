package com.artassingment.akkaraporn.log;

import com.artassingment.akkaraporn.request.RequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    public void save(Long requestId, RequestEntity.Status status) {
        LogEntity log = new LogEntity();
        log.setRequestId(requestId);
        log.setStatus(status);
        logRepository.save(log);
    }

    @Override
    public Page<LogDTO> findLogByRequestId(Long requestId, int offset, int pageSize) {
        return logRepository.findLogByRequestId(requestId, PageRequest.of(offset, pageSize));
    }
}
