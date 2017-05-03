package gui.modeli;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import predmeti.Predmet;

public class PredmetiTabelaModel extends AbstractTableModel {
	private String[] kolone = {"Naziv predmeta","ESBP","Skolska godina","Semestar","Polozen","Ocena","Napomena"};
	private List<Predmet> predmeti;
	
	public PredmetiTabelaModel(List<Predmet> predmeti) {
		if(predmeti==null)
			this.predmeti = new LinkedList<Predmet>();
		else
			this.predmeti = predmeti;
	}
	
	@Override
	public int getColumnCount() {
		return kolone.length;
	}
	@Override
	public int getRowCount() {
		return predmeti.size();
	}

	@Override
	public Object getValueAt(int vrsta, int kolona) {
		switch (kolona) {
		case 0:
			return predmeti.get(vrsta).getNaziv();
		case 1:
			return predmeti.get(vrsta).getESBP();
		case 2:
			return predmeti.get(vrsta).getSkolskaGodina();
		case 3:
			return predmeti.get(vrsta).getSemestar()==1?"Prvi":"Drugi";
		case 4:
			return predmeti.get(vrsta).isPolozen();
		case 5:
			return predmeti.get(vrsta).getOcena();
		case 6:
			return predmeti.get(vrsta).getNapomena();
		default:
			return "Greska";
		}
	}
	
	@Override
	public String getColumnName(int kolona) {
		return kolone[kolona];
	}
	
	public void azurirajTabelu(List<Predmet> predmeti){
		this.predmeti = predmeti;
		fireTableDataChanged();
	}
}
