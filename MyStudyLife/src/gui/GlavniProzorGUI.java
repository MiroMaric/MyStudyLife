package gui;

import gui.modeli.KalendarTabelaModel;
import gui.modeli.PredmetiTabelaModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Guard;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
public class GlavniProzorGUI extends JFrame {
		
	private JPanel contentPane;
	public static JTable table;
	private JTabbedPane tabbedPane;
	private JPanel panelKalendar;
	private JPanel panelPredmeti;
	private JButton btnSledeciMesec;
	private JLabel lblDatum;
	private JLabel lblNewLabel;
	private JLabel lblPonedeljak;
	private JLabel lblUtorak;
	private JLabel lblSreda;
	private JLabel lblCetvratak;
	private JLabel lblPetak;
	private JLabel lblSubota;
	private JButton btnPrethodniMesec;
	private GregorianCalendar gc = GUIKontroler.gc;
	private String[][] datumi = GUIKontroler.datumi;
	private JButton btnKolokvijum;
	private JButton btnIspit;
	private JButton btnDodajPredmet;
	private JScrollPane scrollPanePredmeti;
	public static JTable tablePredmeti;
	private JButton btnIzmeniPredmet;
	private JButton btnObrisiPredmet;

	/**
	 * Create the frame.
	 */
	public GlavniProzorGUI() {
		setTitle("MyStudyLife");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTabbedPane(), BorderLayout.CENTER);
	}
	
	
	
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					btnKolokvijum.setEnabled(true);
					btnIspit.setEnabled(true);
				}
			});
			table.setCellSelectionEnabled(true);
			table.setBounds(10, 61, 650, 360);
			GUIKontroler.popuniMatricuDatuma(datumi,gc);
			azurirajLblDatum();
			table.setModel(new KalendarTabelaModel(datumi));
			table.setRowHeight(60);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for(int i=0;i<table.getModel().getColumnCount();i++)
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		return table;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setFont(new Font("Courier New", Font.PLAIN, 18));
			tabbedPane.setFocusable(false);
			tabbedPane.addTab("Kalendar", null, getPanelKalendar(), null);
			tabbedPane.addTab("Predmeti", null, getPanelPredmeti(), null);
		}
		return tabbedPane;
	}
	private JPanel getPanelKalendar() {
		if (panelKalendar == null) {
			panelKalendar = new JPanel();
			panelKalendar.setPreferredSize(new Dimension(100, 300));
			panelKalendar.setLayout(null);
			panelKalendar.add(getTable());
			panelKalendar.add(getBtnSledeciMesec());
			panelKalendar.add(getLblDatum());
			panelKalendar.add(getLblNewLabel());
			panelKalendar.add(getLblPonedeljak());
			panelKalendar.add(getLblUtorak());
			panelKalendar.add(getLblSreda());
			panelKalendar.add(getLblCetvratak());
			panelKalendar.add(getLblPetak());
			panelKalendar.add(getLblSubota());
			panelKalendar.add(getBtnPrethodniMesec());
			panelKalendar.add(getBtnKolokvijum());
			panelKalendar.add(getBtnIspit());
		}
		return panelKalendar;
	}
	private JPanel getPanelPredmeti() {
		if (panelPredmeti == null) {
			panelPredmeti = new JPanel();
			panelPredmeti.setLayout(null);
			panelPredmeti.add(getBtnDodajPredmet());
			panelPredmeti.add(getScrollPanePredmeti());
			panelPredmeti.add(getBtnIzmeniPredmet());
			panelPredmeti.add(getBtnObrisiPredmet());
		}
		return panelPredmeti;
	}
	private JButton getBtnSledeciMesec() {
		if (btnSledeciMesec == null) {
			btnSledeciMesec = new JButton(">");
			btnSledeciMesec.setToolTipText("Sledeci mesec");
			btnSledeciMesec.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int mesec = gc.get(GregorianCalendar.MONTH);
					if(mesec+1==12){
						gc.set(GregorianCalendar.MONTH, 0);
						gc.set(GregorianCalendar.YEAR, gc.get(GregorianCalendar.YEAR)+1);
					}
					else
						gc.set(GregorianCalendar.MONTH, gc.get(GregorianCalendar.MONTH)+1);
					GUIKontroler.popuniMatricuDatuma(datumi,gc);
					azurirajTabelu();
					azurirajLblDatum();
				}
			});
			btnSledeciMesec.setBounds(383, 432, 92, 23);
		}
		return btnSledeciMesec;
	}
	private JButton getBtnPrethodniMesec() {
		if (btnPrethodniMesec == null) {
			btnPrethodniMesec = new JButton("<");
			btnPrethodniMesec.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int mesec = gc.get(GregorianCalendar.MONTH);
					if(mesec-1==-1){
						gc.set(GregorianCalendar.MONTH,11);
						gc.set(GregorianCalendar.YEAR, gc.get(GregorianCalendar.YEAR)-1);
					}
					else
						gc.set(GregorianCalendar.MONTH, gc.get(GregorianCalendar.MONTH)-1);
					GUIKontroler.popuniMatricuDatuma(datumi,gc);
					azurirajTabelu();
					azurirajLblDatum();
				}
			});
			btnPrethodniMesec.setToolTipText("Prethodni mesec");
			btnPrethodniMesec.setBounds(198, 432, 92, 23);
		}
		return btnPrethodniMesec;
	}
	private JLabel getLblDatum() {
		if (lblDatum == null) {
			lblDatum = new JLabel("");
			lblDatum.setFont(new Font("Courier New", Font.PLAIN, 21));
			lblDatum.setHorizontalAlignment(SwingConstants.CENTER);
			lblDatum.setBounds(10, 0, 650, 23);
		}
		return lblDatum;
	}
	public static void azurirajTabelu(){
		KalendarTabelaModel model = (KalendarTabelaModel) table.getModel();
		model.azurirajTabelu(GUIKontroler.datumi);
	}
	
	public static void azurirajTabeluPredmeti(){
		PredmetiTabelaModel model = (PredmetiTabelaModel)tablePredmeti.getModel();
		model.azurirajTabelu(GUIKontroler.predmeti);
	}
	
	private void azurirajLblDatum(){
		String datum = gc.get(GregorianCalendar.MONTH)+1+"/"+gc.get(GregorianCalendar.YEAR);
		getLblDatum().setText(datum);
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("NED");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblNewLabel.setBounds(10, 34, 92, 23);
		}
		return lblNewLabel;
	}
	private JLabel getLblPonedeljak() {
		if (lblPonedeljak == null) {
			lblPonedeljak = new JLabel("PON");
			lblPonedeljak.setHorizontalAlignment(SwingConstants.CENTER);
			lblPonedeljak.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblPonedeljak.setBounds(99, 34, 98, 23);
		}
		return lblPonedeljak;
	}
	private JLabel getLblUtorak() {
		if (lblUtorak == null) {
			lblUtorak = new JLabel("UTO");
			lblUtorak.setHorizontalAlignment(SwingConstants.CENTER);
			lblUtorak.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblUtorak.setBounds(198, 34, 92, 23);
		}
		return lblUtorak;
	}
	private JLabel getLblSreda() {
		if (lblSreda == null) {
			lblSreda = new JLabel("SRE");
			lblSreda.setHorizontalAlignment(SwingConstants.CENTER);
			lblSreda.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblSreda.setBounds(287, 34, 94, 23);
		}
		return lblSreda;
	}
	private JLabel getLblCetvratak() {
		if (lblCetvratak == null) {
			lblCetvratak = new JLabel("CET");
			lblCetvratak.setHorizontalAlignment(SwingConstants.CENTER);
			lblCetvratak.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblCetvratak.setBounds(380, 34, 92, 23);
		}
		return lblCetvratak;
	}
	private JLabel getLblPetak() {
		if (lblPetak == null) {
			lblPetak = new JLabel("PET");
			lblPetak.setHorizontalAlignment(SwingConstants.CENTER);
			lblPetak.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblPetak.setBounds(468, 34, 98, 23);
		}
		return lblPetak;
	}
	private JLabel getLblSubota() {
		if (lblSubota == null) {
			lblSubota = new JLabel("SUB");
			lblSubota.setHorizontalAlignment(SwingConstants.CENTER);
			lblSubota.setFont(new Font("Courier New", Font.PLAIN, 15));
			lblSubota.setBounds(565, 34, 92, 23);
		}
		return lblSubota;
	}
	private JButton getBtnKolokvijum() {
		if (btnKolokvijum == null) {
			btnKolokvijum = new JButton("Dodaj kolokvijum");
			btnKolokvijum.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUIKontroler.otvoriDodajKolokvijumGUI();
				}
				
			});
			btnKolokvijum.setEnabled(false);
			btnKolokvijum.setFocusPainted(false);
			btnKolokvijum.setContentAreaFilled(false);
			btnKolokvijum.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnKolokvijum.setBounds(670, 60, 140, 35);
		}
		return btnKolokvijum;
	}
	
	private JButton getBtnIspit() {
		if (btnIspit == null) {
			btnIspit = new JButton("Dodaj ispit");
			btnIspit.setEnabled(false);
			btnIspit.setFocusPainted(false);
			btnIspit.setContentAreaFilled(false);
			btnIspit.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnIspit.setBounds(670, 107, 140, 35);
		}
		return btnIspit;
	}
	private JButton getBtnDodajPredmet() {
		if (btnDodajPredmet == null) {
			btnDodajPredmet = new JButton("Dodaj predmet");
			btnDodajPredmet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUIKontroler.otvoriDodajPredmedGUI();
				}
			});
			btnDodajPredmet.setFocusPainted(false);
			btnDodajPredmet.setContentAreaFilled(false);
			btnDodajPredmet.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnDodajPredmet.setBounds(670, 60, 140, 35);
		}
		return btnDodajPredmet;
	}
	private JScrollPane getScrollPanePredmeti() {
		if (scrollPanePredmeti == null) {
			scrollPanePredmeti = new JScrollPane();
			scrollPanePredmeti.setBounds(10, 11, 650, 360);
			scrollPanePredmeti.setViewportView(getTablePredmeti());
		}
		return scrollPanePredmeti;
	}
	private JTable getTablePredmeti() {
		if (tablePredmeti == null) {
			tablePredmeti = new JTable();
			
			tablePredmeti.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					btnIzmeniPredmet.setEnabled(true);
					btnObrisiPredmet.setEnabled(true);
				}
			});
			tablePredmeti.setModel(new PredmetiTabelaModel(GUIKontroler.predmeti));
			tablePredmeti.setRowHeight(25);
		}
		return tablePredmeti;
	}
	private JButton getBtnIzmeniPredmet() {
		if (btnIzmeniPredmet == null) {
			btnIzmeniPredmet = new JButton("Izmeni predmet");
			btnIzmeniPredmet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUIKontroler.otvoriIzmeniPredmetGUI();
					btnIzmeniPredmet.setEnabled(false);
					btnObrisiPredmet.setEnabled(false);
				}
			});
			btnIzmeniPredmet.setEnabled(false);
			btnIzmeniPredmet.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnIzmeniPredmet.setFocusPainted(false);
			btnIzmeniPredmet.setContentAreaFilled(false);
			btnIzmeniPredmet.setBounds(670, 106, 140, 35);

		}
		return btnIzmeniPredmet;
	}
	private JButton getBtnObrisiPredmet() {
		if (btnObrisiPredmet == null) {
			btnObrisiPredmet = new JButton("Obrisi predmet");
			btnObrisiPredmet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane op = new JOptionPane();
					@SuppressWarnings("static-access")
					int odgovor = op.showConfirmDialog(null, "Da li stvarno zelite da obrisite odabrani predmet?", "Brisanje predmeta", JOptionPane.YES_NO_OPTION);
					if(odgovor==JOptionPane.YES_OPTION){
						GUIKontroler.predmeti.remove(tablePredmeti.getSelectedRow());
						GUIKontroler.serijalizujPredmete();
					}
					azurirajTabeluPredmeti(); //Van if-a da tabela ne bi ostala selektovana
					btnIzmeniPredmet.setEnabled(false);//Dugme "izmeni predmet" ostaje aktivno posle brisanje predmeta!
					btnObrisiPredmet.setEnabled(false);
					azurirajTabeluPredmeti();
				}
			});
			btnObrisiPredmet.setEnabled(false);
			btnObrisiPredmet.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnObrisiPredmet.setFocusPainted(false);
			btnObrisiPredmet.setContentAreaFilled(false);
			btnObrisiPredmet.setBounds(670, 152, 140, 35);
		}
		return btnObrisiPredmet;
	}
}
