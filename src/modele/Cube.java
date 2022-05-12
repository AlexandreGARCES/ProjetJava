package modele;

import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;

@SuppressWarnings("serial")
public class Cube extends Element{
	
	
	public Cube(int longu, int haut,int prof, Element pere) {
		
		super(pere);
		this.getPos()[1] = haut;
		this.getTaille()[0] = longu;
		this.getTaille()[2] = prof;
		this.getTaille()[1] = haut;
		this.setDestructible(true);
		System.out.println(Modele.remplissage);
		switch(Modele.remplissage) {
		case COULEUR:
			this.setCouleur(Modele.couleur);
			System.out.println(this.getCouleur());
			this.setType_Remplissage(Modele.remplissage);
		case TEXTURE:
			this.setTexture(Modele.texture);
			this.setType_Remplissage(Modele.remplissage);
		}
		System.out.println(this.getType_Remplissage());
		if (this.getPere() != null) {
		int [] tab = new int[3];
		for(int i =0; i < 3; i++) {
				tab[i] = this.getPere().getPos()[i];
		}
		tab[1] -= this.getPere().getTaille()[1];
		
		this.setPos(tab);
		}



		
		
	}
	
	public Cube(int longu, int haut,int prof,Element pere, int[] pos) {
		super(pere);
		this.getTaille()[0] = longu;
		this.getTaille()[2] = prof;
		this.getTaille()[1] = haut;
		if (pere!= null) {
			int [] tab = new int[3];
			for(int i =0; i < this.getTaille().length; i++) {
				tab[i] = this.getPere().getPos()[i] + pos[i];
			}
			tab[1] -= this.getPere().getTaille()[1];
			this.setPos(tab);
		}
		else {
			this.setPos(pos);
		}
		this.setDestructible(false);
		
		switch(Modele.remplissage) {
		case COULEUR:
			this.setCouleur(Modele.couleur);
			this.setType_Remplissage(Modele.Remplissage.COULEUR);
		case TEXTURE:
			this.setTexture(Modele.texture);
			this.setType_Remplissage(Modele.Remplissage.TEXTURE);
		}

		
	}

	@Override
	public void construire(Group groupe, Modele mod) {
		Box shape = new Box(this.getTaille()[0], this.getTaille()[1], this.getTaille()[2]);


		shape.translateXProperty().set(this.getPos()[0]);
		shape.translateYProperty().set(this.getPos()[1]);
		shape.translateZProperty().set(this.getPos()[2]);


		this.setRemplissage(shape);
		groupe.getChildren().add(shape);

		shape.setOnMouseClicked(event -> {
			{
				if (event.getButton() == MouseButton.PRIMARY) {
					if (this.getFils().isEmpty()) {
						Element elem = null;
						switch(Modele.element_a_ajouter) {
						case CUBE:
							Cube b1 = new Cube(50, 50, 50,this);
							elem = b1;
						default:
							break;

						}
						elem.construire(groupe, mod);

						this.getFils().add(elem);
						for(Element enfant: elem.getFils()) {
							enfant.construire(groupe, mod);
						}

					}

				}
				mod.majGroup(mod.getTerrain());

			}
			if (event.getButton() == MouseButton.SECONDARY) {	
				if (this.isDestructible()) {
					if(this.getPere() != null) {
						this.getPere().getFils().remove(this);
					}
					for(Element elem : this.getFils()) {
						elem.setPere(null);

					}
					groupe.getChildren().remove(shape);
				}


			}
			mod.majGroup(mod.getTerrain());

		});


	}

}
