package modele;

import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.shape.Shape3D;

public class Element{
	
	
	public ArrayList<Element> fils;
	public Element pere;
	public int n_fils;
	public Modele modele;
	private boolean destructible;
	private Shape3D shape;
	public int hauteur;
	
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
		shape.setOnMousePressed(event -> {
		    {
		    	if (event.isPrimaryButtonDown()) {
		    		if (this.fils.isEmpty()) {		    			
		    			Element elem = this.modele.ajouter(this);
		    			this.fils.add(elem);
		    			
		    		}
		    		
		    	}
		    	              
		    	}
		    	 if (event.isSecondaryButtonDown()) {	
		    		if (this.destructible) {
		    			System.out.println(this.pere +  " pere");
		    			if(this.pere != null) {
				    		this.pere.fils.remove(this);
				    		System.out.println(this.pere.fils);
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

}

