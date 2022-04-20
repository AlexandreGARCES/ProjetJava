package modele;

public class Construction {
	
	public Element base;
	public Groupe groupe;
	
	
	public Construction (Element elem) {
		
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
