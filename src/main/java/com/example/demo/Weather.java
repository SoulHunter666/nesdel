package com.example.demo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class Weather {
    public static String GetGeodata(String ss) {
        String s1;
        try {
            Document document = Jsoup.connect("https://world-weather.ru/pogoda/russia/" + ss + "/").get();
            String element = document.select("#weather-now-description>dl>dd").text();
            s1 = element;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return s1;
    }

    public static String convertCyrilic(String message) {
        char[] abcCyr = {' ', 'а', 'б', 'в', 'г', 'д', 'ѓ', 'е', 'ж', 'з', 'ѕ', 'и', 'й', 'к', 'л', 'љ', 'м', 'н', 'њ', 'о', 'п', 'р', 'с', 'т', 'ќ', 'у', 'ф', 'х', 'ц', 'ч', 'џ', 'ш', 'ы' ,'я', 'А', 'Б', 'В', 'Г', 'Д', 'Ѓ', 'Е', 'Ж', 'З', 'Ѕ', 'И', 'Ј', 'К', 'Л', 'Љ', 'М', 'Н', 'Њ', 'О', 'П', 'Р', 'С', 'Т', 'Ќ', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Џ', 'Ш', 'Я' , 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/', '-', '_'};
        String[] abcLat = {"_", "a", "b", "v", "g", "d", "]", "e", "zh", "z", "y", "i", "y", "k", "l", "q", "m", "n", "w", "o", "p", "r", "s", "t", "'", "u", "f", "kh", "ts", "ch", "x", "sh" , "y", "ya" , "A", "B", "V", "G", "D", "}", "E", "Zh", "Z", "Y", "I", "J", "K", "L", "Q", "M", "N", "W", "O", "P", "R", "S", "T", "KJ", "U", "F", "Kh", "C", "ch", "X", "Sh", "Ya", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "Kh", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "/", "_", "_" };
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++) {
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }
        return builder.toString();
    }

    static String Now(String ss) {
        String s2 = null;
        try {
            Document document = Jsoup.connect("https://world-weather.ru/pogoda/russia/" + ss + "/").get();
            String element = document.select("#weather-now-icon").toString();
            String s1 = element;
            int ind = s1.indexOf("Пасмурно");
            if (ind != -1) {
                s2 = "Пасмурно";
            }
            int ind1 = s1.indexOf("Ясно");
            if (ind1 != -1) {
                s2 = "Ясно";
            }
            int ind2 = s1.indexOf("Слабый дождь");
            if (ind2 != -1) {
                s2 = "Слабый дождь";
            }
            int ind3 = s1.indexOf("Кратковременные осадки");
            if (ind3 != -1) {
                s2 = "Кратковременные осадки";
            }
            int ind4 = s1.indexOf("Дождь со снегом");
            if (ind4 != -1) {
                s2 = "Дождь со снегом";
            }
            int ind5 = s1.indexOf("Сильный снег");
            if (ind5 != -1) {
                s2 = "Сильный снег";
            }
            int ind6 = s1.indexOf("Небольшой снег с дождем");
            if (ind6 != -1) {
                s2 = "Небольшой снег с дождем";
            }
            int ind7 = s1.indexOf("Облачно и слабый снег");
            if (ind7 != -1) {
                s2 = "Облачно и слабый снег";
            }
            int ind8 = s1.indexOf("Преимущественно облачно");
            if (ind8 != -1) {
                s2 = "Преимущественно облачно";
            }
            int ind9 = s1.indexOf("Местами сильный дождь");
            if (ind9 != -1) {
                s2 = "Местами сильный дождь";
            }
            int ind10 = s1.indexOf("Снег");
            if (ind10 != -1) {
                s2 = "Снег";
            }
            int ind11 = s1.indexOf("Сильный дождь");
            if (ind11 != -1) {
                s2 = "Сильный дождь";
            }
            int ind12 = s1.indexOf("Дождь");
            if (ind12 != -1) {
                s2 = "Дождь";
            }
            int ind13 = s1.indexOf("Незначительная облачность");
            if(ind13 != -1){
                s2 = "Незначительная облачность";
            }
            int ind14 = s1.indexOf("Слабый снег");
            if(ind14 != -1){
                s2 = "Слабый снег";
            }
            int ind15 = s1.indexOf("Частично облачно");
            if(ind15 != -1){
                s2 = "Частично облачно";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return s2;
    }
}
