package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.MySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import java.sql.*;

public class KayitControlleryeni {
//	private static final String String = null;
//	VertabaniController veritabanicontroller = new VertabaniController();
    Kontroller kontroller= new Kontroller();
	Alert alert = new Alert(AlertType.ERROR);
	Alert alert3 = new Alert(AlertType.CONFIRMATION);
	

    
    ButtonType btn1=new ButtonType("Evet");
    ButtonType btn2 = new ButtonType("Hayir");
    ButtonType btn4 = new ButtonType("Tamam",ButtonData.OK_DONE);

	/*
	public String Kangb(String kanGb) {
	if(!kanGb.isEmpty() && (kanGb=="0 Rh+" && kanGb=="0 Rh-" && kanGb=="AB Rh+" && kanGb=="AB Rh-" && kanGb=="A Rh+" && kanGb=="A Rh-" && kanGb=="B Rh+" && kanGb=="B Rh-"))
				{
			  return kanGb;
			}
			return "";
	}
	
	*/
	public void btnKontrol() {
		
		if(rdbtn_erkek.isSelected()==true) {
			 rdbtn_kiz.setDisable(true);
			
			 
		}
		else if(rdbtn_kiz.isSelected()==true) {
			rdbtn_erkek.setDisable(true);
		}
		
		if(rdbtn_erkek.isSelected()==false) {
			rdbtn_kiz.setDisable(false);
		}
		 if(rdbtn_kiz.isSelected()==false) {
			rdbtn_erkek.setDisable(false);
		}
		
		/*
   	 if( rdbtn_erkek.isSelected()==true) {
		 
		 rdbtn_kiz.setDisable(true);
	 }*/
	}
    @FXML  private Label lblsifre;
	
    @FXML private ResourceBundle resources;

    @FXML private URL location;

    @FXML private Button btn_kayitOrg;

    @FXML private ComboBox<String> combx_bolum;

    @FXML private ComboBox<String> combx_fakulte;

    @FXML private ComboBox<String> combx_sinif;
    
    @FXML private ComboBox<String> combx_kanGb;

    @FXML private ImageView img_foto;

    @FXML private RadioButton rdbtn_erkek;

    @FXML private RadioButton rdbtn_kiz;

    @FXML private TextField txt_adK;
    
    @FXML  private AnchorPane OrgKayit;
    
    @FXML
    private Button btn_geri;
    
    @FXML private TextField txt_soyadK;
    
    @FXML
    private TextField txt_tel;
   
    @FXML
    private TextField txt_AnneAd;
    

    @FXML
    private TextField txt_Eposta;
   
    @FXML
    private TextArea txt_adres;
    
    @FXML
    private TextField txt_BabaAd;
    
    @FXML
    private TextField txt_acilTel;

    @FXML
    private Button btn_Cik;
    
    
    @FXML  
    private PasswordField txt_sifreK;
    

    ObservableList<KayitClass>orgKayit;
       
    String ad,soyad,anneAd,babaAd,adres,tel,ePosta,sifre,no,sinif,bolum,fakulte="";
    int  fakulte_id = 0,bolum_id=0;
    String kanGrup,acililetisimTel="";
    int cinsiyet;
    
    @FXML
    private Label lblNo;
    
    @FXML
    void btn_Cik_click(ActionEvent event) {

    	kontroller.cikis();
    }
  
