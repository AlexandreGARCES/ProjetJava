package modele;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
public class Modele {
	
	private Color couleur;
	private Image texture;
	public int largeur = 50;
	public int longueur  = 50;

	private Groupe terrain;
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
		for(int i =-largeur/2; i< largeur/2; i++) {	
    		for(int j = -longueur/2; j<longueur/2; j++) {
    			Cube box = new Cube(50, 50, 50, this, null);
    			box.getShape().translateXProperty().set(i*50);
    			box.getShape().translateZProperty().set(j*50);
    			box.setDestructible(false);
    			this.getTerrain().getChildren().add(box.getShape());
    			
    		}
    		
    	}
		this.setElement_a_ajouter(Element_a_ajouter.CUBE);


		
		
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
		case CUBE:
			Cube b1 = new Cube(50, 50, 50, this, pere);
			this.terrain.getChildren().add(b1.getShape());
			elem = b1;
		}
		return elem;
			
		

		
	}
	
	
	
	

}
