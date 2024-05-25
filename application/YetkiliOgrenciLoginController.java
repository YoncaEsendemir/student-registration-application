package application;

import java.sql.ResultSet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class YetkiliOgrenciLoginController {
	
	Kontroller kontroller= new Kontroller();
	static String  orgnog;
	
	public void OtomatikFormDegistir(String fxmlyol,int width,int yuk) {
    	try {
			 Stage stage2 = new Stage();
   		    FXMLLoader load= new FXMLLoader(getClass().getResource(fxmlyol));
  	     	AnchorPane pane =(AnchorPane) load.load();

    	    
           Scene scene= new Scene(pane,width,yuk);
           stage2.initStyle(StageStyle.TRANSPARENT);;
           stage2.setScene(scene);
           stage2.show();

           ((Stage)YetkiliLog.getScene().getWindow()).close();
	      }
	        catch (Exception e) {
					// TODO: handle exception
		        	
		        	Alert alert2 = new Alert(AlertType.ERROR);
		        	alert2.setTitle("Form Geçişi");
		        	alert2.setHeaderText("Form Geçişi hata");
		        	alert2.setContentText("Yetkili Islemleri sayfasina geçilemedi" + e);
		        	alert2.showAndWait();
		      	System.out.println(e);

		        }
		
	}
	
	public Boolean DogrulamaAdmin(String kul ,String sifre) {
		
   	 
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("İste Otomasyon ");
    	alert.setHeaderText("Hata mesaji");
   	 if(!kul.isEmpty() && !sifre.isEmpty()) {
		 
		 if(!(kul.matches("[0-9]+")) && kul.length()<10 && kul.length()>=4) {
     		   if(sifre.contains("=") || sifre.contains("'") || sifre.contains("#") || sifre.contains("\\x75")|| sifre.contains("\\x25")) {
       			   alert.setContentText("SQL Injection tespit edildi ");
       			 alert.showAndWait();
       			   System.exit(0);
       		   } 
     		   
     		   else {
     			   // admin olacaksa değiştir sonra sifre
       			   if(sifre.length()<=4) {
     
       				return true;

       				 }
           		        
       			   else {
		     alert.setContentText("şifre uzunluğu 4 karakterden uzun olması gerek ");
		     alert.showAndWait();   
       			   }
       		   }
	 
		 }
		 else if(( Integer.parseInt(kul) >= 10000 && Integer.parseInt(kul) <= 99999)  && kul.length()<6 && kul.length()>=4 ) {
   		   if(sifre.contains("=") || sifre.contains("'") || sifre.contains("#") || sifre.contains("\\x75")|| sifre.contains("\\x25")) {
   			   alert.setContentText("SQL Injection tespit edildi ");
   			 alert.showAndWait();
   			   System.exit(0);
   		   } 
 		   else {
 			   // admin olacaksa değiştir sonra sifre
   			   if(sifre.length()<=4) {
 
   				return true;

   				 }
       		        
   			   else {
	     alert.setContentText("şifre uzunluğu 4 karakterden uzun olması gerek ");
	     alert.showAndWait();   
   			   }
   		   }
   		   
		 }
		 
		 else {
			 alert.setContentText("admin iseniz kulanici adiniz hatali kullanici adınızda rakam içermiyor olması gerekir oğrenci iseniz 10000-20000 arasinda girmeniz gerek  veya kullanici adiniz  5 karakterden kısa ");
			 alert.showAndWait();
		 }
	     	
	 }
	 else {
		 alert.setContentText("kulanici Adi veya şifre girilmedi ");
		 alert.showAndWait();
	 }
		
		return false;
	}
	
	
	
	public Boolean DogrulamaOrg(String kul ,String sifre) {
		
	   	 
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("İste Otomasyon ");
    	alert.setHeaderText("Hata mesaji");
   	 if(!kul.isEmpty() && !sifre.isEmpty()) {
		 
		 if((kul.matches("[0-9]+")) && kul.length()<10 && kul.length()>=4) {
     		   if(sifre.contains("=") || sifre.contains("'") || sifre.contains("#") || sifre.contains("\\x75")|| sifre.contains("\\x25")) {
       			   alert.setContentText("SQL Injection tespit edildi ");
       			 alert.showAndWait();
       			   System.exit(0);
       		   } 
     		   
     		   else {
     			   // admin olacaksa değiştir sonra sifre
       			   if(sifre.length()<=4) {
     
       				return true;

       				 }
           		        
       			   else {
		     alert.setContentText("şifre uzunluğu 4 karakterden uzun olması gerek ");
		     alert.showAndWait();   
       			   }
       		   }
	 
		 }
		 else if(( Integer.parseInt(kul) >= 10000 && Integer.parseInt(kul) <= 99999)  && kul.length()<6 && kul.length()>=4 ) {
   		   if(sifre.contains("=") || sifre.contains("'") || sifre.contains("#") || sifre.contains("\\x75")|| sifre.contains("\\x25")) {
   			   alert.setContentText("SQL Injection tespit edildi ");
   			 alert.showAndWait();
   			   System.exit(0);
   		   } 
 		   else {
 			   // admin olacaksa değiştir sonra sifre
   			   if(sifre.length()<=4) {
 
   				return true;

   				 }
       		        
   			   else {
	     alert.setContentText("şifre uzunluğu 4 karakterden uzun olması gerek ");
	     alert.showAndWait();   
   			   }
   		   }
   		   
		 }
		 
		 else {
			 alert.setContentText("admin iseniz kulanici adiniz hatali kullanici adınızda rakam içermiyor olması gerekir oğrenci iseniz 10000-20000 arasinda girmeniz gerek  veya kullanici adiniz  5 karakterden kısa ");
			 alert.showAndWait();
		 }
	     	
	 }
	 else {
		 alert.setContentText("kulanici Adi veya şifre girilmedi ");
		 alert.showAndWait();
	 }
		
		return false;
	}
	
	
	
    public String orgno2() {
  	   
       	return	orgno1(orgnog);
       	
       }

       
       public String orgno1(String orgNo) {
    	   if(!orgNo.isEmpty()) {
    		 return  orgNo; 
    	   }
	   return " ";
       }
	
	
    @FXML
    private Button btn_Cik;

    @FXML
    private Button btn_Geri;

    @FXML
    private AnchorPane YetkiliLog;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_loginOgr;

    @FXML
    private Button btn_loginYk;
    
    @FXML
    private PasswordField txt_ogrenciSifre;

    @FXML
    private TextField txt_ogrencino;


    @FXML
    private PasswordField txt_yekiliSifre;

    @FXML
    private TextField txt_yetkiliKul;
    
    String yetkili="";
    String yetkiSifre="";
    
    
    String org="";
    String orgSifre="";
	

    @FXML
    void btn_loginOgr_click(ActionEvent event) {
    	org=txt_ogrencino.getText();
    	orgSifre=txt_ogrenciSifre.getText();
    	
    	 if(DogrulamaOrg(org, orgSifre)) {
    		 
    		 try {
    			  ResultSet resultset=VeritabaniController.OrgGiris(org, orgSifre);
    			  if(resultset.next()) 
    			  {
    				  orgnog=resultset.getString("orgno");
    				  orgno1(orgnog);
    				  
    			    OtomatikFormDegistir("OgrenciBilgiGoster.fxml",600,480);	         

    			  }
			    	else {
   			   		 System.out.println(org);
   			   		 System.out.println(orgSifre);
   			 	     kontroller.AlertErrorVeritabani("Hatali giriş tekrar dene");
   			   		
   			   		 
   			   		 txt_ogrencino.clear();
   			    	 txt_ogrenciSifre.clear();
   			   		 
   			    	} 
  
    		 }
             catch (Exception e) {
            	 kontroller.AlertErrorVeritabani(e.getMessage().toString());
			}
			     
    	 }
    	 else {
    		 System.out.println("olmadi hahaah");
    	 }
    }

   
    @FXML
    void btn_loginYk_click(ActionEvent event) {
    	
			    	 yetkili=txt_yetkiliKul.getText().trim();
			    	 yetkiSifre=txt_yekiliSifre.getText().trim();
			    	// VeritabaniController.md5Sifrele(yetkiSifre);
			    	 if(DogrulamaAdmin(yetkili, yetkiSifre)) {
			    		 
			    		 try {
			    			 yetkiSifre	= VeritabaniController.md5Sifrele(yetkiSifre);
			    			 ResultSet res = VeritabaniController.YetkiliGiris(yetkili, yetkiSifre);
			    			 
					    		if(res.next()) {
					    		//	kontroller.AlertInfoVeritabani("Yetkili giriş "+yetkili+" Yapdi");
					    			if(yetkili.contains(res.getString("kullaniciAd")) && yetkiSifre.contains(res.getString("sifre")))
					    			{
					    				kontroller.AlertInfoVeritabani("Yetkili giriş "+yetkili+" giriş Yapdı");	
					    			
					    		 OtomatikFormDegistir("YetkiliIslemleri.fxml",810,470);
					    			}
						    	}
						    	else {
						    		 System.out.println(yetkili);
						    		 System.out.println(yetkiSifre);
						    		// System.out.println("Sifre bilgilerini veya Kullaniçi adi hatali ");
						    	}

			    		 }
			    		 catch (Exception e) {
			    			 kontroller.AlertErrorVeritabani(e.getMessage().toString());
						}
	

					    		
                          }
			    	 else {

			    		 kontroller.AlertErrorVeritabani("Hatlı giriş kontroll ediniz");
			    	 }
			    	
    }
			
    
    
    @FXML
    void btn_Cik_click(ActionEvent event) {
    	kontroller.cikis();
    }

    @FXML
    void btn_Geri_click(ActionEvent event) {
    	kontroller.FormDegistir("AnaMenu.fxml", YetkiliLog, 302, 399, "Ana Menüe Geçilemedi");
    }
    	
 
    @FXML
    void initialize() {

    }
    
    
    
    
    

}
