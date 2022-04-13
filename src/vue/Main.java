package vue;


import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;

import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import modele.Modele;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
 
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load((getClass().getResource("Menu.fxml")));
	    Scene scene = new Scene(root);
	    
	    primaryStage.setTitle("Lego");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {	
        launch(args);
    }
	
public Scene getScene() throws IOException {
	
	Modele mod = new Modele(); 	
	Group group = mod.getTerrain();

    Scene scene = new Scene(group);
    
    	
    	scene.addEventHandler(KeyEvent.KEY_PRESSED, event->{
    		switch(event.getCode())
    		{
    		case W:
    			mod.setCouleur(Color.WHITE);
    			break;
    		case B:
    			mod.setCouleur(Color.BLUE);
    			break;
    		
	    	case R:
				mod.setCouleur(Color.RED);
				break;
			
	    	case V:
				mod.setCouleur(Color.GREEN);
				break;
			default:
				break;
    
		}
    		
    	});
		
		
		return scene;
		
	}


	

}
