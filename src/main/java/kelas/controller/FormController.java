package kelas.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kelas.Navigator;
import kelas.data.DataProduk;

public class FormController {

    @FXML
    private TextField namaField;

    @FXML
    private ComboBox<String> kategoriBox;

    @FXML
    private TextField hargaField;

    @FXML
    private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;

    @FXML
    public void initialize() {
        kategoriBox.getItems().add("Makanan");
        kategoriBox.getItems().add("Minuman");
        kategoriBox.setValue("Makanan");
    }

    public void setKonteks(Navigator nav, DataProduk dataProduk) {
        navigator = nav;
        data = dataProduk;
    }

    @FXML
    private void simpan() {
        try {
            String nama = namaField.getText();
            String kategori = kategoriBox.getValue();
            String hargaText = hargaField.getText().replace(",", ".");
            double harga = Double.parseDouble(hargaText);

            data.tambahProduk(nama, kategori, harga);
            navigator.bukaDaftar();

        } catch (NumberFormatException e) {
            pesanLabel.setText("Harga harus diisi dengan angka.");
        } catch (IllegalArgumentException e) {
            pesanLabel.setText(e.getMessage());
        } catch (IOException e) {
            pesanLabel.setText("Tidak bisa membuka halaman daftar.");
        }
    }

    @FXML
    private void bukaDaftar() {
        try {
            navigator.bukaDaftar();
        } catch (IOException e) {
            pesanLabel.setText("Halaman daftar tidak bisa dibuka.");
        }
    }
}
