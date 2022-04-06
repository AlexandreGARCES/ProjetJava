package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
public class Modele {
	
	private Color couleur;
	private Image texture;
	public int largeur = 50;
	public int longueur  = 50;
    private final DoubleProperty larg = new SimpleDoubleProperty(largeur);
    private final DoubleProperty longu = new SimpleDoubleProperty(longueur);

	private Groupe terrain;
	public enum Etat {
		COULEUR, TEXTURE
	}
	public enum Element_a_ajouter{
		BOITE
	}
	private Element_a_ajouter element_a_ajouter;
	private  Etat etat;
	
	public Modele() {
		
		this.couleur = Color.SILVER;
		this.texture = null;
		this.etat = Etat.COULEUR;
		this.setTerrain(new Groupe());
		for(int i =0; i< largeur; i++) {	
    		for(int j = 0; j<longueur; j++) {
    			Boite box = new Boite(50, 50, 50, this, null);
    			box.getShape().translateXProperty().set(i*50);
    			box.getShape().translateZProperty().set(j*50);
    			box.setDestructible(false);
    			this.getTerrain().getChildren().add(box.getShape());
    			
    		}
    		
    	}
		this.setElement_a_ajouter(Element_a_ajouter.BOITE);
    	this.getTerrain().translateXProperty().bind(larg);;
    	this.getTerrain().translateYProperty().bind(longu);


		
		
	}



	public void setCouleur(Color coul) {
		this.couleur = coul;;
	}
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public Etat getEtat() {
		return this.etat;
	}



	public Image getTexture() {
		return texture;
	}



	public void setTexture(Image texture) {
		this.texture = texture;
	}
	



	public Groupe getTerrain() {
		return this.terrain;
	}



	public void setTerrain(Groupe terrain) {
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
		case BOITE:
			Boite b1 = new Boite(50, 50, 50, this, pere);
			this.terrain.getChildren().add(b1.getShape());
			elem = b1;
		}
		return elem;
			
		

		
	}
	
	
	
	

}
