package com.barista.maker.coffeemachine.service;

import com.barista.maker.coffeemachine.entity.Holiday;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface HolidayService {
    List<Holiday> getHolidays(int year, String countryCode);
    boolean isHoliday(LocalDate localDate);
}
