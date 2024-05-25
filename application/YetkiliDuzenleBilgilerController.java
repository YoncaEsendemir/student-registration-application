package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.MySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class YetkiliDuzenleBilgilerController {
	
	YetkiliTabloGosterController yetkiliTabloGosterController= new YetkiliTabloGosterController();
	
    Kontroller kontroller= new Kontroller();
	Alert alert = new Alert(AlertType.ERROR);
	
   
	int id=yetkiliTabloGosterController.IdGetirduzenlevoid();
	String DuzenleGetir= yetkiliTabloGosterController.degerbtnDeger2();
	
	public void btnKontrol() {
		
		if(rdbtn_erkekY.isSelected()==true) {
			 rdbtn_kizY.setDisable(true);
			
			 
		}
		else if(rdbtn_kizY.isSelected()==true) {
			rdbtn_erkekY.setDisable(true);
		}
		
		if(rdbtn_erkekY.isSelected()==false) {
			rdbtn_kizY.setDisable(false);
		}
		 if(rdbtn_kizY.isSelected()==false) {
			rdbtn_erkekY.setDisable(false);
		}
	
	}
	
    @FXML
    private AnchorPane kayitDuzenYetkili;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Cik;

    @FXML
    private Button btn_Geri;
    

    @FXML
    private Label lbl_orgno;

    @FXML
    private Button btn_SilY;

    @FXML
    private Button btn_duzenleY;

    @FXML
    private ComboBox<String> combx_bolumY;

    @FXML
    private ComboBox<String> combx_fakulteY;

    @FXML
    private ComboBox<String> combx_kanGbY;

    @FXML
    private ComboBox<String> combx_sinifY;

    @FXML
    private ImageView img_foto;

    @FXML
    private RadioButton rdbtn_erkekY;

    @FXML
    private RadioButton rdbtn_kizY;

    @FXML
    private TextField txt_AnneAdY;

    @FXML
    private TextField txt_BabaAdY;

    @FXML
    private TextField txt_EpostaY;

    @FXML
    private TextField txt_acilTelY;

    @FXML
    private TextField txt_adY;
    
    @FXML
    private DatePicker datePicerDateY;

    @FXML
    private TextArea txt_adresY;

    @FXML
    private TextField txt_sifreY;

    @FXML
    private TextField txt_soyadY;

    @FXML
    private TextField txt_telY;

    ObservableList<KayitClass>orgKayit;
    
    String ad,soyad,anneAd,babaAd,adres,tel,ePosta,sifre,no,sinif,bolum,fakulte="";
    int fakulte_id;

	int bolum_id;
    String kanGrup,acililetisimTel="";
    int cinsiyet;
    
    @FXML
    void btn_Geri_click(ActionEvent event) {
    	kontroller.FormDegistir("AnaMenu.fxml", kayitDuzenYetkili, 302, 399, "Ana Menüe Geçilemedi");
    }
    
    @FXML
    void btn_Cik_click(ActionEvent event) {
    	kontroller.cikis();
    }
    
    
    @FXML
    void combx_bolumY_sec(ActionEvent event) {
        bolum = combx_bolumY.getSelectionModel().getSelectedItem();
        try {
        	bolum_id=VeritabaniController.GetBolumid(bolum);
        }
        catch (Exception e) {
        	kontroller.AlertErrorVeritabani(e.getMessage().toString());
		}
    }
    
   
    
    @FXML
    void combx_fakulteY_sec(ActionEvent event) {
               	
        fakulte = combx_fakulteY.getSelectionModel().getSelectedItem();

        try { 
        	fakulte_id=VeritabaniController.Fakulteid(fakulte);
        }
        catch (Exception e) {
        	kontroller.AlertErrorVeritabani(e.getMessage().toString());
		}
        
        
        try {
        ResultSet resultSet=  VeritabaniController.GetBolum(fakulte);
		  ObservableList<String> bolumListe = FXCollections.observableArrayList();	  
		  while(resultSet.next()) {			 
			  //System.out.println("dxcbv");
			  bolumListe.add(resultSet.getString("bolum_adi"));
		  }
		  combx_bolumY.getItems().setAll(bolumListe);
		  VeritabaniUtil.BaglantiKapat();		 
	   }

		 catch (Exception e) {
			 kontroller.AlertErrorVeritabani(e.getMessage().toString());
		}
    }

    @FXML
    void btn_Sil_click(ActionEvent event) {
    	
	    Alert silmeOnay= new Alert(AlertType.CONFIRMATION);
	    silmeOnay.setTitle("ISTE Otomasyonu");
	    silmeOnay.setHeaderText("Onay Kutusu");
	    silmeOnay.setContentText("Silmek istediğiniz emin misiniz ?");
	    
	       ButtonType btn1=new ButtonType("Evet");
	       ButtonType btn2 = new ButtonType("İptal",ButtonData.CANCEL_CLOSE);
	
	       silmeOnay.getButtonTypes().setAll(btn1,btn2);

	       
	       Optional<ButtonType> sonuc= silmeOnay.showAndWait();
	        if(sonuc.get()==btn1) 
	        {
	            String sql="DELETE FROM orgkayit WHERE id =?";
	       		ObservableList<KayitClass>orgSil;
	       		orgSil=FXCollections.observableArrayList();
	       	 try {
	              orgSil.add(new KayitClass(id));
	       		 VeritabaniController.InsertDeleteUpdate("delete",sql,orgSil);
	       		 
	       	 }
	       	 catch (Exception e) {
	    			// TODO: handle exception
	       		 System.out.println("hata var!");
	    		} 
	        }
	       	            
    }
  
    
    @FXML
    void btn_duzenle_click(ActionEvent event) {
	
    	ad=kontroller.TekHarf(txt_adY.getText().trim());
    	soyad= kontroller.TekHarf(txt_soyadY.getText().trim());
    	anneAd=kontroller.TekHarf(txt_AnneAdY.getText().trim());
    	babaAd=kontroller.TekHarf(txt_BabaAdY.getText().trim());
    	tel= kontroller.TekSayi(txt_telY.getText().trim());
   	    acililetisimTel=kontroller.TekSayi(txt_acilTelY.getText().trim());
    	ePosta=(txt_EpostaY.getText().trim());
    	adres=txt_adresY.getText().trim();
    	sinif=combx_sinifY.getValue();
    	kanGrup=combx_kanGbY.getValue();
    	try {
			fakulte_id=VeritabaniController.Fakulteid(combx_fakulteY.getValue());
			bolum_id=VeritabaniController.GetBolumid(combx_bolumY.getValue());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	no=lbl_orgno.getText();
    	sifre=txt_sifreY.getText();
    	
    	System.out.println(no);
    //	System.out.print(id+ "\n" +ad+ "\n" +soyad+ "\n" +anneAd+ "\n" +babaAd+ "\n" +adres+ "\n" +tel+ "\n" +ePosta+ "\n" +sifre+ "\n" +no+ "\n" +sinif+ "\n" +cinsiyet+ "\n" +fakulte_id+ "\n" + bolum_id + "\n" +kanGrup+ "\n" +acililetisimTel);
    	
    	
    	//!ad.isEmpty() && !soyad.isEmpty() && !anneAd.isEmpty() && !babaAd.isEmpty() && !tel.isEmpty() !acililetisimTel.isEmpty() && !ePosta.
    	if((!ad.isEmpty() && !soyad.isEmpty() && !anneAd.isEmpty() && !babaAd.isEmpty() && !tel.isEmpty() && !acililetisimTel.isEmpty() && !ePosta.isEmpty() && !adres.isEmpty() && !sinif.isEmpty() && !combx_fakulteY.getValue().isEmpty() && !combx_bolumY.getValue().isEmpty() && !kanGrup.isEmpty()) && (rdbtn_erkekY.isDisable() || rdbtn_kizY.isDisable()) && !no.isEmpty()){
    	 System.out.println("tüm değerler girildi " + cinsiyet );
    	 ObservableList<KayitClass>orgKayit;
    	 orgKayit=FXCollections.observableArrayList();
    String sql="UPDATE orgkayit SET ad=?, soyad=?, anneAd=?, babaAd=?, tel=?, adres=?,e_posta=?,sifre=?,orgno=?,sinif=?,cinsiyet=?,fakulte=?,bolum=?,kanGrup=?,acil_iletisim_tel=?  WHERE id =?";
    	 try {
    		 orgKayit.add(new KayitClass(id,ad,soyad,anneAd,babaAd,adres,tel,ePosta,sifre,no,sinif,cinsiyet,fakulte_id,bolum_id,kanGrup,acililetisimTel));
    		 System.out.println("Update yerine geldi çok şükür");
    		 VeritabaniController.InsertDeleteUpdate("update",sql,orgKayit);
    		 kontroller.AlertInfoVeritabani(no+" Numeralı Oğrenci Güncellendi");
    	 }
    	 catch (Exception e) {
			// TODO: handle exception
    		 kontroller.AlertErrorVeritabani(e.getMessage().toString());
		}
    	
    	 
    	 
     }
     else
     {
    	 alert.setTitle("Bilgi Eksikliği");
    	 alert.setHeaderText("Bilgileri Kontrol et");
    	 alert.setContentText("Bilgilerinizi tekrar Kontroll eder mısınız lütfen ");
     }
    	
    	
    }

    @FXML
    void rdbtn_erkekY_click(ActionEvent event) {
    	btnKontrol();
        cinsiyet=0;
    }

    @FXML
    void rdbtn_kizY_click(ActionEvent event) {
    	btnKontrol();
        cinsiyet=1;
    }

    @FXML
    void initialize() {
    	
    	ObservableList<String> sinif =FXCollections.observableArrayList("1","2","3","4");
    	ObservableList<String> kangb =FXCollections.observableArrayList("0 Rh+","0 Rh-","AB Rh+","AB Rh-","A Rh+","A Rh-","B Rh+","B Rh-");
    	
    	combx_sinifY.getItems().addAll(sinif);
           combx_sinifY.setVisibleRowCount(2);
           combx_kanGbY.setItems(kangb);
           combx_kanGbY.setVisibleRowCount(3);
           
           
           
           
           try {
         	   
         	   ResultSet resultSet= VeritabaniController.Fakulte();
        
         		  ObservableList<String> fakulteList = FXCollections.observableArrayList();
         		//  ObservableList<String> bolumListe = FXCollections.observableArrayList();	  
         		  while(resultSet.next()) 
         		  {			 
         			  fakulteList.add(resultSet.getString("fakulte_adi"));
         			//  bolumListe.add(resultSet.getString("bolum_adi"));
         		  }
         		 combx_fakulteY.getItems().setAll(fakulteList);
         		  VeritabaniUtil.BaglantiKapat();		 
         	   }

            catch (Exception e) {
         	   kontroller.AlertErrorVeritabani(e.getMessage().toString());
    		}
    	
    	
    	
    	if(DuzenleGetir.equals("Düzenle")) {
    		
        	try {
                ResultSet resultset = VeritabaniController.GetOrgAll(id);
                
                if(resultset.next()) {
       
               	 
               	 txt_adY.setText(resultset.getString("ad"));
               	 txt_soyadY.setText(resultset.getString("soyad"));
               	 txt_AnneAdY.setText(resultset.getString("anneAd"));
               	 txt_BabaAdY.setText(resultset.getString("babaAd"));
               	 txt_telY.setText(resultset.getString("tel"));
               	 txt_adresY.setText(resultset.getString("adres"));
               	 txt_EpostaY.setText(resultset.getString("e_posta"));
               	 txt_sifreY.setText(resultset.getString("sifre"));
               	 lbl_orgno.setText(resultset.getString("orgno"));
               	 combx_sinifY.setValue(resultset.getString("sinif"));
               	
               	 System.out.println(resultset.getInt("cinsiyet"));
               	 
               	  if(resultset.getInt("cinsiyet")==0) {
                    	 rdbtn_erkekY.setDisable(true);
                   	 rdbtn_erkekY.setSelected(true);
               	  }
               	  else if(resultset.getInt("cinsiyet")==1){
                     	 rdbtn_kizY.setDisable(true);
                     	 rdbtn_kizY.setSelected(true);
               	  }
    
               	 combx_kanGbY.setValue(resultset.getString("kanGrup"));
                combx_bolumY.setValue(resultset.getString("bolum_adi"));	
                combx_fakulteY.setValue(resultset.getString("fakulte_adi"));
				System.out.println(resultset.getString("bolum_adi"));
               	 txt_acilTelY.setText(resultset.getString("acil_iletisim_tel"));
               	 datePicerDateY.setValue(resultset.getDate("kayitTarhi").toLocalDate());

                }
                 
           	}
           	catch (Exception e) {
       			// TODO: handle exception
           	   kontroller.AlertErrorVeritabani(e.getMessage().toString());
       		}
    		
    		
    	}
    	else if(DuzenleGetir.equals("Göster")) {
               btn_duzenleY.setDisable(true);
               btn_SilY.setDisable(true);
        	
        	try {
             ResultSet resultset = VeritabaniController.GetOrgAll(id);
             
             if(resultset.next()) {
    
            	 txt_adY.setDisable(true);
            	 txt_adY.setText(resultset.getString("ad"));
            	 txt_soyadY.setDisable(true);
            	 txt_soyadY.setText(resultset.getString("soyad"));
            	 txt_AnneAdY.setDisable(true);
            	 txt_AnneAdY.setText(resultset.getString("anneAd"));
            	 txt_BabaAdY.setDisable(true);
            	 txt_BabaAdY.setText(resultset.getString("babaAd"));
            	 txt_telY.setDisable(true);
            	 txt_telY.setText(resultset.getString("tel"));
            	 txt_adresY.setDisable(true);
            	 txt_adresY.setText(resultset.getString("adres"));
            	 txt_EpostaY.setDisable(true);
            	 txt_EpostaY.setText(resultset.getString("e_posta"));
            	 txt_sifreY.setDisable(true);
            	 txt_sifreY.setText(resultset.getString("sifre"));
            	 lbl_orgno.setText(resultset.getString("orgno"));
            	 

            	 combx_sinifY.setValue(resultset.getString("sinif"));
            	 combx_sinifY.setDisable(true);
            	 
            	  if(resultset.getInt("cinsiyet")==0) {
                 	 rdbtn_erkekY.setDisable(true);
                	 rdbtn_erkekY.setSelected(true);
                	 rdbtn_kizY.setDisable(true);
            	  }
            	  else if(resultset.getInt("cinsiyet")==1){
                  	 rdbtn_kizY.setDisable(true);
                  	 rdbtn_kizY.setSelected(true);
                  	rdbtn_erkekY.setDisable(true);
            	  }
 
            	 
            	 combx_kanGbY.setValue(resultset.getString("kanGrup"));
            	 combx_kanGbY.setDisable(true);
             	 combx_bolumY.setDisable(true);
             	combx_bolumY.setValue(resultset.getString("bolum_adi"));
             	 combx_fakulteY.setDisable(true);
             	combx_fakulteY.setValue(resultset.getString("fakulte_adi"));
            	 txt_acilTelY.setDisable(true);
            	 txt_acilTelY.setText(resultset.getString("acil_iletisim_tel"));
            	datePicerDateY.setDisable(true);
            	 datePicerDateY.setValue(resultset.getDate("kayitTarhi").toLocalDate());

             }
              
        	}
        	catch (Exception e) {
    			// TODO: handle exception
        		   kontroller.AlertErrorVeritabani(e.getMessage().toString());
    		}
    }
    	
    
   //  no = lbl_orgno.getText();
}
}
