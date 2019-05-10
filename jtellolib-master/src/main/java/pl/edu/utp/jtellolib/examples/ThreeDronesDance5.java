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
public class ThreeDronesDance5 {

	private static DronesManager dm;
	private static Tello t1, t2, t3;
	private static Tello[] swarm;

	public static void main(String[] args) {
		init();

		System.out.println("******** Take off and flip ********");
		takeOffAndFlips();

		startPosition();

		System.out.println("******** Wave ********");
		waves();

		startPosition();

		System.out.println("******** Triangle ********");
		triangle();
	
		startPosition();
		
		System.out.println("******** Up and down ********");
		upAndDown();

		startPosition();

		System.out.println("******** Sorting ********");
		sorting();

		startPosition();
		applyAndSync(t -> t.land(), 20000, swarm);
	}

	private static void takeOffAndFlips() {
		t1.takeoff();
		sleep(1000);
		t2.takeoff();
		sleep(1000);
		t3.takeoff();
		sync(swarm);

//		t3.flip(FORWARD);
//		sleep(700);
//		t2.flip(FORWARD);
//		sleep(700);
//		t1.flip(FORWARD);
//		sync(swarm);
	}

	private static void waves() {
		t1.up(100);
		sleep(700);
		t2.up(100);
		sleep(700);
		t3.up(100);
		sleep(200);

		t1.sync();
		t1.down(100);
		t2.sync();
		t2.down(100);
		t3.sync();
		t3.down(100);

		t1.sync();
		t1.up(100);
		t2.sync();
		t2.up(100);
		t3.sync();
		t3.up(100);

		t1.sync();
		t1.down(100);
		t2.sync();
		t2.down(100);
		t3.sync();
		t3.down(100);

		sync(swarm);
	}

	private static void triangle() {
		final int distX = 150;
		final int distZ = 150;
		
		t2.go(-distX, 0, distZ, 100);
		t2.sync();
		
		t1.go(0, 240, 0, 100);
		t2.go(distX, -120, -distZ, 100);
		t3.go(-distX, -120, distZ, 100);
		sync(swarm);
		
		t2.go(0, 240, 0, 100);
		t3.go(distX, -120, -distZ, 100);
		t1.go(-distX, -120, distZ, 100);
		sync(swarm);
		
		t3.go(0, 240, 0, 100);
		t1.go(distX, -120, -distZ, 100);
		t2.go(-distX, -120, distZ, 100);
		sync(swarm);
		
		t2.go(distX, 0, -distZ, 100);
		t2.sync();
	}

	private static void upAndDown() {
		t1.go(-20, 0, 60, 100);
		t2.go(20, 0, -60, 100);
		t3.go(-20, 0, 60, 100);
		sync(swarm);
		t1.go(40, 0, -120, 100);
		t2.go(-40, 0, 120, 100);
		t3.go(40, 0, -120, 100);
		sync(swarm);
		t1.go(-20, 0, 60, 100);
		t2.go(20, 0, -60, 100);
		t3.go(-20, 0, 60, 100);
		sync(swarm);
	}
	
	private static void sorting() {
		t1.flip(LEFT);
		sleep(700);
		t2.flip(LEFT);
		sleep(700);
		t3.flip(LEFT);
		sync(swarm);

		t1.curve(0, 60, -40, 0, 120, 0, 60);
		t2.curve(0, -60, 60, 0, -120, 0, 60);
		sync(t1, t2);
		t1.curve(0, 60, -40, 0, 120, 0, 60);
		t3.curve(0, -60, 60, 0, -120, 0, 60);
		sync(t1, t3);
		t2.curve(0, 60, -40, 0, 120, 0, 60);
		t3.curve(0, -60, 60, 0, -120, 0, 60);
		sync(t2, t3);

		t1.flip(RIGHT);
		sleep(700);
		t2.flip(RIGHT);
		sleep(700);
		t3.flip(RIGHT);
		sync(swarm);
		
		t3.curve(0, 120, -70, 0, 240, 0, 60);
		t1.curve(0, -120, 70, 0, -240, 0, 60);
		sync(t3, t1);
	}

	private static void startPosition() {
		t1.go(0, 0, 110, 100, M1);
		t2.go(0, 0, 110, 100, M2);
		t3.go(0, 0, 110, 100, M3);
		sync(10000, swarm);
	}

	private static void init() {
		dm = new DronesManager();
		t1 = dm.connectEdu("T1", "192.168.20.51");
		t2 = dm.connectEdu("T2", "192.168.20.52");
		t3 = dm.connectEdu("T3", "192.168.20.53");
		swarm = new Tello[]{t1, t2, t3};
	}
}
