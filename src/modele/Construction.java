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
	
	public void createTerrain() {
		for(int i =-largeur/2; i< largeur/2; i++) {	
    		for(int j = -longueur/2; j<longueur/2; j++) {
    			int[] pos = {i*50, 0, j*50};
    			Element box;
    			if(( -1<=i && 1 >= i) && (-1 <= j && 1 >= j)) {
    				box = new Cube(50, 50, 50,null, pos,6);
    			}
    			else {
    				box = new Cube(50, 50, 50,null, pos,this.couleurBase);
    			}
    			box.setDestructible(false);
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
	
	public Construction copie(Element element) {
		ArrayList<Element> ar = new ArrayList<Element>();
		Construction cstr;
		if (element == null) {
			for (Element elem: this.getBase()) {
				int [] posi = elem.getPos();
				ar.add(((Cube)elem).copiePos(null, posi));
			}
			cstr = new Construction(ar);

		}
		else {
			
		
		for(Element elem : this.ConstructionSansBase().getBase()) {
			int[] posi = elem.getPos();
			Cube ccopie = ((Cube)elem).copiePos((Cube)element, posi);

			ar.add(ccopie);
		}
			cstr = new Construction(ar);
		
		}
			
		cstr.setCouleurBase(this.couleurBase);
		return cstr;
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


