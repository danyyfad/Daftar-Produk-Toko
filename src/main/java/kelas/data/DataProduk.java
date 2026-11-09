package kelas.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kelas.model.Produk;

public class DataProduk {

    private ObservableList<Produk> daftarProduk = FXCollections.observableArrayList();
    private int nomor = 1;

    public ObservableList<Produk> getDaftarProduk() {
        return daftarProduk;
    }

    public void tambahProduk(String nama, String kategori, double harga) {
        String kode = String.format("PRD%03d", nomor);
        Produk produk = new Produk(kode, nama, kategori, harga);
        daftarProduk.add(produk);
        nomor++;
    }
}
