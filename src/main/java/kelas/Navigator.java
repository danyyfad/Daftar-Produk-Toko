package kelas;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kelas.data.DataProduk;
import kelas.model.Produk;
import kelas.controller.FormController;
import kelas.controller.DaftarController;
import kelas.controller.DetailController;

public class Navigator {

    private Stage stage;
    private DataProduk data;
    private Scene scene;

    public Navigator(Stage stage, DataProduk data) {
        this.stage = stage;
        this.data = data;
    }

    public void bukaForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/kelas/form.fxml"));
        Parent halaman = loader.load();

        FormController controller = loader.getController();
        controller.setKonteks(this, data);

        tampilkan(halaman);
    }

    public void bukaDaftar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/kelas/daftar.fxml"));
        Parent halaman = loader.load();

        DaftarController controller = loader.getController();
        controller.setKonteks(this, data);

        tampilkan(halaman);
    }

    public void bukaDetail(Produk produk) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/kelas/detail.fxml"));
        Parent halaman = loader.load();

        DetailController controller = loader.getController();
        controller.setData(this, produk);

        tampilkan(halaman);
    }

    private void tampilkan(Parent halaman) {
        if (scene == null) {
            scene = new Scene(halaman, 900, 620);
            scene.getStylesheets().add(getClass().getResource("/kelas/styles/app.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Daftar Produk Toko");
            stage.setMinWidth(680);
            stage.setMinHeight(500);
        } else {
            scene.setRoot(halaman);
        }

        stage.show();
    }
}
