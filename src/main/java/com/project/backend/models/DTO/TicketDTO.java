package com.project.backend.models.DTO;

import java.time.LocalDateTime;

public interface TicketDTO {
    String getusername();
    String getticket_id();
    String getfull_name();
    String getflight_id();
    LocalDateTime getflight_date();
    String getfrom_airport();
    String getto_airport();
}
