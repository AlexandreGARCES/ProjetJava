package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

@SuppressWarnings("serial")
public class Element implements Serializable{
	
	private ArrayList<Element> fils;
	private Element pere;
	private int n_fils;
	
	private Modele modele;
	private boolean destructible;
	private Shape3D shape;
	private int[] taille= {0, 0, 0};
	private int[] pos_relative = {0, 0, 0};
	
	public Element() {}
	
	public Element(Modele mod, Element pere) {
		this.fils = new ArrayList<Element>();
		this.modele = mod;
		this.pere = pere;

		
		
	}
	private void supprimerFils() {
		
		Stack<Element> stack = new Stack<Element>();

		for(Element elem : this.fils) {
			stack.add(elem);
			
		}
		while(!(stack.isEmpty())){
			stack.pop().supprimerFils();
		}
		if (this.pere != null) {
			this.pere.fils.remove(this);
		}
		this.modele.getTerrain().getChildren().remove(this.getShape());
		
		
	}
	
	private void supprimer() {
		
		if (this.pere != null) {
			this.pere.fils.remove(this);
		}
		for(Element elem : this.fils) {
			elem.pere = null;
			
		}
		this.modele.getTerrain().getChildren().remove(this.getShape());
		
	}
	
	public void setShape(Shape3D sh) {
		this.shape = sh;
		
		shape.setOnMouseClicked(event -> {
		    {
		    	if (event.getButton() == MouseButton.PRIMARY) {
		    		if (this.fils.isEmpty()) {
		    			Element elem = this.modele.ajouter(this);
		    			this.fils.add(elem);
		    			for(Element enfant: elem.fils) {
		    				this.modele.getTerrain().getChildren().add(enfant.getShape());
		    			}
		    			
		    		}
		    		
		    	}
		    	              
		    	}
		    	 if (event.getButton() == MouseButton.SECONDARY) {	
		    		if (this.destructible) {
		    			if(this.pere != null) {
				    		this.pere.fils.remove(this);
		    			}
		    			this.supprimer();
		    			
		    		}

		    	              
		    	}

    	});
		
	}
	
	public Shape3D getShape() {
		return this.shape;
	}
	

	public boolean isDestructible() {
		return destructible;
	}

	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}
	
	public void setRemplissage() {
		PhongMaterial material = new PhongMaterial();
		switch(this.modele.getRemplissage()) {

		case COULEUR:
			material.setDiffuseColor(this.modele.getCouleur());
		
		case TEXTURE:
			material.setDiffuseMap(this.modele.getTexture());
			
		}
		this.shape.setMaterial(material);
	}
	public int[] getPos_relative() {
		return pos_relative;
	}
	public void setPos_relative(int[] pos_relative) {
		this.pos_relative = pos_relative;
	}
	public int[] getTaille() {
		return taille;
	}
	public void setTaille(int[] taille) {
		this.taille = taille;
	}
	public int getN_fils() {
		return n_fils;
	}
	public void setN_fils(int n_fils) {
		this.n_fils = n_fils;
	}
	public ArrayList<Element> getFils() {
		return fils;
	}
	public void setFils(ArrayList<Element> fils) {
		this.fils = fils;
	}
	public Element getPere() {
		return pere;
	}
	public void setPere(Element pere) {
		this.pere = pere;
	}
	public Modele getModele() {
		return modele;
	}
	public void setModele(Modele modele) {
		this.modele = modele;
	}

}

