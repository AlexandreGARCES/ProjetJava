package modele;

import java.io.Serializable;

public class Construction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Element base;
	

	private Groupe groupe;
	private String nom;
	
	
	public Construction (Element elem, String nom) {
		
		this.groupe = new Groupe();
		this.nom = nom;
		this.base = elem;
		this.groupe.getChildren().add(elem.getShape());
		ajoutRec(elem);
		
	}
	
	public void ajoutRec(Element elem) {
		for (Element i : elem.getFils()) {
			this.groupe.getChildren().add(i.getShape());
			ajoutRec(i);
		}
		
	}
	public Element getBase() {
		return base;
	}

	public void setBase(Element base) {
		this.base = base;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
