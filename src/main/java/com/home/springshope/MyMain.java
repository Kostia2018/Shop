package com.home.springshope;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MyMain {

    public static void main(String[] args) throws IOException {
        System.out.println("привет");


        LocalDate myDate = LocalDate.of(2022, 1, 20);
        LocalDate myDateN = myDate.plusMonths(2);

        LocalDate dateTwo = LocalDate.now().plusMonths(3);

        System.out.println(myDate);

        System.out.println(dateTwo);

        System.out.println(myDateN);


        String formatdate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String formatdate2 = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);


        String myFormat = LocalDate.now().format(DateTimeFormatter.ofPattern("dd:MM:YYYY"));

        System.out.println(myFormat);


    }


}







