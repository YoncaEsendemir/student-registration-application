package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AnaMenuController {

	 Kontroller kontroller= new Kontroller();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane Form1;

    @FXML
    private Button btn_Ogrenci_kayit;

    @FXML
    private Button btn_cikis;

    @FXML
    private Button btn_giris;

    @FXML
    void btn_Ogrenci_kayit_click(ActionEvent event) {
          /*
    	try {
    		Stage stageK = new Stage();
    		
      	   FXMLLoader load= new FXMLLoader(getClass().getResource("Kayit.fxml"));
     		AnchorPane panek =(AnchorPane) load.load();
              Scene scene= new Scene(panek,560,579);
              stageK.initStyle(StageStyle.TRANSPARENT );
              stageK.setScene(scene);
              stageK.show();
    		
        	 ((Stage)Form1.getScene().getWindow()).close();

    	}
    	catch (Exception e) {
			// TODO: handle exception
           	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Form Geçişi");
        	alert.setHeaderText("Form Geçişi hata");
        	alert.setContentText("Yetkili Islemleri sayfasina geçilemedi" + e);
        	alert.showAndWait();
        	
           System.out.println(e);
		}*/
    	
    	kontroller.FormDegistir("Kayit.fxml", Form1,560, 579,"Öğrenci Kayit Formuna Geçilemedi!");

    }
 
    @FXML
    void btn_cikis_click(ActionEvent event) {

    	kontroller.cikis();
    	
    }

    @FXML
    void btn_giris_click(ActionEvent event) {

    	kontroller.FormDegistir("YetkiliOgrenciLogin.fxml", Form1,375,411,"Yetkili Islemleri sayfasina geçilemedi");;
    }

    @FXML
    void initialize() { 
    	
    	
    }

}