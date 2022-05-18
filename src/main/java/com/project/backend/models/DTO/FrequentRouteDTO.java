package com.project.backend.models.DTO;

public interface FrequentRouteDTO {
    Integer getuser_id();
    String getroute_code();
    String getfrom_airport();
    String getto_airport();
    Integer getflight_amount();
}
