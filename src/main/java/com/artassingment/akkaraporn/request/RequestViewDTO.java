package com.artassingment.akkaraporn.request;

import java.util.Date;

public interface RequestViewDTO {
    Long getRequestId();
    String getTitle();
    String getDetail();
    String getStatus();
    Long getCustomerId();
    String getCustomerFirstName();
    String getCustomerLastName();
    Long getStaffId();
    String getStaffFirstName();
    String getStaffLastName();
    Date getLastUpdate();
}
