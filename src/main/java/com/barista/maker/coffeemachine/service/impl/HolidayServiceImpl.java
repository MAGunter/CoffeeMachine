package com.barista.maker.coffeemachine.service.impl;

import com.barista.maker.coffeemachine.entity.Holiday;
import com.barista.maker.coffeemachine.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final RestTemplate restTemplate;

    @Value("${nager.api.url}")
    private String apiUrl;

    public List<Holiday> getHolidays(int year, String countryCode) {
        String url = String.format("%s/v3/PublicHolidays/%d/%s", apiUrl, year, countryCode);
        ResponseEntity<Holiday[]> response = restTemplate.getForEntity(url, Holiday[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            throw new RuntimeException("Failed to fetch holidays from API");
        }
    }

    public boolean isHoliday(LocalDate date) {
        String countryCode = "KZ";
        int year = date.getYear();
        List<Holiday> holidays = getHolidays(year, countryCode);
        return holidays.stream().anyMatch(holiday -> holiday.getDate().equals(date));
    }
}

