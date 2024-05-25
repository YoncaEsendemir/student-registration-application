package application;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Kontroller {


	Alert alert = new Alert(AlertType.ERROR);
	Alert alert2 = new Alert(AlertType.INFORMATION);
	
	Alert alert3 = new Alert(AlertType.CONFIRMATION);
	
	
	public void SileGuncelemeEkleme(String mesaj) {  
	       Alert alert= new Alert(AlertType.CONFIRMATION);
	       alert.setTitle("ISTE Otomasyonu");
	       alert.setHeaderText("Onay Kutusu");
	       alert.setContentText(mesaj);
	       
	       ButtonType btn1=new ButtonType("Evet");
	       ButtonType btn2 = new ButtonType("Hayir");
	     //  ButtonType btn4 = new ButtonType("Tamam",ButtonData.OK_DONE);
	       
	       
	       alert.getButtonTypes().setAll(btn1,btn2);
	       // hangi nutton seçildiğini kontroll edeceğiz 
	       // Optional seçenek olarak type ButtonType kullanilir 
	       
	       Optional<ButtonType> sonuc= alert.showAndWait();
	        if(sonuc.get()==btn1) {
	        	System.out.println("Evet butonuna basildi");
	        }
	        else if(sonuc.get()==btn2) {
	        	System.out.println("Hayir butonuna basildi ");
	        }
	        else {
	        	System.out.println("iptal butonunla basildi !");
	        }
	}
	
	
	public void AlertInfoVeritabani(String mesaj) {  
		alert2.setTitle("Bilgi Bildirim");
		alert2.setContentText(mesaj);
		alert2.showAndWait();
	}
	
	
	
	
	public void AlertErrorVeritabani(String e) {  
		alert.setTitle("Sql Hatasi");
		alert.setHeaderText("Sql Hatasi var");
		alert.setContentText(e);
		 alert.showAndWait();
	}
	
	
	public String TekHarf(String harf) {
		
		if(!harf.isEmpty() && !(harf.matches("[0-9]+"))){
		
		  return harf;
		}
			alert.setTitle("Harf Hatasi");
			alert.setHeaderText("Rakam var ");
			alert.setContentText("Text Alanın içinde rakam var");
            alert.showAndWait();
		return "";
	}


	public String TekSayi(String sayi) {
		if(!sayi.isEmpty() && (sayi.matches("[0-9]+")) && sayi.length()<12 && sayi.length()>10){
			
			  return sayi;
			}
				alert.setTitle("Rakam Hatasi");
				alert.setHeaderText("Harf  var ");
				alert.setContentText("Text Alanın içinde harf var veya boş ve 11 rakam girdiğinise emin olun ");
	            alert.showAndWait();
			return "";
		
	}
	
	
	public String EmailDogrulama(String mail) {
		alert.setTitle("Mail Hatasi");
		alert.setHeaderText("Email hatali giriş");
	if(mail.length()>=10) {
      	 if(mail.isEmpty() && mail.contains("@gmail.com") ) {
      	  
      		alert.setContentText("Eposta girilmedi veya @gmail.com içermiyor  ");
	            alert.showAndWait();
	            return "";
      	    	 }
      	    	 else {
                  if(mail.contains("=") || mail.contains("'") || mail.contains("#") || mail.contains("\\x75")|| mail.contains("\\x25")) {
                	  
                		alert.setContentText("SQL Injection tespit edildi  ");
        	            alert.showAndWait();
        	            return "";
                  }
                  else {
                	  
      	    		  return mail;

                  }
                             	    		 
      	    	 }
      	    	 
   	}
	return "";
}
	
	
	public String SifreKontrol(String sifre) {
		if(sifre.contains("=") || sifre.contains("'") || sifre.contains("#") || sifre.contains("\\x75")|| sifre.contains("\\x25"))
				{
			   alert.setContentText("SQL Injection tespit edildi ");
			   alert.showAndWait();
			   System.exit(0);
			   return "";
		}
		else {
			if(sifre.length()>3 && sifre.length()<=4 ){ 
				return sifre;
			}else {
				   alert.setContentText("sifre 4 karakterden fazla olmasın");
				   alert.showAndWait();	
				   return "";
			}

		}
	
	}
		
	
      public Boolean cikis(){
    	    Alert alert = new Alert(AlertType.NONE);
    	    alert.setTitle("Pencere Kapatma");
    	    alert.setHeaderText("çikiş");
    	    alert.setContentText("Çikmak istediğinize emin misniz !!");  
    	 	ButtonType btnEvet= new ButtonType("Evet");
    	 	ButtonType btnHayir=new ButtonType("Hayir");
    	 	alert.getButtonTypes().setAll(btnEvet,btnHayir);
    	 	Optional<ButtonType> sonuc= alert.showAndWait();
    	 	
    	 	if(sonuc.get()==btnEvet) {
    	 		System.exit(0);
    	 	
    	 	}
    	 	else if(sonuc.get()==btnHayir) {
    	 		
    	 	}
    	 	return false;
      }
      
      
      
      public String OrgNo() {
    	  
    	  String orgNo="";
    	  int no=0;
    	  
    	  
             for(int i=0; i<5;i++) {
            	 
            	 no=(int) (Math.random()*10);
            	 
            		 orgNo+= Integer.toString(no); 
            	 
             }
       
    	  return  orgNo;
      }
      
      
      public void FormDegistir(String FormYolu,AnchorPane Form1,int gen,int yuk,String hatamesaj) {
    	  
      	try {
    		Stage stageK = new Stage();
    		//"Kayit.fxml"
      	   FXMLLoader load= new FXMLLoader(getClass().getResource(FormYolu));
     		AnchorPane panek =(AnchorPane) load.load();
              Scene scene= new Scene(panek,gen,yuk);
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
        	alert.setContentText(hatamesaj + e);
        	alert.showAndWait();
        	
           System.out.println(e);
		}
      }
	
      
      
	
}
