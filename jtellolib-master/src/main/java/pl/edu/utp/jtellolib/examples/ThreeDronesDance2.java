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
public class ThreeDronesDance2 {

	private static DronesManager dm;
	private static Tello t1, t2, t3;
	private static Tello[] swarm;

	public static void main(String[] args) {
		init();
		
		applyAndSync(t -> t.takeoff(), swarm);
		startPosition();

		flipSequence1();
		flipSequence2();

		startPosition();

		applyAndSync(t -> t.curve(100, 70, 70, 0, 100, 150, 60), swarm);
		applyAndSync(t -> t.curve(-100, -70, -70, 0, -100, -150, 60), swarm);

		applyAndSync(t -> t.land(), swarm);
	}

	private static void flipSequence1() {
		t1.flip(LEFT);
		sleep(500);
		t2.flip(LEFT);
		sleep(500);
		t3.flip(LEFT);
		sleep(500);
		sync(swarm);
	}

	private static void flipSequence2() {
		t3.flip(RIGHT);
		sleep(500);
		t2.flip(RIGHT);
		sleep(500);
		t1.flip(RIGHT);
		sleep(500);
		sync(swarm);
	}

	private static void startPosition() {
		t1.go(0, 0, 100, 100, M1);
		t2.go(0, 0, 100, 100, M2);
		t3.go(0, 0, 100, 100, M3);
		sync(swarm);
	}

	private static void init() {
		// diferent console logging type
		dm = new DronesManager(DronesManager.OutpuType.NORMAL_FAST);
		t1 = dm.connectEdu("T1", "192.168.20.51");
		t2 = dm.connectEdu("T2", "192.168.20.52");
		t3 = dm.connectEdu("T3", "192.168.20.53");
		swarm = new Tello[]{t1, t2, t3};
	}
}
