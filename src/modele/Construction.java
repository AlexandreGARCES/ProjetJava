package modele;

import java.io.Serializable;
import java.util.ArrayList;

public class Construction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Element> base;
	private String nom;
	
	
	public Construction (ArrayList<Element> tab_elem, String nom) {
		
		this.nom = nom;
		this.base = tab_elem;
		for(Element elem: this.base) {
			ajoutRec(elem);
		}
		
	}
	
	public void ajoutRec(Element elem) {

			for (Element i : elem.getFils()) {
				ajoutRec(i);
			}	
		}
		
	
	public ArrayList<Element> getBase() {
		return base;
	}

	public void setBase(ArrayList<Element> base) {
		this.base = base;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
