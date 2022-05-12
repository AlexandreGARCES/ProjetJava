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
	
	public static Color couleur;
	public static Image texture;
	public int largeur = 50;
	public int longueur  = 50;
	public File fichier;
	public  Group groupe = new Group();
	private Construction terrain;
	private ArrayList<Construction> constructions;
	public static Modes modeTerrain;
	public enum Modes{
		VISUALISATION, CONSTRUCTION
	}
	public enum Remplissage {
		COULEUR, TEXTURE
	}
	public enum Element_a_ajouter{
		CUBE
	}
	public static Element_a_ajouter element_a_ajouter;
	public static  Remplissage remplissage;
	
	public Modele() {
		
		this.couleur = Color.SILVER;
		this.texture = null;
		Modele.remplissage = Remplissage.COULEUR;
		this.createTerrain();
		
		this.setElement_a_ajouter(Element_a_ajouter.CUBE);
		this.setModeTerrain(Modes.CONSTRUCTION);
		//this.setModeTerrain(Modes.VISUALISATION);
		
		this.fichier = new File("constructions.xml");
		///charger();

		if (this.constructions == null) {
			this.constructions = new ArrayList<Construction>();
		}

		
		Cube c = new Cube(50, 50, 50,null);
		Cube c2 = new Cube(50,50, 50, c);
		Cube c3 = new Cube(50, 50, 50, c2);
		Cube c4= new Cube(50,50, 50, c3);
		ArrayList<Element> ar = new ArrayList<Element>();
		ar.add(c);
		Construction constr = new Construction (ar, "pile grise");
		this.constructions.add(constr);
		this.setCouleur(Color.AQUA);
		Cube cc = new Cube(50, 50, 50, null);
		Cube cc2 = new Cube(50,50, 50,cc);
		Cube cc3 = new Cube(50, 50, 50,cc2);
		Cube cc4= new Cube(50,50, 50, cc3);
		ArrayList<Element> arr = new ArrayList<Element>();
		arr.add(cc);
		Construction constr1 = new Construction (arr, "pile bleue");
		this.constructions.add(constr1);
		System.out.println(this.constructions.toString());
		this.majGroup(this.getTerrain());
		
		
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
		Modele.couleur = coul;;
	}
	
	public Color getCouleur() {
		return Modele.couleur;
	}
	
	public Remplissage getRemplissage() {
		return Modele.remplissage;
	}



	public Image getTexture() {
		return texture;
	}

	
	
	public void setTexture(Image texture) {
		Modele.texture = texture;
	}
	



	public Construction getTerrain() {
		
		switch(Modele.modeTerrain) {
		case CONSTRUCTION:
			this.majGroup(this.terrain);
			return this.terrain;
		case VISUALISATION:
			this.majGroup(this.constructions.get(1));
			return this.constructions.get(1);
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
			System.out.println("oui");
			FileInputStream fis = new FileInputStream("construction.xml");
			System.out.println("file");
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



	public void setElement_a_ajouter(Element_a_ajouter element_a_ajouter) {
		Modele.element_a_ajouter = element_a_ajouter;
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
	
	public  void majGroup(Construction constr) {
		this.groupe =  new Group();
		for(Element elem: constr.getBase()) {
			elem.construire(this.groupe, this);
		}
		
	}
	public Group getGroupe() {
		return this.groupe;	}
	
	

}
