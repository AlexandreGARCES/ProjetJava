package controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Modele;

public class ControleurMenu {

    @FXML
    private Button boutonCatalgue;

    @FXML
    private Button boutonJouer;
    
    @FXML
    private ImageView imageFond;

    @FXML
    private ImageView imageLogo;

    @FXML
    public void afficherJeu(ActionEvent event) throws IOException {
    	Stage window = (Stage) boutonJouer.getScene().getWindow();
    	Modele mod = new Modele(); 	
		Group group = mod.getTerrain();
		AnchorPane pane = new AnchorPane();
		SubScene subScene = new SubScene(group, 1400, 800, true, null);
		
		Camera camera = new PerspectiveCamera();
	    camera.setTranslateZ(-30);
	    subScene.setCamera(camera);
	    group.translateZProperty().set(3000);
		
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Jeu.fxml"));
	    pane = loader.load();
	    pane.getChildren().add(0,subScene);
	    Scene scene = new Scene(pane);
	    
	    window.setScene(scene);
	    window.show();
    }
}
