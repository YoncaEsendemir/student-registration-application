package application;

import java.util.Date;

public class KayitClass {

    private int id;
    private String ad;
    private String soyad;
    private String anneAd;
    private String babaAd;
    private String tel;
    private String adres;
    private String e_posta;
    private String sifre;
    private String ogrNo;
    private String sinif;
    private int cinsiyet;
    private int fakulte;
    private int bolum;
    private Date kayitTarihi;
    private String kanGrupu;
    private String acilIletisimTel;
    private String fakulte_adi;
    private String bolum_adi;

    // Boş constructor
    public KayitClass() {
    }

    // Tam constructor
    public KayitClass(String ad, String soyad, String anneAd, String babaAd, String tel, String adres, String e_posta, String sifre, String ogrNo, String sinif, int cinsiyet, int fakulte, int bolum, String kanGrupu, String acilIletisimTel) {
        this.ad = ad;
        this.soyad = soyad;
        this.anneAd = anneAd;
        this.babaAd = babaAd;
        this.tel = tel;
        this.adres = adres;
        this.e_posta = e_posta;
        this.sifre = sifre;
        this.ogrNo = ogrNo;
        this.sinif = sinif;
        this.cinsiyet = cinsiyet;
        this.fakulte = fakulte;
        this.bolum = bolum;
        this.kanGrupu = kanGrupu;
        this.acilIletisimTel = acilIletisimTel;
    }
    
    public KayitClass(int id,String ad, String soyad, String anneAd, String babaAd, String tel, String adres, String e_posta, String sifre, String ogrNo, String sinif, int cinsiyet, int fakulte, int bolum, String kanGrupu, String acilIletisimTel) {
        this.id = id;
    	this.ad = ad;
        this.soyad = soyad;
        this.anneAd = anneAd;
        this.babaAd = babaAd;
        this.tel = tel;
        this.adres = adres;
        this.e_posta = e_posta;
        this.sifre = sifre;
        this.ogrNo = ogrNo;
        this.sinif = sinif;
        this.cinsiyet = cinsiyet;
        this.fakulte = fakulte;
        this.bolum = bolum;
        this.kanGrupu = kanGrupu;
        this.acilIletisimTel = acilIletisimTel;
    }
    
    public KayitClass(int id) {
        this.id = id;
    }
    
    public KayitClass(int id,String orgNo,String ad,String soyad,String fakulte_adi,String bolum_adi) {
        this.id = id;
        this.ogrNo = orgNo;
        this.ad = ad;
        this.soyad = soyad;
        this.fakulte_adi = fakulte_adi;
        this.bolum_adi = bolum_adi;   
    }

    // Getter ve Setter metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getAnneAd() {
        return anneAd;
    }

    public void setAnneAd(String anneAd) {
        this.anneAd = anneAd;
    }

    public String getBabaAd() {
        return babaAd;
    }

    public void setBabaAd(String babaAd) {
        this.babaAd = babaAd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getE_posta() {
        return e_posta;
    }

    public void setE_posta(String e_posta) {
        this.e_posta = e_posta;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getOgrNo() {
        return ogrNo;
    }

    public void setOgrNo(String ogrNo) {
        this.ogrNo = ogrNo;
    }

    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    public int getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(int cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public int getFakulte() {
        return fakulte;
    }

    public void setFakulte(int fakulte) {
        this.fakulte = fakulte;
    }

    public int getBolum() {
        return bolum;
    }

    public void setBolum(int bolum) {
        this.bolum = bolum;
    }

    public Date getKayitTarihi() {
        return kayitTarihi;
    }

    public void setKayitTarihi(Date kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

    public String getKanGrupu() {
        return kanGrupu;
    }

    public void setKanGrupu(String kanGrupu) {
        this.kanGrupu = kanGrupu;
    }

    public String getAcilIletisimTel() {
        return acilIletisimTel;
    }

    public void setAcilIletisimTel(String acilIletisimTel) {
        this.acilIletisimTel = acilIletisimTel;
    }

	public String getFakulte_adi() {
		return fakulte_adi;
	}

	public void setFakulte_adi(String fakulte_adi) {
		this.fakulte_adi = fakulte_adi;
	}

	public String getBolum_adi() {
		return bolum_adi;
	}

	public void setBolum_adi(String bolum_adi) {
		this.bolum_adi = bolum_adi;
	}
    

}

