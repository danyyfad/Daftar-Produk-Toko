package kelas.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import kelas.Navigator;
import kelas.model.Produk;

public class DetailController {

    @FXML
    private Label kodeLabel;

    @FXML
    private Label namaLabel;

    @FXML
    private Label kategoriLabel;

    @FXML
    private Label hargaLabel;

    private Navigator navigator;

    public void setData(Navigator nav, Produk produk) {
        navigator = nav;

        kodeLabel.setText(produk.getKode());
        namaLabel.setText(produk.getNama());
        kategoriLabel.setText(produk.getKategori());
        hargaLabel.setText(NumberFormat.getCurrencyInstance(new Locale("id", "ID"))
                .format(produk.getHarga()));
    }

    @FXML
    private void tambahLagi() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bukaDaftar() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
