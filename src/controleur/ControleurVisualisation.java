package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import modele.Modele;

public class ControleurVisualisation extends Controleur implements Initializable {

	ArrayList<String> constructions;

	public ControleurVisualisation() {
		this.constructions=this.mod.getListeConstructions();
	}
	
	//-----------------------------------------------------
	
	//rechercheMultiCrit (Pour n'avoir que la liste des constructions qui nous intéressent)

	/*
	this.constructions.clear();
	this.constructions.add("OUIIIIIIII");
	this.constructions.add("NOOOOOOOOOOOOOOOOON");
	this.initialize(this.url, this.rbundle);
	*/
	
	//-----------------------------------------------------
	
	@FXML
    private SubScene subScene3D;

    @FXML
    private Button boutonMenu;

    @FXML
    private Button boutonModifier;

    @FXML
    private Button boutonPleinEcran;
    
    @FXML
    private Button boutonSupprimer;
    

    
    @FXML
    void SwitchFXMLConstruction(ActionEvent event) throws IOException {
    	this.changerFenetre("Construction", event);
    }
    
    @FXML
    void SwitchFXMLMenu(ActionEvent event) throws IOException {
    	this.mod.raz(4);
    	this.retourMenu(event);
    }
    
    @FXML
    void SwitchFXMLPleinEcran(ActionEvent event) throws IOException {
    	this.changerFenetre("PleinEcran", event);
    }
    
    @FXML
    void supprimerConstruction(ActionEvent event) {
    	System.out.println(Modele.getConstructions());
    	Modele.getConstructions().remove(this.mod.constructionActuelle.getNom());
    	this.initialize(this.url, this.rbundle);
    }
    
}
