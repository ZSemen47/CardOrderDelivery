package ru.netology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    private LocalDate time = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public String calculatedData(int Days) {
        LocalDate newDate = time.plusDays(Days);
        return formatter.format(newDate);
    }

}
