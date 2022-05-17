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
    	Modele.getConstructions().remove(this.mod.constructionActuelle.getNom());
    	this.mod.raz(4);
    	this.initialize(this.url, this.rbundle);
    }
    
}
