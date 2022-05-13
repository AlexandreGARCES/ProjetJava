package modele;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.Group;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

@SuppressWarnings("serial")
public class Element implements Serializable{
	
	private int couleur;	
	private ArrayList<Element> fils;
	private Element pere;
	private int n_fils;
	private boolean destructible;
	private int[] taille= {0, 0, 0};
	private int[] pos = {0, 0, 0};
	
	public Element() {}
	
	
	
	public Element( Element pere) {
		this.fils = new ArrayList<Element>();
		this.pere = pere;
		if (this.getPere() != null) {
			this.getPere().getFils().add(this);
		}
	}
	
	public void afficher(Group groupe, Modele mod) {
		
	}
	public void setRemplissage(Shape3D shape) {
		PhongMaterial material = Modele.materiaux.get(this.couleur);
        shape.setMaterial(material);
	}
	
	public boolean isDestructible() {
		return destructible;
	}

	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}
		/*
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
	*/
	public int[] getPos() {
		return pos;
	}
	public void setPos(int[] pos_relative) {
		this.pos= pos_relative;
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
	
	public int getCouleur() {
		return couleur;
	}

	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}


}


