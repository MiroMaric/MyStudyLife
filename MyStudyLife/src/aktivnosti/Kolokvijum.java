package aktivnosti;

import java.util.GregorianCalendar;

import predmeti.Predmet;

public class Kolokvijum {
	private Predmet predmet;
	private GregorianCalendar vremePolaganja;
	private String mesto;
	public Predmet getPredmet() {
		return predmet;
	}
	public void setPredmet(Predmet predmet) {
		if(predmet!=null)
			this.predmet = predmet;
	}
	public GregorianCalendar getVremePolaganja() {
		return vremePolaganja;
	}
	public void setVremePolaganja(GregorianCalendar vremePolaganja) {
		if(vremePolaganja!=null)
			this.vremePolaganja = vremePolaganja;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		if(mesto!=null && mesto.length()>=2)
			this.mesto = mesto;
	}
}
