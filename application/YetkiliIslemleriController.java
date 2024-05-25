package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class YetkiliIslemleriController {
	

	   Kontroller kontroller = new Kontroller();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane SagAncherPane;

    @FXML
    private AnchorPane YetkiliIslemForm;
    
    @FXML
    private Label lblAltBas1;

    @FXML
    private Label lblAltBas2;

    @FXML
    private Label lblAltBas3;

    @FXML
    private Label lblAltBas4;

    @FXML
    private Button btnAnaMenu;

    @FXML
    private Button btnCik;

    @FXML
    private Button btnKayitGuncele;

    @FXML
    private Button btnKayitListe;

    @FXML
    private Button btnRapor;

    @FXML
    private Pane panelYetkili;

    
    @FXML
    private AnchorPane PaneRaporGoster;
    
    @FXML
    void btnAnaMenu_click(ActionEvent event) {
    	kontroller.FormDegistir("AnaMenu.fxml", YetkiliIslemForm, 302, 399, "Ana Menüe Geçilemedi");
    }

    @FXML
    void btnCik_click(ActionEvent event) {
    	kontroller.cikis();
    }

    @FXML
    void btnKayitGuncele_click(ActionEvent event) {
		try {
     		FXMLLoader load= new FXMLLoader(getClass().getResource("YetkiliTabloGoster.fxml"));
     		AnchorPane panel1 =(AnchorPane) load.load();
     		
    		//  AnchorPane panel1=(AnchorPane)FXMLLoader.load(getClass().getResource("YetkiliTabloGoster.fxml"));
      		
    		  YetkiliTabloGosterController ytg= load.getController();
    		  ytg.veriAktar("Düzenle");
    		  SagAncherPane.getChildren().setAll(panel1);
    	}
    	catch(Exception e) {
    		 e.printStackTrace();
    	} 
    }

    @FXML
    void btnKayitListe_click(ActionEvent event) {
    	try {
    		//YetkiliDuzenleBilgiler.fxml
     		FXMLLoader load= new FXMLLoader(getClass().getResource("YetkiliTabloGoster.fxml"));
     		AnchorPane panel1 =(AnchorPane) load.load();
     		
  		  YetkiliTabloGosterController ytg= load.getController();
  		  ytg.veriAktar("Göster");
  		 
  		  SagAncherPane.getChildren().setAll(panel1);
  		  
  		  
  		  
  	}
  	catch(Exception e) {
  		 System.out.println(e);
  	}
    }

    @FXML
    void btnRapor_click(ActionEvent event) {
		try {
     		FXMLLoader load= new FXMLLoader(getClass().getResource("YetkiliTabloGoster.fxml"));
     	//	AnchorPane panel1 =(AnchorPane) load.load();
     		
     		  SagAncherPane.getChildren().setAll(PaneRaporGoster);
    		  
    	}
    	catch(Exception e) {
    		 e.printStackTrace();
    	}
    }

    @FXML
    void initialize() {
       

    }
    
    
    public void FormdDe() {
    	try {
    		/*
     		FXMLLoader load= new FXMLLoader(getClass().getResource("YetkiliTabloGoster.fxml"));
     		
     		AnchorPane panel1 =null;
     		
  		  YetkiliTabloGosterController ytg= load.getController();
  		  panel1 = ytg.asd();
  		  SagAncherPane.getChildren().setAll(panel1);

    		*/
    		
    		Stage stageK = new Stage();
    		//"Kayit.fxml"
       	  // FXML Loader load= new FXMLLoader(getClass().getResource("YetkiliIslemleri.fxml"));
         // AnchorPane panek =(AnchorPane) load.load();
    		
    		
    		
      	   FXMLLoader load2= new FXMLLoader(getClass().getResource("YetkiliDuzenleBilgiler.fxml"));
     		AnchorPane pane2 =(AnchorPane) load2.load();
     
     		
              Scene scene= new Scene(pane2,640,495);
             
              stageK.initStyle(StageStyle.TRANSPARENT );
              stageK.setScene(scene);
              stageK.setX(540);
              stageK.setY(115);
              stageK.show();
              
            //  ((Stage)YetkiliIslemForm.getScene().getWindow()).close();
    		
        	 

    	}
    	catch (Exception e) {
			// TODO: handle exception

      	
           System.out.println(e);
		}

    }

}




    
