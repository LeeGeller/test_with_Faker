package org.example;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerate {

    private DataGenerate() {

    }
    public static String date(int day) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    public static String generateCity() {


        var cities = new String[]{"Москва", "Псков", "Майкоп"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {

        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {

        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {

        }


        public static UserInfo generateUser(String locale) {
            UserInfo userInfo = new UserInfo(generateCity(), generateName(locale), generatePhone(locale));

            return userInfo;
        }
    }


    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }

}
