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

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;


public class Modele {
	
	public static ArrayList<PhongMaterial>  materiaux;
	public static int couleur;
	
	
	public int largeur = 50;
	public int longueur  = 50;
	
	
	public  Group groupe = new Group(); /// deplacer vers gestion3D
	private Construction terrain;
	private ArrayList<Construction> constructions; /// les constructions dissponible, à organiser en bibliothèque
	public static Modes modeTerrain;
	public enum Modes{
		VISUALISATION, CONSTRUCTION
	}
	public enum Element_a_ajouter{////construction plus tard
		CUBE
	}
	public static Element_a_ajouter element_a_ajouter;////construction plus tard
	
	public Modele(){
		
		Modele.couleur = 5;
		this.createMateriaux();///gestion3D
		this.createTerrain();
		this.setElement_a_ajouter(Element_a_ajouter.CUBE);////construction plus tard
		this.setModeTerrain(Modes.CONSTRUCTION);///mettre sur bonton (controleurs)
		//this.setModeTerrain(Modes.VISUALISATION);
		
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

		this.majGroup(this.getTerrain());///gestion3D
		
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

	
	public void createMateriaux() {////gestion3D
		Modele.materiaux = new ArrayList<PhongMaterial>();
		Color[] tab = {Color.RED, Color.GREEN, Color.AQUA, Color.BLUE, Color.WHITE, Color.SILVER, Color.BLACK };
		for (Color couleur : tab) {
			PhongMaterial materiel = new PhongMaterial();
			materiel.setDiffuseColor(couleur);
			Modele.materiaux.add(materiel);
			
		}
		
	}


	public Construction getTerrain() {
		
		switch(Modele.modeTerrain) {
		case CONSTRUCTION:
			this.majGroup(this.terrain);
			return this.terrain;
		case VISUALISATION:
			this.majGroup(this.constructions.get(0));
			return this.constructions.get(0);
		default:
			return null;
		}

	}



	public void createTerrain() {
		ArrayList<Element> ar = new ArrayList<Element>();
		for(int i =-largeur/2; i< largeur/2; i++) {	
    		for(int j = -longueur/2; j<longueur/2; j++) {
    			int[] pos = {i*50, 0, j*50};
    			Cube box = new Cube(50, 50, 50,null, pos);
    			ar.add(box);
    			
    		}
    		
    	}
		this.terrain = new Construction(ar, "terrain");
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

	public  void majGroup(Construction constr) {
		this.groupe =  new Group();
		
		for(Element elem: constr.getBase()) {
			elem.afficher(this.groupe, this);
		}
		
	}

	public void setElement_a_ajouter(Element_a_ajouter element_a_ajouter) {
		Modele.element_a_ajouter = element_a_ajouter;
	}


	public void setCouleur(int coul) {
		Modele.couleur = coul;
	}
	
	public int getCouleur() {
		return Modele.couleur;
	}

	public void setModeTerrain(Modes modeTerrain) {
		Modele.modeTerrain = modeTerrain;
	}



	public ArrayList<Construction> getConstructions() {
		return constructions;
	}



	public void setConstructions(ArrayList<Construction> constructions) {
		this.constructions = constructions;
	}
	

	public Group getGroupe() {
		return this.groupe;	}
	
	

}
