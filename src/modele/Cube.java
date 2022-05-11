package modele;

import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;

public class Cube extends Element{
	
	public Cube(int longu, int haut,int prof, Modele mod, Element pere) {
		
		super(mod, pere);
		Box b = new Box(longu, haut, prof);
		this.getPos_relative()[1] = haut;
		this.getTaille()[0] = longu;
		this.getTaille()[2] = prof;
		this.getTaille()[1] = haut;
		this.setShape(b);
		this.setDestructible(true);

		if (pere != null) {
			Shape3D pereShape = pere.getShape();
			this.getPere().getFils().add(this);
			this.getShape().translateXProperty().set(pereShape.getTranslateX()- this.getPere().getPos_relative()[0]);
			this.getShape().translateYProperty().set(pereShape.getTranslateY() - this.getPere().getPos_relative()[1]);
			this.getShape().translateZProperty().set(pereShape.getTranslateZ()- this.getPere().getPos_relative()[2]);
			
		}
		
		this.setRemplissage();

	}
	
}
