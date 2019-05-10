package pl.edu.utp.jtellolib.examples;

import pl.edu.utp.jtellolib.DronesManager;
import pl.edu.utp.jtellolib.Tello;
import pl.edu.utp.jtellolib.Utils;
import static pl.edu.utp.jtellolib.Tello.Direction.*;
import static pl.edu.utp.jtellolib.Tello.MissionPad.*;

/**
 * Choreography of 3 Tello Edu in station mode. <br>
 * Each drone should start from Mission Pad. Drone T1 should start from Mission
 * Pad 1, adn so on. Mission Pads should be placed in line: <br>
 * M3-M2-M1 <br>
 * Distance between Pads should be 120 cm.
 */
public class ThreeDronesDance1 {

	public static void main(String[] args) {

		DronesManager dm = new DronesManager();
		Tello t1 = dm.connectEdu("T1", "192.168.20.51");
		Tello t2 = dm.connectEdu("T2", "192.168.20.52");
		Tello t3 = dm.connectEdu("T3", "192.168.20.53");
		
		t1.takeoff();
		t2.takeoff();
		t3.takeoff();
		Utils.sync(t1, t2, t3);

		t1.up(100);
		t2.up(100);
		t3.up(100);
		Utils.sync(t1, t2, t3);

		t1.cw(180);
		t2.cw(180);
		t3.cw(180);
		Utils.sync(t1, t2, t3);

		t1.forward(100);
		t2.back(100);
		t3.forward(100);
		Utils.sync(t1, t2, t3);

		t1.back(150);
		t2.forward(150);
		t3.back(150);
		Utils.sync(t1, t2, t3);

		t1.ccw(180);
		t2.ccw(180);
		t3.ccw(180);
		Utils.sync(t1, t2, t3);

		t1.flip(BACK);
		t2.flip(FORWARD);
		t3.flip(BACK);
		Utils.sync(t1, t2, t3);

		t1.back(100);
		t2.forward(100);
		t3.back(100);
		Utils.sync(t1, t2, t3);

		t1.up(50);
		t2.down(50);
		t3.up(50);
		Utils.sync(t1, t2, t3);

		t1.flip(RIGHT);
		t2.flip(FORWARD);
		t3.flip(LEFT);
		Utils.sync(t1, t2, t3);

		t1.go(0, 0, 50, 20, M1);
		t2.go(0, 0, 50, 20, M2);
		t3.go(0, 0, 50, 20, M3);
		Utils.sync(t1, t2, t3);

		t1.land();
		t2.land();
		t3.land();
		Utils.sync(t1, t2, t3);

	}

}
