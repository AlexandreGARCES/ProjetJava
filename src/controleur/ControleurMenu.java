package controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.stage.Stage;
import modele.Modele;
import vue.Gestion3D;

public class ControleurMenu extends Controleur{

	//-----------------------------------------------------
	
	//PasserAUneAutreScene
	
	
	public ControleurMenu() {
		System.out.println("nouveau Controleur menu");
	}
	
	//-----------------------------------------------------

    @FXML
    private Button boutonConstruction;

    @FXML
    private Button boutonVisualisation;

    @FXML
    private ImageView imageFond;

    @FXML
    private ImageView imageLogo;
    

    @FXML
    void switchFXMLConstruction(ActionEvent event) throws IOException {
    	this.changerFenetre("Construction", event);
    }
    
    
    @FXML
    void switchFXMLVisualisation(ActionEvent event) throws IOException {
    	this.changerFenetre("Visualisation", event);
    }
}

