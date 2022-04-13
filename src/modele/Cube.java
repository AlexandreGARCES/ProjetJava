package modele;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;

public class Cube extends Element{
	

	
	public Cube(int longu, int haut,int prof, Modele mod, Element pere) {
		super(mod, pere);
		Box b = new Box(longu, haut, prof);
		this.pos_relative[1] = haut;
		this.taille[0] = longu;
		this.taille[2] = prof;
		this.taille[1] = haut;
		this.setShape(b);
		this.setDestructible(true);

		if (pere != null) {
			Shape3D pereShape = pere.getShape();
			this.getShape().translateXProperty().set(pereShape.getTranslateX()- this.pere.pos_relative[0]);
			this.getShape().translateYProperty().set(pereShape.getTranslateY() - this.pere.pos_relative[1]);
			this.getShape().translateZProperty().set(pereShape.getTranslateZ()- this.pere.pos_relative[2]);
			
		}
		
		this.setRemplissage();
		

		
		
		
		
		
	}

	

}
