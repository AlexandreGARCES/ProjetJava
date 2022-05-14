package controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Modele;
import vue.Gestion3D;

public class ControleurPleinEcran {

	Stage window;
	Scene scene;
	
    @FXML
    private Button boutonMenu;

    @FXML
    private AnchorPane pane;

    @FXML
    private SubScene subScene;

    @FXML
    void SwitchFXMLMenu(ActionEvent event) throws IOException {
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	AnchorPane pane = new AnchorPane();

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/Visualisation.fxml"));
	    pane = loader.load();

	   	Gestion3D gestion3D=new Gestion3D();
	   	pane.getChildren().add(gestion3D.subScene3D);
	    scene = new Scene(pane);
	    gestion3D.addTouches(scene);
	    
	    Modele.setMode(false);
	    
	    window.setScene(scene);
	    window.show();
    }

}
