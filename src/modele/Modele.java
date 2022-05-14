package modele;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;


public class Modele extends Observable{
	
	public static int couleurChoisie=5;//posé à gris au démarrage de l'appli AAAAAAAAA
	
	public static Modes mode;
	public enum Modes{
		VISUALISATION, CONSTRUCTION
	}
	
	public enum Element_a_ajouter{////construction plus tard
		CUBE
	}
	public static Element_a_ajouter element_a_ajouter;////construction plus tard
	
	private ArrayList<Construction> constructions; /// les constructions dissponible, à organiser en bibliothèque
	public Construction constructionActuelle;
	
	public Modele(){
		this.constructionActuelle= new Construction("terrain",5);//vérifier qu'il s'affiche à un moment AAAAAAAAAA
		this.setElement_a_ajouter(Element_a_ajouter.CUBE);////construction plus tard
		
		
		
		File fichier = new File("constructions.xml");
		try {
			fichier.createNewFile();
		}
		catch (Exception e) {
			 e.getStackTrace();
		}
		this.charger();
		if (this.constructions == null) {
			this.constructions = new ArrayList<Construction>();
		}
		System.out.println(mode);
	}
	
	//-----------------------------------------------------
	
	//changeConstructionAAfficher
	//rendreConstructionModifiable
	//rendreConstructionNonModifiable
	//enregisterEnXML(ou FXML)
	//chargerXML(ou FXML)
	//changerLaCouleurBriqueAPlacer (ou texture)
	//gererLaRechercheMultiCritere
	//doit contenir la liste de toutes les constructions existantes
	//-----------------------------------------------------
	
	// Methode renvoyant la contruction à afficher(visualisation)
	// modifier (bouton): passer en mode construction (conserver la même construcction)
	//construction -> visualisation : demander la sauvegarde
	
	public static void setMode(boolean mode) {
		if (mode) {
			Modele.mode=Modes.CONSTRUCTION;///mettre sur bonton (controleurs)
		}else {
			Modele.mode=Modes.VISUALISATION;
		}
	}

	@SuppressWarnings("resource")
	public void sauvegarder() {
		XMLEncoder encoder = null;
		try {
			FileOutputStream fos = new FileOutputStream("constructions.xml");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			encoder.writeObject(this.constructions);
			encoder.flush();
				}
		catch (Exception e){
			throw new RuntimeException("Ecriture des données impossible");
		}
		finally {
			if (encoder != null) encoder.close();
		}	
	}

	@SuppressWarnings("unchecked")
	public void charger() {
		XMLDecoder decoder = null;
		try {
			FileInputStream fis = new FileInputStream("constructions.xml");
			System.out.println("oui");
			System.out.println("file");
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			this.constructions = (ArrayList<Construction>) decoder.readObject();
			}
		catch (Exception e){
			throw new RuntimeException("Chargement des données impossible");
		}
		finally {
			if (decoder != null) decoder.close();
		}	
	}

	public void setElement_a_ajouter(Element_a_ajouter element_a_ajouter) {
		Modele.element_a_ajouter = element_a_ajouter;
	}
	
	//pour la sérialisation on as pas besoin de sérialiser les variables static non?

	public ArrayList<Construction> getConstructions() {
		return constructions;
	}
	
	public void setConstructions(ArrayList<Construction> constructions) {
		this.constructions = constructions;
	}

}
