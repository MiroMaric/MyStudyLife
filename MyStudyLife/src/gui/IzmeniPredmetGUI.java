package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import predmeti.Predmet;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class IzmeniPredmetGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNazivPredmeta;
	private JLabel lblNazivPredmeta;
	private JLabel lblESBP;
	private JTextField textFieldESBP;
	private JLabel lblSkolskaGodina;
	private JTextField textFieldSkolskaGodina;
	private JComboBox<String> comboBox;
	private JLabel lblSemestar;
	private JTextArea textAreaNapomena;
	private JButton btnIzmeniPredmet;
	private JButton btnOdustani;
	private JLabel lblNapomena;
	private JTable table = GlavniProzorGUI.tablePredmeti; //Da li pozivati ovako tabelu predmeta?

	public IzmeniPredmetGUI() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 460, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		contentPane.add(getTextFieldNazivPredmeta());
		contentPane.add(getLblNazivPredmeta());
		contentPane.add(getLblESBP());
		contentPane.add(getTextFieldESBP());
		contentPane.add(getLblSkolskaGodina());
		contentPane.add(getTextFieldSkolskaGodina());
		contentPane.add(getComboBox());
		contentPane.add(getLblSemestar());
		contentPane.add(getTextAreaNapomena());
		contentPane.add(getBtnIzmeniPredmet());
		contentPane.add(getBtnOdustani());
		contentPane.add(getLblNapomena());
	}

	private JTextField getTextFieldNazivPredmeta() {
		if (textFieldNazivPredmeta == null) {
			textFieldNazivPredmeta = new JTextField();
			textFieldNazivPredmeta.setText((String) table.getValueAt(table.getSelectedRow(), 0));
			textFieldNazivPredmeta.setColumns(10);
			textFieldNazivPredmeta.setBounds(10, 37, 180, 35);
		}
		return textFieldNazivPredmeta;
	}
	private JLabel getLblNazivPredmeta() {
		if (lblNazivPredmeta == null) {
			lblNazivPredmeta = new JLabel("Naziv premeta*");
			lblNazivPredmeta.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblNazivPredmeta.setBounds(10, 11, 130, 25);
		}
		return lblNazivPredmeta;
	}
	private JLabel getLblESBP() {
		if (lblESBP == null) {
			lblESBP = new JLabel("ESBP*");
			lblESBP.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblESBP.setBounds(252, 11, 180, 25);
		}
		return lblESBP;
	}
	private JTextField getTextFieldESBP() {
		if (textFieldESBP == null) {
			textFieldESBP = new JTextField();
			textFieldESBP.setColumns(10);
			textFieldESBP.setText(table.getValueAt(table.getSelectedRow(), 1)+"");
			textFieldESBP.setBounds(252, 37, 180, 35);
		}
		return textFieldESBP;
	}
	private JLabel getLblSkolskaGodina() {
		if (lblSkolskaGodina == null) {
			lblSkolskaGodina = new JLabel("Skolska godina*");
			lblSkolskaGodina.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblSkolskaGodina.setBounds(10, 97, 180, 25);
		}
		return lblSkolskaGodina;
	}
	private JTextField getTextFieldSkolskaGodina() {
		if (textFieldSkolskaGodina == null) {
			textFieldSkolskaGodina = new JTextField();
			textFieldSkolskaGodina.setText((String) table.getValueAt(table.getSelectedRow(), 2));
			textFieldSkolskaGodina.setColumns(10);
			textFieldSkolskaGodina.setBounds(10, 123, 180, 35);
		}
		return textFieldSkolskaGodina;
	}
	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Prvi", "Drugi"}));
			comboBox.setSelectedIndex(GUIKontroler.predmeti.get(table.getSelectedRow()).getSemestar()-1); //Podesavanje odgovarajuceg semestra
			comboBox.setFont(new Font("Courier New", Font.PLAIN, 15));
			comboBox.setBounds(252, 123, 180, 35);
		}
		return comboBox;
	}
	private JLabel getLblSemestar() {
		if (lblSemestar == null) {
			lblSemestar = new JLabel("Semestar*");
			lblSemestar.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblSemestar.setBounds(252, 97, 180, 25);
		}
		return lblSemestar;
	}
	private JTextArea getTextAreaNapomena() {
		if (textAreaNapomena == null) {
			textAreaNapomena = new JTextArea();
			textAreaNapomena.setLineWrap(true);
			textAreaNapomena.setBounds(11, 210, 178, 67);
		}
		return textAreaNapomena;
	}
	private JButton getBtnIzmeniPredmet() {
		if (btnIzmeniPredmet == null) {
			btnIzmeniPredmet = new JButton("Izmeni predmet");
			btnIzmeniPredmet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						Predmet p = new Predmet();
						p.setNaziv(textFieldNazivPredmeta.getText());
						try {
							p.setESBP(Integer.parseInt(textFieldESBP.getText()));
						} catch (Exception e) {
							throw new RuntimeException("Pogresno uneti ESBP bodovi");
						}
						p.setSkolskaGodina(textFieldSkolskaGodina.getText());
						p.setSemestar(comboBox.getSelectedIndex()+1);
						p.setNapomena(textAreaNapomena.getText());
						p.setPolozen(false);
						p.setOcena(5);
						GUIKontroler.predmeti.set(table.getSelectedRow(), p);
						GUIKontroler.serijalizujPredmete();
						GlavniProzorGUI.azurirajTabeluPredmeti();
						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(IzmeniPredmetGUI.this, e.getMessage(), "Greska", JOptionPane.DEFAULT_OPTION);
					}
				}
			});
			btnIzmeniPredmet.setFocusPainted(false);
			btnIzmeniPredmet.setContentAreaFilled(false);
			btnIzmeniPredmet.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnIzmeniPredmet.setBounds(272, 191, 140, 35);
		}
		return btnIzmeniPredmet;
	}
	private JButton getBtnOdustani() {
		if (btnOdustani == null) {
			btnOdustani = new JButton("Odustani");
			btnOdustani.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GlavniProzorGUI.azurirajTabeluPredmeti();
					dispose();
				}
			});
			btnOdustani.setFocusPainted(false);
			btnOdustani.setContentAreaFilled(false);
			btnOdustani.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnOdustani.setBounds(272, 243, 140, 35);
			
		}
		return btnOdustani;
	}
	private JLabel getLblNapomena() {
		if (lblNapomena == null) {
			lblNapomena = new JLabel("Napomena");
			lblNapomena.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblNapomena.setBounds(10, 180, 130, 25);
		}
		return lblNapomena;
	}
}
