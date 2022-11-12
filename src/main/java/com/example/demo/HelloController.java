package com.example.demo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HelloController {
public String s2;
    @FXML
    private ImageView Close;

    @FXML
    private ImageView Minimize;

    @FXML
    private AnchorPane UpPane;
    private double x, y;
    @FXML
    private Label time;

    @FXML
    private TextField city;
    @FXML
    private Button getWeather;

    @FXML
    private Label osh;

    @FXML
    private Label dav;

    @FXML
    private Label vet;

    @FXML
    private Label vlag;

    @FXML
    private Label nazG;

    @FXML
    private ImageView pog1;

    @FXML
    private ImageView pog2;

    @FXML
    private Label pogT;
    @FXML
    private Pane pogPane;

    @FXML
    private Pane geoPane;

    @FXML
    private ImageView pog3;

    @FXML
    private ImageView pog4;

    @FXML
    private  ImageView pog6;

    @FXML
    private ImageView pog5;

    @FXML
    void initialize(Stage stage) {
        UpPane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        UpPane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        Close.setOnMouseClicked(mouseEvent -> stage.close());
        Minimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        getWeather.setOnMouseClicked(mouseEvent -> {
            try {
                String s1 = city.getText();
                s1 = Weather.convertCyrilic(s1);
                s2 = s1;
                Document document;
                String temperature;
                int sp1 = s1.indexOf("sankt_peterburg");
                int sp2 = s1.indexOf("Sankt_Peterburg");
                int sp3 = s1.indexOf("sankt_Peterburg");
                int sp4 = s1.indexOf("Sankt_peterburg");
                int m1 = s1.indexOf("Moskva");
                int m2 = s1.indexOf("moskva");
                String m = "moscow";
                String sp = "saint_petersburg";
                if (sp1 != -1 || sp2 != -1 || sp3 != -1 || sp4 != -1) {
                    s2 = sp;
                }
                if(m1 != -1|| m2 != -1){
                    s2 = m;
                }
                    if (s2 == sp) {
                        document = Jsoup.connect("https://world-weather.ru/pogoda/russia/saint_petersburg/").get();
                        temperature = document.select("#weather-now-number").text();
                    } else if (s2 == m) {
                        document = Jsoup.connect("https://world-weather.ru/pogoda/russia/moscow/").get();
                        temperature = document.select("#weather-now-number").text();

                    } else {
                        document = Jsoup.connect("https://world-weather.ru/pogoda/russia/" + s2 + "/").get();
                        temperature = document.select("#weather-now-number").text();
                    }
                    String t1 = Weather.GetGeodata(s2);
                    String kp = Weather.Now(s2);
                    pogT.setText("Сейчас: " + kp);

                    if (kp == "Дождь" || kp == "Сильный дождь" || kp == "Слабый дождь" || kp == "Кратковременные осадки" || kp == "Местами сильный дождь") {
                        pog1.setVisible(true);
                        pog2.setVisible(true);
                    } else {
                        pog1.setVisible(false);
                        pog2.setVisible(false);
                    }
                    if(kp == "Снег" || kp == "Сильный снег" || kp == "Слабый снег" || kp == "Дождь со снегом" || kp == "Небольшой снег с дождем" || kp == "Облачно и слабый снег"){
                        pog3.setVisible(true);
                        pog4.setVisible(true);
                    }
                    else {
                        pog3.setVisible(false);
                        pog4.setVisible(false);
                    }
                    if(kp == "Пасмурно" || kp == "Преимущественно облачно" || kp == "Незначительная облачность" || kp == "Частично облачно"){
                        pog6.setVisible(true);
                    }
                    else {
                        pog6.setVisible(false);
                    }
                    if(kp == "Ясно"){
                        pog5.setVisible(true);
                    }
                    else {
                        pog5.setVisible(false);
                    }
                    pogPane.setVisible(true);
                    geoPane.setVisible(true);
                    osh.setText(t1.substring(0, 4));
                    dav.setText(t1.substring(4, 19));
                    vlag.setText(t1.substring(19, 23));
                    vet.setText(t1.substring(23, 31).replace(",", " "));
                    nazG.setVisible(true);
                    nazG.setText(city.getText() + " сейчас: " + temperature);



            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Упс :(");
                alert.setHeaderText("Похоже, что такого города не существует");
                alert.setContentText("Попробуй ещё раз! P.S. Кстати города России только");
                Stage s1 = (Stage) alert.getDialogPane().getScene().getWindow();
                s1.getIcons().add(new Image(this.getClass().getResource("117-20211109_125630-2048x2007.png").toString()));
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                    }
                });
            }


        });

    }
    }





