package controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ControleurPleinEcran extends Controleur{
	
    @FXML
    private Button boutonMenu;

    @FXML
    private AnchorPane pane;

    @FXML
    private SubScene subScene;

    @FXML
    void SwitchFXMLMenu(ActionEvent event) throws IOException {
    	this.changerFenetre("Visualisation", event);
    }

}

