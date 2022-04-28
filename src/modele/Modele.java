package modele;


import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
public class Modele {
	
	private Color couleur;
	private Image texture;
	public int largeur = 50;
	public int longueur  = 50;

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
		
		
		this.constructions = new ArrayList<Construction>();

		
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
			return this.constructions.get(1).groupe;
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
