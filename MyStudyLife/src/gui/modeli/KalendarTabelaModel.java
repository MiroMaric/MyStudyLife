package gui.modeli;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class KalendarTabelaModel extends AbstractTableModel {
	private String[] dani = {"Ponedeljak","Utorak","Sreda","Cetvratk","Petak","Subota","Nedelja"};
	private String[][] datumi = new String[6][7];
	
	
	public KalendarTabelaModel(String[][] datumi) {
		if(datumi!=null)
			this.datumi = datumi;
	}
	@Override
	public int getColumnCount() {
		return datumi[0].length;
	}

	
	@Override
	public int getRowCount() {
		return datumi.length;
	}

	@Override
	public Object getValueAt(int vrsta, int kolona) {
		return datumi[vrsta][kolona];
	}
	@Override
	public String getColumnName(int index) {
		return dani[index];
	}
	public void azurirajTabelu(String[][] datumi){
		this.datumi = datumi;
		fireTableDataChanged();
	}
}
