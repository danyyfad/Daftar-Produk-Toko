package kelas.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class DaftarController {

    @FXML
    private TableView<Produk> tabel;

    @FXML
    private TableColumn<Produk, String> kodeCol;

    @FXML
    private TableColumn<Produk, String> namaCol;

    @FXML
    private TableColumn<Produk, String> kategoriCol;

    @FXML
    private TableColumn<Produk, String> hargaCol;

    @FXML
    private TextField cariField;

    @FXML
    private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;
    private ObservableList<Produk> hasilCari = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        kodeCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getKode()));
        namaCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNama()));
        kategoriCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getKategori()));
        hargaCol.setCellValueFactory(d -> new SimpleStringProperty(
                NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(d.getValue().getHarga())));

        tabel.setItems(hasilCari);
    }

    public void setKonteks(Navigator nav, DataProduk dataProduk) {
        navigator = nav;
        data = dataProduk;
        cari();
    }

    @FXML
    private void cari() {
        if (data == null) {
            return;
        }

        String kata = "";
        if (cariField.getText() != null) {
            kata = cariField.getText().toLowerCase();
        }

        hasilCari.clear();
        for (Produk p : data.getDaftarProduk()) {
            if (p.getNama().toLowerCase().contains(kata)) {
                hasilCari.add(p);
            }
        }

        pesanLabel.setText("Jumlah produk: " + hasilCari.size());
    }

    @FXML
    private void resetPencarian() {
        cariField.clear();
        cari();
    }

    @FXML
    private void tambah() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            pesanLabel.setText("Form tidak bisa dibuka.");
        }
    }

    @FXML
    private void detail() {
        Produk dipilih = tabel.getSelectionModel().getSelectedItem();

        if (dipilih == null) {
            pesanLabel.setText("Pilih produk dulu pada tabel.");
            return;
        }

        try {
            navigator.bukaDetail(dipilih);
        } catch (IOException e) {
            pesanLabel.setText("Detail produk tidak bisa dibuka.");
        }
    }
}
