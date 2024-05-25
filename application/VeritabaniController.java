package application;
import com.MySQL.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class VeritabaniController {

	public VeritabaniController() {
		// TODO Auto-generated constructor stub
		con= VeritabaniUtil.Baglan(); 
	}
	
	 // Connection con= null ;
			  
	static  Connection con= null ;
	// sorgu ifadesi oluşturmak için preparedStatament hazirlanmiş ifadeler 
	static PreparedStatement sorguIfadesi=null ;
	// var olan sonuclari getirmek için 
	static ResultSet getirilen= null ;
	
	static String sql="";
	
	
	// combobox Fakulte Tablodaki Fakulteleri getirir 
	
	public static ResultSet Fakulte() throws SQLException {
		con= VeritabaniUtil.Baglan();
		sql= "Select fakulte_adi FROM fakulteler";
		sorguIfadesi=con.prepareStatement(sql);
		ResultSet getirilen = sorguIfadesi.executeQuery();
		return getirilen;
	}
	
	// Seçtığımız Fakulteye  göre id return eder 
	
	public static int Fakulteid(String fakulteAd) throws SQLException {
		int fakulteId = -1; // Varsayılan değer, eğer fakülte bulunamazsa -1 dönecek
		con = VeritabaniUtil.Baglan();
		sql = "Select fakulte_id FROM fakulteler WHERE fakulte_adi=?";
		sorguIfadesi = con.prepareStatement(sql);
		sorguIfadesi.setString(1, fakulteAd);
		ResultSet getirilen = sorguIfadesi.executeQuery();
		if (getirilen.next()) {
			fakulteId = getirilen.getInt("fakulte_id");
		}
		VeritabaniUtil.BaglantiKapat();
		return fakulteId;
	}
	
	/*
		public static ResultSet Arama(String text) {
			if()
			return getirilen;
		}
	*/
	
	
	// Seçtığımız bölüme göre id return eder 
	public static int GetBolumid(String bolumAd) throws SQLException {
		int bolumId = -1; // Varsayılan değer, eğer bölüm bulunamazsa -1 dönecek
		con = VeritabaniUtil.Baglan();
		sql = "Select bolum_id FROM bolumler WHERE bolum_adi=?";
		sorguIfadesi = con.prepareStatement(sql);
		sorguIfadesi.setString(1, bolumAd);
		ResultSet getirilen = sorguIfadesi.executeQuery();
		if (getirilen.next()) {
			bolumId = getirilen.getInt("bolum_id");
		}
		VeritabaniUtil.BaglantiKapat();
		return bolumId;
	}
	
	
	// Seçtiğimiz Fakulte adini alir Fakulte Tablosundaki Fakulte_adi ile fakulteAd karşilaştirir Fakulte adi varsa  Doğru İd getiri ve bolum tablosundaki 
	// Fakulteid ile Karşilaştirilir ve o Fakulteye göre bölümler gerlir 
	
	public static ResultSet GetBolum(String fakulteAd) throws SQLException {
		//WHERE bolumler.fakulte.id=?
		con= VeritabaniUtil.Baglan();
		 sql = "SELECT bolum_adi FROM bolumler WHERE fakulte_id = (SELECT fakulte_id FROM fakulteler WHERE fakulte_adi = ?)";
				sorguIfadesi=con.prepareStatement(sql);
				sorguIfadesi.setString(1,fakulteAd);
				ResultSet getirilen = sorguIfadesi.executeQuery();
				//ObservableList<KayitClass> kayitlar =FXCollections.observableArrayList();
				return getirilen;	
	     }
	
	
	// Tüm org bilgileri getir 
	public static ResultSet GetOrgAll(int id) throws SQLException {
		con=VeritabaniUtil.Baglan();
		sql="SELECT orgkayit.id,orgkayit.ad,orgkayit.soyad,orgkayit.anneAd,orgkayit.babaAd,orgkayit.tel,orgkayit.adres,orgkayit.e_posta,orgkayit.sifre,orgkayit.orgno,orgkayit.sinif,orgkayit.cinsiyet,bolumler.bolum_adi, fakulteler.fakulte_adi, orgkayit.kayitTarhi, orgkayit.kanGrup, orgkayit.acil_iletisim_tel"+
		" From orgkayit INNER JOIN bolumler"+ 
		" ON orgkayit.bolum=bolumler.bolum_id "+
		" INNER JOIN fakulteler ON bolumler.fakulte_id=fakulteler.fakulte_id WHERE orgkayit.id=?";
		sorguIfadesi=con.prepareStatement(sql);
		sorguIfadesi.setInt(1, id);;
         getirilen= sorguIfadesi.executeQuery();
        return getirilen;
	}
	
//SELECT orgkayit.orgno, orgkayit.ad, orgkayit.soyad, orgkayit.fakulte, orgkayit.bolum FROM orgkayit 
	// Bu select Funksiyonu tabloda görüntülenmek isteden değerleri getirir 
	
	public static void GetOrg(TableView table ,String sql,TableColumn colid, TableColumn colNo, TableColumn colAd,TableColumn colSoyad, TableColumn colFakulte, TableColumn colBolum) throws SQLException {
		
		try {
	    	ObservableList<KayitClass> kayitclass=FXCollections.observableArrayList();
			con= VeritabaniUtil.Baglan();
			sorguIfadesi=con.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();	
			
	         while(getirilen.next()) {
	        	 kayitclass.add(new KayitClass(getirilen.getInt("id"),getirilen.getString("orgno"),getirilen.getString("ad"),getirilen.getString("soyad"),getirilen.getString("fakulte_adi"),getirilen.getString("bolum_adi")));     	 
	         }
	         
	         colid.setCellValueFactory(new PropertyValueFactory<>("id"));
	         colNo.setCellValueFactory(new PropertyValueFactory<>("ogrNo"));
	         colAd.setCellValueFactory(new PropertyValueFactory<>("ad"));
	         colSoyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
	         colFakulte.setCellValueFactory(new PropertyValueFactory<>("fakulte_adi"));
	         colBolum.setCellValueFactory(new PropertyValueFactory <> ("bolum_adi"));
	         
	         table.setItems(kayitclass);
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	
		
	}
	
	
	
	// Silme Eklme ve Düzenleme fonksiyonu
	public static void  InsertDeleteUpdate(String islem,String sorgu,ObservableList<KayitClass>orgKayit) throws SQLException 
	{
		KayitClass kayitClass =new KayitClass()	;
	//	(OrgKayit) OrgTable.getItems().get(OrgTable.getSelectionModel().getSelectedIndex());
		kayitClass=orgKayit.get(0);
		//con= VeritabaniUtil.Baglan();
		sql=sorgu;
	
		sorguIfadesi=con.prepareStatement(sql);

 		if(islem.equals("insert") || islem.equals("update")) {

 		  	sorguIfadesi.setString(1,kayitClass.getAd());
 		  	sorguIfadesi.setString(2,kayitClass.getSoyad());
 		  	sorguIfadesi.setString(3,kayitClass.getAnneAd());
 		  	sorguIfadesi.setString(4,kayitClass.getBabaAd());
 		  	sorguIfadesi.setString(5,kayitClass.getTel());
 		  	sorguIfadesi.setString(6,kayitClass.getAdres());
 		  	sorguIfadesi.setString(7,kayitClass.getE_posta());
 		  	// Şifreyi MD5 ile şifrele
 		  	String sifreMD5 = md5Sifrele(kayitClass.getSifre());
 		  	sorguIfadesi.setString(8, sifreMD5);
 		  	sorguIfadesi.setString(9, kayitClass.getOgrNo());
 		  	sorguIfadesi.setString(10,kayitClass.getSinif());
 		  	sorguIfadesi.setInt(11,kayitClass.getCinsiyet());
 		  	sorguIfadesi.setInt(12,kayitClass.getFakulte());
 		  	sorguIfadesi.setInt(13, kayitClass.getBolum());
 		  	sorguIfadesi.setString(14, kayitClass.getKanGrupu());
 		  	sorguIfadesi.setString(15, kayitClass.getAcilIletisimTel());
 	
 		  	if(islem.equals("update")) {
 		  		sorguIfadesi.setInt(16, kayitClass.getId());
 		     	} 	
			}
 				else if(islem.equals("delete")) {
				sorguIfadesi.setInt(1, kayitClass.getId());
			}
 		sorguIfadesi.executeUpdate();
	}
	
	
	
	// Eşsiz orgno için 
	public static ResultSet OrgKontrol(String orgno) throws SQLException 
	{
		con=VeritabaniUtil.Baglan();
		sql="SELECT orgkayit.orgno From orgkayit WHERE orgkayit.orgno=? ";
		sorguIfadesi=con.prepareStatement(sql);
		sorguIfadesi.setString(1, orgno);
		getirilen=sorguIfadesi.executeQuery();
		
		return getirilen;
	
	}
	
	// Ogrenci bilgileri kendi bigilerini görmesi i��in 
	public static ResultSet OrgGet(String orgno) throws SQLException 
	{
		con=VeritabaniUtil.Baglan();
		sql="SELECT orgkayit.ad,orgkayit.soyad,orgkayit.tel,orgkayit.adres,orgkayit.e_posta,orgkayit.orgno,orgkayit.sinif,orgkayit.cinsiyet,bolumler.bolum_adi, fakulteler.fakulte_adi, orgkayit.kayitTarhi"+
		" From orgkayit INNER JOIN bolumler"+ 
		" ON orgkayit.bolum=bolumler.bolum_id "+
		" INNER JOIN fakulteler ON bolumler.fakulte_id=fakulteler.fakulte_id WHERE orgkayit.orgno=?";
		sorguIfadesi=con.prepareStatement(sql);
		sorguIfadesi.setString(1, orgno);
		getirilen=sorguIfadesi.executeQuery();
		return getirilen;
	}
	
	// Oğrenci Gişi Bilgilerini kontrol etme  rrgno adi ve şifre ile
	
	public static ResultSet OrgGiris(String orgno, String sifre) throws SQLException {
		con = VeritabaniUtil.Baglan();
		// Kullanıcıdan alınan şifreyi MD5 ile şifrele
		String sifreMD5 = md5Sifrele(sifre);
		sql = "SELECT orgkayit.orgno FROM orgkayit WHERE orgkayit.orgno=? AND orgkayit.sifre=?";
		sorguIfadesi = con.prepareStatement(sql);
		sorguIfadesi.setString(1, orgno);
		sorguIfadesi.setString(2, sifreMD5);
		getirilen = sorguIfadesi.executeQuery();
		return getirilen;
	}
	
	
	// Yetkili Giriş Bilgilerini kontrol etme  kullanıcı adi ve şifre ile
	
	public static ResultSet YetkiliGiris(String kulAd, String sifre) throws SQLException {
		con = VeritabaniUtil.Baglan();

		sql = "SELECT * FROM yetkili WHERE yetkili.kullaniciAd=? AND yetkili.sifre=?";
		sorguIfadesi = con.prepareStatement(sql);
		sorguIfadesi.setString(1, kulAd);
		sorguIfadesi.setString(2, sifre);
		getirilen = sorguIfadesi.executeQuery();
		return getirilen;
	}
	
	
	// Md5 Şifreleme Fonksiyonu
	public static String md5Sifrele(String sifre) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sifre.getBytes());
            byte[] byteData = md.digest();

            // byte to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	//class sonu
}
