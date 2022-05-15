package modele;

import java.util.ArrayList;

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
	
	public Cube copie(Cube pere) {
		ArrayList<Element> ar = new ArrayList<Element>();
		Cube Ccopie = new Cube(this.getTaille()[0], this.getTaille()[1], this.getTaille()[2], pere, this.getCouleur());
		Ccopie.setFils(ar);
		Ccopie.setN_fils(ar.size());
		for(Element elel : this.getFils()) {
			Cube c1 = ((Cube)elel).copie(Ccopie);
			ar.add(c1);
			
		}
		
		
		
		return Ccopie;
		
	}
	
	public Cube copiePos(Cube pere, int[] posi) {
		ArrayList<Element> ar = new ArrayList<Element>();
		Cube Ccopie = new Cube(this.getTaille()[0], this.getTaille()[1], this.getTaille()[2], pere, this.getCouleur());
		Ccopie.setFils(ar);
		Ccopie.setN_fils(ar.size());
		for(Element elel : this.getFils()) {
			Cube c1 = ((Cube)elel).copie(Ccopie);
			ar.add(c1);
			
		}
		return Ccopie;
		
	}

	@Override
	public void afficher(Group groupe) {
		Box shape = new Box(this.getTaille()[0], this.getTaille()[1], this.getTaille()[2]);


		shape.translateXProperty().set(this.getPos()[0]);
		shape.translateYProperty().set(this.getPos()[1]);
		shape.translateZProperty().set(this.getPos()[2]);


		this.setRemplissage(shape);
		groupe.getChildren().add(shape);
		for(Element elem : this.getFils()) {
			elem.afficher(groupe);
		}
		shape.setOnMouseClicked(event -> {
			{if (Modele.mode == Modes.CONSTRUCTION) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if (this.getFils().isEmpty()) {///à changer si plusieurs enfants
						System.out.println(Modele.element_a_ajouter);
						/*/switch(Modele.element_a_ajouter) {///ce sera une construction
						case CUBE:
							Cube b1 = new Cube(50, 50, 50,this,Modele.couleurChoisie);
							b1.afficher(groupe);
						case CONSTRUCTION:
												default:
							break;
						}
						*/
						Construction cst = Modele.constructionaAjouter.copie(this);
							for(Element eleme : cst.getBase()) {
								eleme.afficher(groupe);
							}
							
							
							
							


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
