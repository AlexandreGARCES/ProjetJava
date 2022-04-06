package modele;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;

public class Boite extends Element{
	
	public int largeur, longueur;

	
	public Boite(int longu, int haut,int larg, Modele mod, Element pere) {
		super(mod, pere);
		Box b = new Box(longu, haut, larg);
		this.hauteur = haut;
		this.largeur = larg;
		this.longueur  =longu;
		this.setShape(b);
		this.setDestructible(true);
		PhongMaterial material = new PhongMaterial();
		if (pere != null) {
			Shape3D pereShape = pere.getShape();
			this.getShape().translateXProperty().set(pereShape.getTranslateX());
			this.getShape().translateYProperty().set(pereShape.getTranslateY() - this.pere.hauteur);
			this.getShape().translateZProperty().set(pereShape.getTranslateZ());
			
		}
		
		switch(this.modele.getEtat()) {

		case COULEUR:
			material.setDiffuseColor(this.modele.getCouleur());
		
		case TEXTURE:
			material.setDiffuseMap(this.modele.getTexture());
			
		}
		b.setMaterial(material);
		
		
		
		
		
	}

	

}
