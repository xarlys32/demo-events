package com.fever.demo.domain.common;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DateHelperTest {

    @Test
    public void checkIfStartDateIsPreviousToEnd_whenValues_isCorrect() {
        String startDate = "2021-02-10";
        String endDate = "2021-07-31";

        boolean isPrevious = DateHelper.checkIfStartDateIsPreviousToEnd(startDate, endDate);

        assertTrue(isPrevious);

    }

    @Test
    public void checkIfStartDateIsPreviousToEnd_whenValues_isWrong() {
        String endDate = "2021-02-10";
        String startDate = "2021-07-31";

        boolean isPrevious = DateHelper.checkIfStartDateIsPreviousToEnd(startDate, endDate);

        assertFalse(isPrevious);

    }

    @Test
    public void checkIfStartDateIsPreviousToEnd_whenValues_isWrongString() {
        String endDate = "2021-02-10T00:00:01";
        String startDate = "2021-07-31";

        assertThrows(DateTimeParseException.class, () -> DateHelper.checkIfStartDateIsPreviousToEnd(startDate, endDate));
    }

    @Test
    public void isValidDateFormat_whenValues_isCorrect() {
        String startDate = "2021-02-10";

        boolean isValid = DateHelper.isValidDateFormat(startDate);

        assertTrue(isValid);
    }

    @Test
    public void isValidDateFormat_whenValues_isWrong() {
        String startDate = "10-02-2021";

        boolean isValid = DateHelper.isValidDateFormat(startDate);

        assertFalse(isValid);
    }

    @Test
    public void addHours_whenValues_isCorrect() {
        String startDate = "2021-02-10";

        String dateWithHours = DateHelper.addHours(startDate, true);

        assertEquals("2021-02-10T00:00:01", dateWithHours);
    }

    @Test
    public void addHours_whenValues_throwsError() {
        String startDate = "2021-02-10T00:00:01";

        assertThrows(DateTimeParseException.class, () -> DateHelper.addHours(startDate, true));
    }
}
