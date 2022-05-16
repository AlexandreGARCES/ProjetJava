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
		this.setDestructible(true);

		
	}
	
	public Cube copie(Cube pere) {
		ArrayList<Element> ar = new ArrayList<Element>();
		int [] pos = {0, 0, 0};
		Cube Ccopie = new Cube(this.getTaille()[0], this.getTaille()[1], this.getTaille()[2], pere, pos, this.getCouleur());
		Ccopie.setDestructible(this.isDestructible());

		for(Element elel : this.getFils()) {
			Cube c1 = ((Cube)elel).copie(Ccopie);
			ar.add(c1);
			
		}
		Ccopie.setFils(ar);
		Ccopie.setN_fils(ar.size());
		
		
		return Ccopie;
		
	}
	
	public Cube copiePos(Cube pere, int[] posi) {
		ArrayList<Element> ar = new ArrayList<Element>();
		int [] pos = {0, 0, 0};
		Cube Ccopie = new Cube(this.getTaille()[0], this.getTaille()[1], this.getTaille()[2], pere, pos, this.getCouleur());
		Ccopie.setDestructible(this.isDestructible());
		int[] oui = Ccopie.getPos();
		for(int i =0; i<oui.length;i++) {
			oui[i] += posi[i];
			
		}
		Ccopie.setPos(oui);
		for(Element elel : this.getFils()) {
			int [] tab = {0, 0, 0};
			for(int i =0; i<3; i++) {
				tab[i] = elel.getPos()[i]-this.getPos()[i];
			}
			tab[1] += 50;
			Cube c1 = ((Cube)elel).copiePos(Ccopie, tab);
			ar.add(c1);
			
		}
		Ccopie.setN_fils(ar.size());
		Ccopie.setFils(ar);
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
						
						Construction cst = Modele.constructionaAjouter.copie(this);
						System.out.println(cst.getBase().size());
							for(Element eleme : cst.getBase()) {
								eleme.afficher(groupe);
								this.getFils().add(eleme);
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
							this.getPere().getFils().add(elem);
						}
						groupe.getChildren().remove(shape);
					}
				}
			}
			}
		});
	}

}