    @FXML
    void btn_geri_click(ActionEvent event) {
    	kontroller.FormDegistir("AnaMenu.fxml",OrgKayit, 302, 399, "Ana Menüe Geçilemedi");
    }
   
    
    @FXML
    void btn_kayitOrg_click(ActionEvent event) {
    	
    	ad=kontroller.TekHarf(txt_adK.getText());
    	soyad= kontroller.TekHarf(txt_soyadK.getText().trim());
    	anneAd=kontroller.TekHarf(txt_AnneAd.getText().trim());
    	babaAd=kontroller.TekHarf(txt_BabaAd.getText().trim());
    	tel= kontroller.TekSayi(txt_tel.getText().trim());
   	    acililetisimTel=kontroller.TekSayi(txt_acilTel.getText().trim());
    	ePosta= kontroller.EmailDogrulama(txt_Eposta.getText().trim());
    	adres=txt_adres.getText().trim();
    	sinif=combx_sinif.getValue();
    	kanGrup=combx_kanGb.getValue();
    	fakulte=combx_fakulte.getValue();
    	bolum=combx_bolum.getValue();
    	no=kontroller.OrgNo();
    	
    	
    	sifre=kontroller.SifreKontrol(txt_sifreK.getText());
    	
    	if(!(sifre==txt_sifreK.getText())) {
    		lblsifre.setDisable(true);
    	}else {
    		
    	}
    	
    	//!ad.isEmpty() && !soyad.isEmpty() && !anneAd.isEmpty() && !babaAd.isEmpty() && !tel.isEmpty() !acililetisimTel.isEmpty() && !ePosta.isEmpty()
     if((!ad.isEmpty() && !soyad.isEmpty() && !anneAd.isEmpty() && !babaAd.isEmpty() && !tel.isEmpty() && !acililetisimTel.isEmpty() && !ePosta.isEmpty() && !adres.isEmpty() && !sinif.isEmpty() && !fakulte.isEmpty() && !bolum.isEmpty() && !kanGrup.isEmpty()) && (rdbtn_erkek.isDisable() || rdbtn_kiz.isDisable()) && !no.isEmpty()){
    
    	 
    	orgKayit=FXCollections.observableArrayList();
    	 //System.out.println("Kayit ou!");
    	 String sql="INSERT INTO orgkayit(ad,soyad,anneAd,babaAd,tel,adres,e_posta,sifre,orgno,sinif,cinsiyet,fakulte,bolum,kanGrup,acil_iletisim_tel) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	 try {
    		 orgKayit.add(new KayitClass(ad,soyad,anneAd,babaAd,tel,adres,ePosta,sifre,no,sinif,cinsiyet,fakulte_id,bolum_id, kanGrup,acililetisimTel));
    		 VeritabaniController.InsertDeleteUpdate("insert",sql,orgKayit);
    		 kontroller.AlertInfoVeritabani( ad+" İsimli Kişinin Kaydı Alındı");
    		 lblNo.setText(no);
    		 txt_tel.clear();
    		 txt_adK.clear();
    		 txt_adres.clear();
    		 txt_Eposta.clear();
    		 txt_AnneAd.clear();
    		 txt_acilTel.clear();
    		 txt_sifreK.clear();
    		 txt_soyadK.clear();
    		 txt_BabaAd.clear();
    		 combx_bolum.getItems().clear();
    		 combx_fakulte.getItems().clear();
    		 combx_sinif.getItems().clear();
    		 combx_kanGb.getItems().clear();
    	 }
    	 catch (Exception e) {
    		 kontroller.AlertErrorVeritabani(e.getMessage().toString());
		}
    		 
     }
     else
     {
    	 alert.setTitle("Bilgi Eksikliği");
    	 alert.setHeaderText("Bilgileri Kontrol et");
    	 alert.setContentText("Bilgilerinizi tekrar Kontroll eder mısınız lütfen ");
    	 alert.showAndWait();
     }

    	
    }

    @FXML
    void rdbtn_erkek_click(ActionEvent event) {
    	btnKontrol();
        cinsiyet=0;
     }
      

    @FXML
    void rdbtn_kiz_click(ActionEvent event) {
    	btnKontrol();
    	cinsiyet=1;
    }
    
/*
    combx_fakulte.setOnAction(event -> {
        fakulte = combx_fakulte.getValue();
        VeritabaniController.GetBolum(fakulte);
    });
  */
    
    @FXML
    void combx_bolum_sec(ActionEvent event) {
        bolum = combx_bolum.getSelectionModel().getSelectedItem();
        try {
        	bolum_id=VeritabaniController.GetBolumid(bolum);
        }
        catch (Exception e) {
        	kontroller.AlertErrorVeritabani(e.getMessage().toString());
		}
        
    }
    
    @FXML
    void combx_fakulte_sec(ActionEvent event) {
    	
        fakulte = combx_fakulte.getSelectionModel().getSelectedItem();
        System.out.println(fakulte);
        try {
			fakulte_id = VeritabaniController.Fakulteid(fakulte);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        try {
        ResultSet resultSet=  VeritabaniController.GetBolum(fakulte);
		  ObservableList<String> bolumListe = FXCollections.observableArrayList();	  
		  while(resultSet.next()) {			 
			  System.out.println("dxcbv");
			  bolumListe.add(resultSet.getString("bolum_adi"));
		  }
		  combx_bolum.setItems(bolumListe);
		  VeritabaniUtil.BaglantiKapat();		 
	   }

		 catch (Exception e) {
			 kontroller.AlertErrorVeritabani(e.getMessage().toString());
		}
        
    }

    @FXML
    void initialize() {
    	
    	ObservableList<String> sinif =FXCollections.observableArrayList("1","2","3","4");
    	ObservableList<String> kangb =FXCollections.observableArrayList("0 Rh+","0 Rh-","AB Rh+","AB Rh-","A Rh+","A Rh-","B Rh+","B Rh-");
    	lblsifre.setVisible(false);
           combx_sinif.getItems().addAll(sinif);
           combx_sinif.setVisibleRowCount(2);
           combx_kanGb.setItems(kangb);
           combx_kanGb.setVisibleRowCount(3);
           
           
           try {
        	   
        	   ResultSet resultSet= VeritabaniController.Fakulte();
       
        		  ObservableList<String> fakulteList = FXCollections.observableArrayList();
        		//  ObservableList<String> bolumListe = FXCollections.observableArrayList();	  
        		  while(resultSet.next()) 
        		  {			 
        			  fakulteList.add(resultSet.getString("fakulte_adi"));
        			//  bolumListe.add(resultSet.getString("bolum_adi"));
        		  }
        		  combx_fakulte.setItems(fakulteList);
        		  VeritabaniUtil.BaglantiKapat();		 
        	   }

           catch (Exception e) {
        	   kontroller.AlertErrorVeritabani(e.getMessage().toString());
		}
        
           
           
           
    }

}
