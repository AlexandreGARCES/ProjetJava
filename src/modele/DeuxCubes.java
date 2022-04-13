package modele;

import java.util.ArrayList;

public class DeuxCubes {
	
	public ArrayList<Cube> cubes;
	
	public DeuxCubes(int longu, int haut,int prof, Modele mod, Element pere) {
		
		cubes.add( new Cube(longu,haut,prof, mod, pere));
		cubes.add(new Cube(longu, haut, prof,mod, cubes.get(0)));

	}
	
}
