package com.fever.demo.domain.common;

import com.fever.demo.config.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {
    public static boolean validateDates(String startDate, String endDate) {
        if(!isValidDateFormat(startDate) || !isValidDateFormat(endDate)) {
            return false;
        }
        return checkIfStartDateIsPreviousToEnd(startDate, endDate);
    }

    public static boolean checkIfStartDateIsPreviousToEnd(String startDate, String endDate) throws DateTimeParseException {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return !end.isBefore(start);
    }

    public static boolean isValidDateFormat(String date) {
        return date != null && date.matches(Constants.DATE_FORMAT);
    }

    public static String addHours(String date, boolean isStart) throws DateTimeParseException {
        LocalDate dateWithHours = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return isStart ? dateWithHours.atTime(0, 0, 1).toString()
                : dateWithHours.atTime(23, 59, 59).toString();

    }
}
