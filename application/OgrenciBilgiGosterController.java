package application;

import java.sql.ResultSet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class OgrenciBilgiGosterController {
	YetkiliOgrenciLoginController yolc=new YetkiliOgrenciLoginController();
	
	Kontroller kontroller= new Kontroller();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAnaMenu;
    
    @FXML
    private AnchorPane orgenciGoster;

    @FXML
    private DatePicker datePicerDateY;


    @FXML
    private TextField txtAd;

    @FXML
    private TextArea txtAdres;

    @FXML
    private TextField txtCinsiyet;

    @FXML
    private TextField txtOrgNo;

    @FXML
    private TextField txtSoyad;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txt_Eposta;

    @FXML
    private TextField txt_bolum;

    @FXML
    private TextField txt_fakulte;

    @FXML
    private TextField txt_sinif;

    @FXML
    void btnAnaMenu_click(ActionEvent event) {
    	kontroller.FormDegistir("AnaMenu.fxml", orgenciGoster, 302, 399, "Ana Menüe Geçilemedi");
    }

    @FXML
    void btnCik_click(ActionEvent event) {
    	kontroller.cikis();
    }
    
    @FXML
    void initialize() {
    	String id=yolc.orgno2() ;
       // System.out.println(id);
    	try {
    		// giriş yaptiktan sonra öğrencinin id'e göre bilgileri geliyor 
    		
            ResultSet resultset = VeritabaniController.OrgGet(id);
            
            if(resultset.next()) {
   
            	txtAd.setDisable(true);
            	txtAd.setText(resultset.getString("ad"));
            	txtSoyad.setDisable(true);
            	txtSoyad.setText(resultset.getString("soyad"));
           	    txtTel.setDisable(true);
           	    txtTel.setText(resultset.getString("tel"));
           	    txtAdres.setDisable(true);
             	txtAdres.setText(resultset.getString("adres"));
             	txt_Eposta.setDisable(true);
             	txt_Eposta.setText(resultset.getString("e_posta"));
             	txtOrgNo.setText(resultset.getString("orgno"));
             	txtOrgNo.setDisable(true);
           	    txt_sinif.setText(resultset.getString("sinif"));
           	    txt_sinif.setDisable(true);
	            txt_bolum.setDisable(true);
	            txt_bolum.setText(resultset.getString("fakulte_adi"));
	            txt_fakulte.setDisable(true);
	            txt_fakulte.setText(resultset.getString("bolum_adi"));
	            if(resultset.getInt("cinsiyet")==1) {
	            	txtCinsiyet.setText("Kız");
	            }
	            else if (resultset.getInt("cinsiyet")==0){
	            	txtCinsiyet.setText("Erkek");
	            }
                txtCinsiyet.setDisable(true);
           	datePicerDateY.setDisable(true);
           	 datePicerDateY.setValue(resultset.getDate("kayitTarhi").toLocalDate());

            }
             
       	}
       	catch (Exception e) {
       	   kontroller.AlertErrorVeritabani(e.getMessage().toString());
   		}

    }

}
