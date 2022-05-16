package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Modele;
import vue.Gestion3D;

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
    void SwitchFXMLConstruction(ActionEvent event) throws IOException {
    	this.changerFenetre("Construction", event);
    }
    
    @FXML
    void SwitchFXMLMenu(ActionEvent event) throws IOException {
    	this.mod.raz();
    	this.retourMenu(event);
    }
    
    @FXML
    void SwitchFXMLPleinEcran(ActionEvent event) throws IOException {
    	this.changerFenetre("PleinEcran", event);
    }
    
}
