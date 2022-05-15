package vue;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException{
		primaryStage.setOnCloseRequest(event -> {
		    //Modele.sauvegarderModele();
			//penser à l'enlever de ControleurConstruction
		});
		Parent root = FXMLLoader.load((getClass().getResource("Menu.fxml")));
	    Scene scene = new Scene(root);
	    
	    primaryStage.setTitle("LEGO");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {	
        launch(args);
    }
}
