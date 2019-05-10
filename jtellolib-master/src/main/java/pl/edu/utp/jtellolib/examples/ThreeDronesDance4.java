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
public class ThreeDronesDance4 {

	private static DronesManager dm;
	private static Tello t1, t2, t3;
	private static Tello[] swarm;

	public static void main(String[] args) {
		init();

		applyAndSync(t -> t.takeoff(), swarm);
		startPosition();

		System.out.println("******** Triangle 1 ********");
		trinagles1();
		startPosition();

		System.out.println("******** Rotate ********");
		applyAndSync(t -> t.ccw(360), swarm);

		System.out.println("******** Triangle 1 ********");
		triangles2();
		startPosition();

		System.out.println("******** Elypse ********");
		applyAndSync(t -> t.curve(100, 70, 70, 0, 100, 150, 60), swarm);
		applyAndSync(t -> t.curve(-100, -70, -70, 0, -100, -150, 60), swarm);
		startPosition();

		System.out.println("******** Flips ********");
		flipSequence1();
		flipSequence2();
		startPosition();

		System.out.println("******** Circle ********");
		circles();

		startPosition();
		applyAndSync(t -> t.land(), 20000, swarm);
	}

	private static void trinagles1() {
		applyAndSync(t -> t.go(80, 0, -10, 70), swarm);
		applyAndSync(t -> t.go(-120, 70, 120, 100), swarm);
		applyAndSync(t -> t.go(20, -70, -110, 100), swarm);

		applyAndSync(t -> t.flip(FORWARD), swarm);
	}

	private static void triangles2() {
		applyAndSync(t -> t.go(0, 0, 70, 70), swarm);

		t1.go(0, 50, -60, 70);
		t2.go(-50, 0, -60, 70);
		t3.go(0, -50, -60, 70);
		sync(swarm);

		t1.go(0, -50, -10, 70);
		t2.go(50, 0, -10, 70);
		t3.go(0, 50, -10, 70);
		sync(swarm);
	}

	private static void circles() {
		t1.curve(50, 120, -70, 0, 240, 0, 60);
		t2.ccw(180);
		t3.curve(-50, -120, 70, 0, -240, 0, 60);
		sync(swarm);

		t3.curve(50, 120, -70, 0, 240, 0, 60);
		t2.ccw(180);
		t1.curve(-50, -120, 70, 0, -240, 0, 60);
		sync(swarm);
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
		t1.go(0, 0, 110, 100, M1);
		t2.go(0, 0, 110, 100, M2);
		t3.go(0, 0, 110, 100, M3);
		sync(swarm);
	}

	private static void init() {
		dm = new DronesManager();
		t1 = dm.connectEdu("T1", "192.168.20.51");
		t2 = dm.connectEdu("T2", "192.168.20.52");
		t3 = dm.connectEdu("T3", "192.168.20.53");
		swarm = new Tello[]{t1, t2, t3};
	}
}
