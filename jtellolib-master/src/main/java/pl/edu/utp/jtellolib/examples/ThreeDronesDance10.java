package pl.edu.utp.jtellolib.examples;

import pl.edu.utp.jtellolib.DronesManager;
import pl.edu.utp.jtellolib.Tello;
import static pl.edu.utp.jtellolib.Utils.*;
import static pl.edu.utp.jtellolib.Tello.Direction.*;
import static pl.edu.utp.jtellolib.Tello.MissionPad.*;

/**
 * Choreography of 3 Tello Edu in station mode. <br>
 * Each drone should start from Mission Pad. Drone T1 should start from Mission
 * Pad 1, adn so on. Mission Pads should be placed in line: <br>
 * M3-M2-M1 <br>
 * Distance between Pads should be 120 cm.
 */
public class ThreeDronesDance10 {

	private static DronesManager dm;
	private static Tello t1, t2, t3;
	private static Tello[] swarm;

	public static void main(String[] args) {
		init();
                
                t1.takeoff();
                t1.sync();
                
//                t1.cw(180);
//                t1.sync();
//                
//                t1.up(200);
//                t1.sync();
////                
//                t1.go(0, -200, 0, 100);
//                t1.sync();
//
//                t1.curve(200, 200, 0, 0, 400, 0, 60);
//                t1.sync(20000);
//                
//                t1.curve(-200, -200, 0, 0, -400, 0, 60);
//                t1.sync(20000);
//                
//                t1.curve(200, 100, -50, 0, 200, -100, 60);
//                t1.sync();
//                
//                t1.curve(-100, -100, 0, 0, 100, 0, 60);
//                t1.sync();
                
                
//                
                
                
		applyAndSync(t -> t.land(), 20000, swarm);
	}

	private static void init() {
		dm = new DronesManager();
		t1 = dm.connectEdu("T1", "192.168.43.44");
//		t2 = dm.connectEdu("T2", "192.168.20.52");
//		t3 = dm.connectEdu("T3", "192.168.20.53");
		swarm = new Tello[]{t1};
	}
}
