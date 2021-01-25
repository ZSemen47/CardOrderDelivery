package ru.netology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    private static LocalDate time = LocalDate.now();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String calculatedData(int Days) {
        LocalDate newDate = time.plusDays(Days);
        return formatter.format(newDate);
    }
}
