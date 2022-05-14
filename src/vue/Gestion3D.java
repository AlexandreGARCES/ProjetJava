package vue;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import modele.Modele;

public class Gestion3D implements Observer{
	private static final int WIDTH = 1400;
	private static final int HEIGHT = 600;
	
	public static ArrayList<PhongMaterial>  materiaux=Gestion3D.createMateriaux();
	
    private double anchorX, anchorY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(21.0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(46.0);
	
	public static Modele mod=new Modele();
	public Group groupe;
	public Camera camera;
	public SubScene subScene3D;
	
	public static ArrayList<PhongMaterial> createMateriaux() {
		ArrayList<PhongMaterial> materiaux = new ArrayList<PhongMaterial>();
		Color[] tab = {Color.RED, Color.GREEN, Color.AQUA, Color.BLUE, Color.WHITE, Color.SILVER, Color.BLACK };
		for (Color couleur : tab) {
			PhongMaterial materiel = new PhongMaterial();
			materiel.setDiffuseColor(couleur);
			materiaux.add(materiel);
		}
		return materiaux;	
	}
	
	public Gestion3D() {
		System.out.println("nouveau gestion3D");
		mod.addObserver(this);
		
		this.majGroup();
		this.subScene3D = new SubScene(groupe, WIDTH, HEIGHT, true, null);
		
		this.camera = new PerspectiveCamera();
	    camera.setTranslateZ(-30);
	    subScene3D.setCamera(camera);

	    groupe.translateXProperty().set(WIDTH/2);
	    groupe.translateYProperty().set(HEIGHT/2);
	    groupe.translateZProperty().set(3000);
		
	    initMouseControl(groupe,subScene3D);
	}
	
    private void initMouseControl(Group group,SubScene  subScene) {
    	Rotate xRotate;
    	Rotate yRotate;
    	group.getTransforms().addAll(
    			xRotate = new Rotate(0, Rotate.X_AXIS),
    			yRotate = new Rotate(0, Rotate.Y_AXIS));
    	
    	xRotate.angleProperty().bind(angleX);
    	yRotate.angleProperty().bind(angleY);
    	
    	subScene.setOnMousePressed(event -> {
    		anchorX = event.getSceneX();
    		anchorY = event.getSceneY();
    		anchorAngleX = angleX.get();
    		anchorAngleY = angleY.get();
    	});
    	
    	subScene.setOnMouseDragged(event -> {
    		angleX.set(anchorAngleX-(anchorY- event.getSceneY()));
    		angleY.set(anchorAngleY-(anchorX- event.getSceneX()));
    		
    	});
    	
    	subScene.addEventHandler(ScrollEvent.SCROLL, event ->{
    		double delta = event.getDeltaY();
    		if(group.getTranslateZ()>-1000 || delta<=0) {
    			group.translateZProperty().set(group.getTranslateZ() - 10*delta);
    		}
    	});
	}

	public void addTouches(Scene scene) {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, e->{
    		switch(e.getCode())
    		{
    		case W:
    			mod.couleurChoisie=4;
    			break;
    		case B:
    			mod.couleurChoisie=3;
    			break;
	    	case R:
	    		mod.couleurChoisie=0;
				break;
	    	case V:
	    		mod.couleurChoisie=1;
				break;
			default:
				break;
		}
    	});		
	}
	
	public void majGroup() {//A VERIFIER ET DEPLACER AAAAAAAAAAAAAAAAAAAAAAAAA
		this.groupe =  new Group();
		System.out.println(this.mod.constructionActuelle.getCouleurBase());
		this.mod.constructionActuelle.afficher(this.groupe);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.majGroup();
		}

	
	
	
	
}
