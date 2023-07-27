package com.artassingment.akkaraporn.log;

import java.util.Date;

public interface LogDTO {
    Long getLogId();
    String getStatus();
    Long getStaffId();
    String getStaffFirstName();
    String getStaffLastName();
    Date getLastUpdate();

}
