package com.project.backend.models.DTO;

import java.time.LocalDateTime;

public interface VoucherDTO {
    String gettitle();
    String getcode();
    LocalDateTime getvalid_until();
}
