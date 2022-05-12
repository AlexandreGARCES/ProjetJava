package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;

@SuppressWarnings("serial")
public class Element implements Serializable{
	
	private Color couleur;
	private Image texture;
	
	
	private ArrayList<Element> fils;
	private Element pere;
	private int n_fils;
	private Modele.Remplissage type_Remplissage;
	private boolean destructible;
	private int[] taille= {0, 0, 0};
	private int[] pos = {0, 0, 0};
	
	public Element() {}
	
	public Element( Element pere) {
		this.fils = new ArrayList<Element>();
		this.pere = pere;

		
		
	}
	
	public void construire(Group groupe, Modele mod) {
		
	}
	public void setRemplissage(Shape3D shape) {
        PhongMaterial material = new PhongMaterial();
        switch(this.getType_Remplissage()) {

        case COULEUR:
            material.setDiffuseColor(this.getCouleur());

        case TEXTURE:
            material.setDiffuseMap(this.getTexture());

        }
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
	
	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public Image getTexture() {
		return texture;
	}

	public void setTexture(Image texture) {
		this.texture = texture;
	}

	public Modele.Remplissage getType_Remplissage() {
		return type_Remplissage;
	}

	public void setType_Remplissage(Modele.Remplissage type_Remplissage) {
		this.type_Remplissage = type_Remplissage;
	}

}


