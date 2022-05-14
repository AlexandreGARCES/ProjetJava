package modele;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.Group;

public class Construction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static int largeur = 50;
	public static int longueur  = 50;
	
	private ArrayList<Element> base;
	private int couleurBase;
	private String nom;
	
	
	public Construction() {};
	
	
	public Construction (String nom, int couleurBase) {
		this.nom = nom;
		this.couleurBase=couleurBase;
		this.base=new ArrayList<Element>();
		this.createTerrain();
	}
	
	public Construction (ArrayList<Element> elements) {
		this.base= elements;
	}
	
	public void createTerrain() {//A VERIFIER AAAAAAAAAAAAAAAAAAAAAAAAA
		for(int i =-largeur/2; i< largeur/2; i++) {	
    		for(int j = -longueur/2; j<longueur/2; j++) {
    			int[] pos = {i*50, 0, j*50};
    			Cube box = new Cube(50, 50, 50,null, pos,this.couleurBase);
    			this.base.add(box);
    		}
    	}
	}
	
	public Construction ConstructionSansBase() {
		ArrayList<Element> elements=new ArrayList<Element>();
		for (Element elem : this.base) {
			for (Element fils : elem.getFils()) {
				elements.add(fils);
			}
		}
		return new Construction(elements);
	}
	
	public void afficher(Group groupe) {
		for(Element elem: this.base) {
			elem.afficher(groupe);
		}
	}
		
	
	public ArrayList<Element> getBase() {
		return base;
	}

	public void setBase(ArrayList<Element> base) {
		this.base = base;
	}
	
	public int getCouleurBase() {
		return this.couleurBase;
	}
	
	public void setCouleurBase(int couleur) {
		this.couleurBase=couleur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}




}
