package gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import aktivnosti.Kolokvijum;
import predmeti.Predmet;

public class GUIKontroler {
	public static List<Predmet> predmeti;
	public static GregorianCalendar gc = new GregorianCalendar();
	public static String[][] datumi = new String[6][7];
	public static List<Kolokvijum> kolokvijumi;
	private static GlavniProzorGUI glavniProzor;
	private static DodajKolokvijumGUI dodajKolokvijum;
	private static DodajPredmetGUI dodajPredmet;
	private static IzmeniPredmetGUI izmeniPredmet;

	
	public static void main(String[] args) {
		ucitajPredmete(); //Deserijalizuje List<Predmet> predmeti iz fajla "predmeti.s"
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					glavniProzor = new GlavniProzorGUI();
					glavniProzor.setVisible(true);
					glavniProzor.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void popuniMatricuDatuma(String[][] datumi,GregorianCalendar gc){
		gc.set(GregorianCalendar.DATE, 1);
		int prviDanUMesecu = gc.get(GregorianCalendar.DAY_OF_WEEK);
		int dan = 1;
		int brojac = 1;
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				if(brojac>=prviDanUMesecu && dan<=gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)){
					datumi[i][j] = dan+"";
					dan++;
				}
				else	
					datumi[i][j] = "";
				brojac++;
			}
		}
	}
	public static void otvoriDodajKolokvijumGUI() {
		try {
			dodajKolokvijum = new DodajKolokvijumGUI();
			dodajKolokvijum.setVisible(true);
			dodajKolokvijum.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dodajKolokvijum.dispose();
				}
			});
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void otvoriDodajPredmedGUI() {
		try {
			dodajPredmet = new DodajPredmetGUI();
			dodajPredmet.setVisible(true);
			dodajPredmet.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dodajPredmet.dispose();
				}
			});
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void otvoriIzmeniPredmetGUI() {
		try {
			izmeniPredmet = new IzmeniPredmetGUI();
			izmeniPredmet.setVisible(true);
			izmeniPredmet.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					izmeniPredmet.dispose();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void serijalizujPredmete(){
		try {
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("predmeti.s")));
			os.writeObject(predmeti);
			os.flush();
			os.close();
		} catch (IOException e) {
			
		}
	}
	
	public static void ucitajPredmete(){
			try {
				ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream("predmeti.s")));
				predmeti = (LinkedList<Predmet>) is.readObject();
				is.close();
			} catch (ClassNotFoundException | IOException e) {
				predmeti = new LinkedList<>();
			}
	}
	public static void serijalizujKolokvijume(){
		try {
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("kolokvijumi.s")));
			os.writeObject(kolokvijumi);
			os.flush();
			os.close();
		} catch (IOException e) {
			
		}
	}
	public static void ucitajKolokvijume(){
		try {
			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream("kolokvijumi.s")));
			kolokvijumi = (LinkedList<Kolokvijum>) is.readObject();
			is.close();
		} catch (ClassNotFoundException | IOException e) {
			kolokvijumi = new LinkedList<>();
		}
	}
	
}
