package modele;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class Modele {
	
	private Color couleur;
	private Image texture;
	public int largeur = 50;
	public int longueur  = 50;
	public File fichier;

	private Groupe terrain;
	private ArrayList<Construction> constructions;
	private Modes modeTerrain;
	public enum Modes{
		VISUALISATION, CONSTRUCTION
	}
	public enum Remplissage {
		COULEUR, TEXTURE
	}
	public enum Element_a_ajouter{
		CUBE
	}
	private Element_a_ajouter element_a_ajouter;
	private  Remplissage remplissage;
	
	public Modele() {
		
		this.couleur = Color.SILVER;
		this.texture = null;
		this.remplissage = Remplissage.COULEUR;
		this.setTerrain(new Groupe());
		
		this.setElement_a_ajouter(Element_a_ajouter.CUBE);
		this.setModeTerrain(Modes.CONSTRUCTION);
		//this.setModeTerrain(Modes.VISUALISATION);
		
		this.fichier = new File("constructions.xml");
		charger();
		if (this.constructions == null) {
			this.constructions = new ArrayList<Construction>();
		}

		
		Cube c = new Cube(50, 50, 50, this, null);
		Cube c2 = new Cube(50,50, 50, this, c);
		Cube c3 = new Cube(50, 50, 50, this, c2);
		Cube c4= new Cube(50,50, 50, this, c3);
		Construction constr = new Construction (c, "pile grise");
		this.constructions.add(constr);
		this.couleur = Color.AQUA;
		Cube cc = new Cube(50, 50, 50, this, null);
		Cube cc2 = new Cube(50,50, 50, this, cc);
		Cube cc3 = new Cube(50, 50, 50, this, cc2);
		Cube cc4= new Cube(50,50, 50, this, cc3);
		Construction constr1 = new Construction (cc, "pile bleue");
		this.constructions.add(constr1);
		System.out.println(this.constructions.toString());
		
		
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
	
	public void setCouleur(Color coul) {
		this.couleur = coul;;
	}
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public Remplissage getRemplissage() {
		return this.remplissage;
	}



	public Image getTexture() {
		return texture;
	}

	
	
	public void setTexture(Image texture) {
		this.texture = texture;
	}
	



	public Group getTerrain() {
		
		switch(this.modeTerrain) {
		case CONSTRUCTION:
			return this.terrain;
		case VISUALISATION:
			return this.constructions.get(1).getGroupe();
		default:
			return null;
		}

	}



	public void setTerrain(Groupe terrain) {
		for(int i =-largeur/2; i< largeur/2; i++) {	
    		for(int j = -longueur/2; j<longueur/2; j++) {
    			Cube box = new Cube(50, 50, 50, this, null);
    			box.getShape().translateXProperty().set(i*50);
    			box.getShape().translateZProperty().set(j*50);
    			box.setDestructible(false);
    			terrain.getChildren().add(box.getShape());
    			
    		}
    		
    	}
		this.terrain = terrain;
	}


	@SuppressWarnings("resource")
	public void sauvegarder() {
		XMLEncoder encoder = null;
		try {
			FileOutputStream fos = new FileOutputStream("construction.xml");
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
			FileInputStream fis = new FileInputStream("construction.xml");
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			this.constructions = (ArrayList<Construction>) decoder.readObject();
			}
		catch (Exception e){
			throw new RuntimeException("chargement des données impossible");
		}
		finally {
			if (decoder != null) decoder.close();
			
		}
			
	}
	public Element_a_ajouter getElement_a_ajouter() {
		return element_a_ajouter;
	}



	public void setElement_a_ajouter(Element_a_ajouter element_a_ajouter) {
		this.element_a_ajouter = element_a_ajouter;
	}



	public Element ajouter(Element pere) {
		Element elem = null;
		switch(this.getElement_a_ajouter()) {
		case CUBE:
			Cube b1 = new Cube(50, 50, 50, this, pere);
			this.terrain.getChildren().add(b1.getShape());
			elem = b1;
		default:
			break;

		}
			
		return elem;
			
		

		
	}



	public Modes getModeTerrain() {
		return modeTerrain;
	}



	public void setModeTerrain(Modes modeTerrain) {
		this.modeTerrain = modeTerrain;
	}



	public ArrayList<Construction> getConstructions() {
		return constructions;
	}



	public void setConstructions(ArrayList<Construction> constructions) {
		this.constructions = constructions;
	}
	
	
	
	
	

}
