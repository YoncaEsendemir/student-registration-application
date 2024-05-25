package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.sql.*;

public class YetkiliTabloGosterController {

	Kontroller kontroller = new Kontroller();	
     YetkiliIslemleriController controller = new YetkiliIslemleriController();
	
   static  int idDuzenGetir=0;
   static String btnDeger;
   String sql;
   
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Cik;

    @FXML
    private TextField arama;
    
    @FXML
    private TableView<KayitClass> OrgGoster;

    @FXML
    private  Button btn_degerGetir;
    
    @FXML
    private TableColumn<KayitClass, String> colid;

    
    @FXML
    private TableColumn<KayitClass, String> colAd;

    @FXML
    private TableColumn<KayitClass, String> colBolum;

    @FXML
    private TableColumn<KayitClass, String> colFakulte;

    @FXML
    private TableColumn<KayitClass, String> colNo;

    @FXML
    private TableColumn<KayitClass, String> colSoyad;
    
    @FXML
    private AnchorPane gosterform;

    @FXML
    void OrgGoster_clicked(MouseEvent event) { 
    	// üzerine tikladiğimiz öğrencinin id'sini alir ve DuzenleControllerda silme ve id göre öğrenciyi getirme için kullanılır  bilgilerini getirmek için 
    	
    	KayitClass kayitClass = new KayitClass();
    	kayitClass=(KayitClass) OrgGoster.getItems().get(OrgGoster.getSelectionModel().getSelectedIndex());
    	  idDuzenGetir = kayitClass.getId();	
    	// System.out.println(idDuzenGetir);
    	 IdGetirduzenle(kayitClass.getId());
    	btn_degerGetir.setDisable(false);
    }
    
    public int IdGetirduzenlevoid() {
    	return	IdGetirduzenle(idDuzenGetir);
    }
    
    
   public int IdGetirduzenle(int id) {
	   //System.out.println(id);
	   return id;
   }

    
    @FXML
    void btn_degerGetir_click(ActionEvent event) {
    	 btnDeger=btn_degerGetir.getText();
    	
    	controller.FormdDe();

    	
    }
    
    @FXML
    void btn_Cik_click(ActionEvent event) {
    	kontroller.cikis();
    }
    
    @FXML
    void txt_arama_keyPressed(KeyEvent event) {
    	// oğrenci no ve ada göre arama yapar
            if(arama.getText().equals("")) {
            	// tabloya değerler getirir txt_arama boşsa
        		 sql="SELECT orgkayit.id, orgkayit.orgno, orgkayit.ad, orgkayit.soyad, bolumler.bolum_adi, fakulteler.fakulte_adi From orgkayit INNER JOIN bolumler  ON orgkayit.bolum=bolumler.bolum_id "+
        				" INNER JOIN fakulteler ON bolumler.fakulte_id=fakulteler.fakulte_id";
    		    try {
    		        VeritabaniController.GetOrg(OrgGoster,sql,colid,colNo,colAd,colSoyad,colFakulte,colBolum);
    		    }
        	catch (Exception e) {
    			// TODO: handle exception
                //System.out.println(e);
        		kontroller.AlertErrorVeritabani(e.getMessage().toString());
    		}
            }
            else {
            	// sql="SELECT * FROM islemler where islemAciklama like '%"+txt_arama.getText()+"%' or user like '%"+txt_arama.getText()+"%'";
            	sql="SELECT orgkayit.id, orgkayit.orgno, orgkayit.ad, orgkayit.soyad, bolumler.bolum_adi, fakulteler.fakulte_adi From orgkayit INNER JOIN bolumler  ON orgkayit.bolum=bolumler.bolum_id "+
        				" INNER JOIN fakulteler ON bolumler.fakulte_id=fakulteler.fakulte_id WHERE orgkayit.ad like '%"+arama.getText()+"%' OR orgkayit.orgno like '%"+arama.getText()+"%'";
            	
    		    try {
    		        VeritabaniController.GetOrg(OrgGoster,sql,colid,colNo,colAd,colSoyad,colFakulte,colBolum);
    		    }
        	catch (Exception e) {
    			// TODO: handle exception
                //System.out.println(e);
        		kontroller.AlertErrorVeritabani(e.getMessage().toString());
    		}
            }
    }

    
    public void veriAktar(String veri) {
    	this.btn_degerGetir.setText(veri);
    }
    
    @FXML
    void initialize() {
    	btn_degerGetir.setDisable(true);  
    	// tabloya değerler getirir 
    		String sql="SELECT orgkayit.id, orgkayit.orgno, orgkayit.ad, orgkayit.soyad, bolumler.bolum_adi, fakulteler.fakulte_adi From orgkayit INNER JOIN bolumler  ON orgkayit.bolum=bolumler.bolum_id "+
    				" INNER JOIN fakulteler ON bolumler.fakulte_id=fakulteler.fakulte_id";
		    try {
		        VeritabaniController.GetOrg(OrgGoster,sql,colid,colNo,colAd,colSoyad,colFakulte,colBolum);
		    }
    	catch (Exception e) {
			// TODO: handle exception
            //System.out.println(e);
    		kontroller.AlertErrorVeritabani(e.getMessage().toString());
		}
    }
    
    public String degerbtnDeger2() {
 	   
       	return	degerbtnDeger(btnDeger);
       	
       }

       
       public String degerbtnDeger(String BtnDeger) {
    	   if(BtnDeger.equals("Düzenle")) {
    		 return  "Düzenle"; 
    	   }
    	   else if(BtnDeger.equals("Göster")) {
    		   return  "Göster";
    	   }
    	   
    	   return " ";
       }
    

    
}
