package com.example.proje2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UygulamaArayuzu extends Application{
    private static final String DOGRU_KULLANICI_ADI = "admin";
    private static final String DOGRU_SIFRE = "adminpass";
    private Kullanici aktifKullanici;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Giriş Ekranı");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label kullaniciAdiLabel = new Label("Kullanıcı Adı:");
        grid.add(kullaniciAdiLabel, 0, 1);

        TextField kullaniciAdiField = new TextField();
        grid.add(kullaniciAdiField, 1, 1);

        Label sifreLabel = new Label("Şifre:");
        grid.add(sifreLabel, 0, 2);

        PasswordField sifreField = new PasswordField();
        grid.add(sifreField, 1, 2);

        Button girisButton = new Button("Giriş");
        grid.add(girisButton, 1, 4);

        girisButton.setOnAction(e -> {
            String kullaniciAdi = kullaniciAdiField.getText();
            String sifre = sifreField.getText();

            try {
                kontrol(kullaniciAdi, sifre);
                // Giriş başarılıysa ana ekranı göster.
                showAnaEkran(primaryStage, kullaniciAdi);
            } catch (yanlisbilgi ex) {
                // Yanlış kullanıcı adı veya şifre durumunda hata mesajı göster.
                showErrorAlert("Geçersiz Kullanıcı Adı veya Şifre", "Lütfen doğru bilgileri girin.");
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void kontrol(String kullaniciAdi, String sifre) throws yanlisbilgi {
        if (!kullaniciAdi.equals(DOGRU_KULLANICI_ADI) || !sifre.equals(DOGRU_SIFRE)) {
            throw new yanlisbilgi();
        }
    }

    private void showAnaEkran(Stage primaryStage, String kullaniciAdi) {

        Stage anaEkranStage = new Stage();
        anaEkranStage.setTitle("Ana Ekran");

        aktifKullanici = new Kullanici(kullaniciAdi, "geçerli bir şifre", "geçerli bir email");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label hosgeldinLabel = new Label("Hoş Geldin, " + kullaniciAdi + "!");
        grid.add(hosgeldinLabel, 0, 0, 2, 1);

        Label takipEdilenIsAlanlariLabel = new Label("Takip Edilen İş Alanları:");
        grid.add(takipEdilenIsAlanlariLabel, 0, 1);

        ListView<String> isAlanlariList = new ListView<>();
        ArrayList<TakipEdilenSosyalMedya> takipEdilenIsAlanlari = aktifKullanici.getTakipEdilenIsAlanlari();

        // İş alanları eklenmesi
        com.example.proje2.IsAlani doktorIsAlani = new com.example.proje2.IsAlani("Doktorlar", "Sağlık");
        com.example.proje2.IsAlani satisYapanIsAlani = new com.example.proje2.IsAlani("Satış Yapan Kişiler", "Pazarlama");
        com.example.proje2.IsAlani kisiselGelisimUzmanlariIsAlani = new com.example.proje2.IsAlani("Kişisel Gelişim Uzmanları", "Eğitim");
        com.example.proje2.IsAlani yayincilarIsAlani = new com.example.proje2.IsAlani("Yayıncılar", "Medya");

        isAlanlariList.getItems().clear();

        isAlanlariList.getItems().add(doktorIsAlani.getIsAlaniAdi() + " (" + doktorIsAlani.getKategori() + ")");
        isAlanlariList.getItems().add(satisYapanIsAlani.getIsAlaniAdi() + " (" + satisYapanIsAlani.getKategori() + ")");
        isAlanlariList.getItems().add(kisiselGelisimUzmanlariIsAlani.getIsAlaniAdi() + " (" + kisiselGelisimUzmanlariIsAlani.getKategori() + ")");
        isAlanlariList.getItems().add(yayincilarIsAlani.getIsAlaniAdi() + " (" + yayincilarIsAlani.getKategori() + ")");

        isAlanlariList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Seçilen iş alanına göre detaylı ekranı göster
            if (newValue != null) {
                String secilenIsAlaniAdi = newValue.split(" \\(")[0];
                showIsAlaniDetayEkrani(anaEkranStage, secilenIsAlaniAdi);
            }
        });

        grid.add(isAlanlariList, 0, 2, 2, 1);

        Scene scene = new Scene(grid, 400, 300);
        anaEkranStage.setScene(scene);
        anaEkranStage.show();

        primaryStage.close();
    }



    private void showIsAlaniDetayEkrani(Stage primaryStage, String isAlaniAdi) {
        // İş alanı detay ekranı
        Stage isAlaniDetayStage = new Stage();
        isAlaniDetayStage.setTitle(isAlaniAdi + " Detayları");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label detayLabel = new Label(isAlaniAdi);
        grid.add(detayLabel, 0, 0);

        Label detayLabel1 = new Label("Erkek=30 - Kadın=70");
        grid.add(detayLabel1, 1, 1);

        Label detayLabel2 = new Label("20-24=25 - 24-28=35 - 28-32=40");
        grid.add(detayLabel2, 1, 2);

        // Cinsiyet ve yaş aralığı için pie chart
        PieChart cinsiyetChart = createCinsiyetChart();
        PieChart yasAraligiChart = createYasAraligiChart();

        grid.add(cinsiyetChart, 1, 3);
        grid.add(yasAraligiChart, 2, 3);

        Scene scene = new Scene(grid, 600, 400);
        isAlaniDetayStage.setScene(scene);
        isAlaniDetayStage.show();
    }

    private PieChart createCinsiyetChart() {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Erkek", 30),
                        new PieChart.Data("Kadın", 70));

        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Cinsiyet Dağılımı");

        return chart;
    }

    private PieChart createYasAraligiChart() {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("20-24", 25),
                        new PieChart.Data("24-28", 35),
                        new PieChart.Data("28-32", 40));

        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Yaş Aralığı Dağılımı");

        return chart;
    }private void showErrorAlert(String baslik, String icerik) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(baslik);
        alert.setContentText(icerik);
        alert.showAndWait();
    }

    // InvalidCredentialsException özel istisnası
    private static class yanlisbilgi extends Exception {
    }
}
