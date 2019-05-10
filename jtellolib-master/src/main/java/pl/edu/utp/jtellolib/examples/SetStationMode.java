package pl.edu.utp.jtellolib.examples;

import pl.edu.utp.jtellolib.DronesManager;
import pl.edu.utp.jtellolib.Tello;
import pl.edu.utp.jtellolib.Utils;

/**
 * This code shows how to set Station Mode on Tello (Tello will be WiFi client)
 * <br>
 * Tello EDU only
 */
public class SetStationMode {

	public static void main(String[] args) {

//		if (args.length < 2) {
//			System.err.println("Too less arguments, SSID and password needed");
//			return;
//		}

		useAp("Redmi", "Mieszkanie+");
	}

	public static void useAp(String ssid, String password) {
		DronesManager dm = new DronesManager();

		Tello tello = dm.connect("Tello", Tello.DEFAULT_IP_ADDRESS);

		tello.ap(ssid, password);
		Utils.sleep(5000);
	}

}
