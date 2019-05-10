package pl.edu.utp.jtellolib.examples;

import pl.edu.utp.jtellolib.DronesManager;
import pl.edu.utp.jtellolib.Tello;
import static pl.edu.utp.jtellolib.Tello.Direction.*;

/**
 * This exapmle shows how to start using library
 */
public class SimpleDroneDance {

	public static void main(String[] args) {
		
		DronesManager dm = new DronesManager();
		Tello t1 = dm.connect();
		
		t1.takeoff().sync();
		
		t1.forward(100).sync();
		
		t1.flip(LEFT).sync();
		
		t1.land().sync();
		
		dm.close();
	}
	
}
