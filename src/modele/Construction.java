package modele;

import javafx.scene.Group;

public class Construction {
	
	public Element base;
	public Groupe groupe;
	public String nom;
	
	
	public Construction (Element elem, String nom) {
		
		this.groupe = new Groupe();
		this.nom = nom;
		this.base = elem;
		this.groupe.getChildren().add(elem.getShape());
		ajoutRec(elem);
		
	}
	
	public void ajoutRec(Element elem) {
		for (Element i : elem.fils) {
			this.groupe.getChildren().add(i.getShape());
			ajoutRec(i);
		}
		
	}

}
