package modele;

import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Box;
import modele.Modele.Modes;

@SuppressWarnings("serial")
public class Cube extends Element{
	
	public Cube() {}
	
	
	public Cube(int longu, int haut,int prof, Element pere, int couleur) {
		
		super(pere,couleur);
		this.getPos()[1] = haut;
		this.getTaille()[0] = longu;
		this.getTaille()[2] = prof;
		this.getTaille()[1] = haut;
		this.setDestructible(true);
		if (this.getPere() != null) {
		int [] tab = new int[3];
		for(int i =0; i < 3; i++) {
				tab[i] = this.getPere().getPos()[i];
		}
		tab[1] -= this.getPere().getTaille()[1];
		
		this.setPos(tab);
		}



		
		
	}
	
	public Cube(int longu, int haut,int prof,Element pere, int[] pos,int couleur) {
		super(pere,couleur);
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

		
	}

	@Override
	public void afficher(Group groupe) {
		Box shape = new Box(this.getTaille()[0], this.getTaille()[1], this.getTaille()[2]);


		shape.translateXProperty().set(this.getPos()[0]);
		shape.translateYProperty().set(this.getPos()[1]);
		shape.translateZProperty().set(this.getPos()[2]);


		this.setRemplissage(shape);
		groupe.getChildren().add(shape);
		shape.setOnMouseClicked(event -> {
			{if (Modele.mode == Modes.CONSTRUCTION) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if (this.getFils().isEmpty()) {///à changer si plusieurs enfants
						Element elem = null;
						switch(Modele.element_a_ajouter) {///ce sera une construction
						case CUBE:
							Cube b1 = new Cube(50, 50, 50,this,Modele.couleurChoisie);
							elem = b1;
						case CONSTRUCTION:
							
						default:
							break;
						}
						elem.afficher(groupe);
					}
				}

				if (event.getButton() == MouseButton.SECONDARY) {	
					if (this.isDestructible()) {
						if(this.getPere() != null) {
							this.getPere().getFils().remove(this);
						}
						for(Element elem : this.getFils()) {
							elem.setPere(this.getPere());
						}
						groupe.getChildren().remove(shape);
					}
				}
			}
			}
		});
	}

}
