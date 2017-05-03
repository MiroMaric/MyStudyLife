package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import predmeti.Predmet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class DodajPredmetGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblNazivPredmeta;
	private JTextField textFieldNazivPredmeta;
	private JLabel lblESBP;
	private JTextField textFieldESBP;
	private JLabel lblSkolskaGodina;
	private JTextField textFieldSkolskaGodina;
	private JLabel lblSemestar;
	private JComboBox<String> comboBox;
	private JLabel lblNapomena;
	private JScrollPane scrollPane;
	private JTextArea textAreaNapomena;
	private JButton btnDodajPredmet;
	private JButton btnOdustani;

	/**
	 * Create the frame.
	 */
	public DodajPredmetGUI() {
		setTitle("Dodaj predmet");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 460, 330);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNazivPredmeta());
		contentPane.add(getTextFieldNazivPredmeta());
		contentPane.add(getLblESBP());
		contentPane.add(getTextFieldESBP());
		contentPane.add(getLblSkolskaGodina());
		contentPane.add(getTextFieldSkolskaGodina());
		contentPane.add(getLblSemestar());
		contentPane.add(getComboBox());
		contentPane.add(getLblNapomena());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnDodajPredmet());
		contentPane.add(getBtnOdustani());
	}
	private JLabel getLblNazivPredmeta() {
		if (lblNazivPredmeta == null) {
			lblNazivPredmeta = new JLabel("Naziv premeta*");
			lblNazivPredmeta.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblNazivPredmeta.setBounds(10, 11, 130, 25);
		}
		return lblNazivPredmeta;
	}
	private JTextField getTextFieldNazivPredmeta() {
		if (textFieldNazivPredmeta == null) {
			textFieldNazivPredmeta = new JTextField();
			textFieldNazivPredmeta.setBounds(10, 37, 180, 35);
			textFieldNazivPredmeta.setColumns(10);
		}
		return textFieldNazivPredmeta;
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
			textFieldSkolskaGodina.setText("xxxx/xxxx");
			textFieldSkolskaGodina.setColumns(10);
			textFieldSkolskaGodina.setBounds(10, 123, 180, 35);
		}
		return textFieldSkolskaGodina;
	}
	private JLabel getLblSemestar() {
		if (lblSemestar == null) {
			lblSemestar = new JLabel("Semestar*");
			lblSemestar.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblSemestar.setBounds(252, 97, 180, 25);
		}
		return lblSemestar;
	}
	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setFont(new Font("Courier New", Font.PLAIN, 15));
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Prvi", "Drugi"}));
			comboBox.setBounds(252, 123, 180, 35);
		}
		return comboBox;
	}
	private JLabel getLblNapomena() {
		if (lblNapomena == null) {
			lblNapomena = new JLabel("Napomena");
			lblNapomena.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblNapomena.setBounds(10, 180, 130, 25);
		}
		return lblNapomena;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 209, 180, 69);
			scrollPane.setViewportView(getTextAreaNapomena());
		}
		return scrollPane;
	}
	private JTextArea getTextAreaNapomena() {
		if (textAreaNapomena == null) {
			textAreaNapomena = new JTextArea();
			textAreaNapomena.setLineWrap(true);
		}
		return textAreaNapomena;
	}
	private JButton getBtnDodajPredmet() {
		if (btnDodajPredmet == null) {
			btnDodajPredmet = new JButton("Dodaj predmet");
			btnDodajPredmet.addActionListener(new ActionListener() {
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
						GUIKontroler.predmeti.add(p);
						GUIKontroler.serijalizujPredmete();
						GlavniProzorGUI.azurirajTabeluPredmeti();
						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(DodajPredmetGUI.this, e.getMessage(), "Greska", JOptionPane.DEFAULT_OPTION);
					}
				}
			});
			btnDodajPredmet.setFocusPainted(false);
			btnDodajPredmet.setContentAreaFilled(false);
			btnDodajPredmet.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnDodajPredmet.setBounds(272, 191, 140, 35);
		}
		return btnDodajPredmet;
	}
	private JButton getBtnOdustani() {
		if (btnOdustani == null) {
			btnOdustani = new JButton("Odustani");
			btnOdustani.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GlavniProzorGUI.azurirajTabeluPredmeti(); //Nije potrebno!?
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
}
