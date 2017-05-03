package predmeti;

import java.io.Serializable;

public class Predmet implements Serializable {
	private String naziv;
	private int ESBP;
	private String skolskaGodina;
	private int semestar;
	private boolean polozen;
	private int ocena;
	private String napomena;
	
	public Predmet(String naziv, int ESBP, String skolskaGodina,int semestar, boolean polozen, int ocena, String napomena) {
		if(naziv==null || naziv=="")
			throw new RuntimeException("Naziv predeta mora biti unet");
		this.naziv = naziv;
		if(ESBP<0 || ESBP>30)
			throw new RuntimeException("Pogresno uneti ESBP bodovi");
		this.ESBP = ESBP;
		if(skolskaGodina==null || skolskaGodina.length()<2)	
			throw new RuntimeException("Skolska godina mora biti uneta ispravno");
		this.skolskaGodina = skolskaGodina;
		if(semestar!=1 || semestar!=2)
			throw new RuntimeException("Pogresno unet semestar");
		this.semestar = semestar;
		this.polozen = polozen;
		if(ocena<0 || ocena>10)
			throw new RuntimeException("Pogresno uneta ocena");
		this.ocena = ocena;
		this.napomena = napomena;
	}
	public Predmet(){
		
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		if(naziv==null || naziv.length()<2)
			throw new RuntimeException("Naziv predeta mora biti ispravno unet");
		this.naziv = naziv;
	}

	public int getESBP() {
		return ESBP;
	}

	public void setESBP(int ESBP) {
		if(ESBP<1 || ESBP>30)
			throw new RuntimeException("Pogresno uneti ESBP bodovi");
		this.ESBP = ESBP;
	}

	public String getSkolskaGodina() {
		return skolskaGodina;
	}

	public void setSkolskaGodina(String skolskaGodina) {
		if(skolskaGodina==null || skolskaGodina.length()!=9 || !skolskaGodina.contains("/"))	
			throw new RuntimeException("Skolska goidna mora biti uneta ispravno");
		this.skolskaGodina = skolskaGodina;
	}
	
	public int getSemestar() {
		return semestar;
	}
	
	public void setSemestar(int semestar) {
		if(semestar!=1 && semestar!=2)
			throw new RuntimeException("Pogresno unet semestar");
		this.semestar = semestar;
	}
	
	public boolean isPolozen() {
		return polozen;
	}

	public void setPolozen(boolean polozen) {
		this.polozen = polozen;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		if(ocena<5 || ocena>10)
			throw new RuntimeException("Pogresno uneta ocena");
		this.ocena = ocena;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	
	
	
}
