package dron_joistick;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import static dron_joistick.DronesManager.OutpuType.*;

/**
 * Implementation of Tello interface
 */
public class TelloImpl implements Tello {

	private final String name;
	private final String ipAddress;
	private final DronesManager dronesManager;
	private int statusCounter = 0;
	private volatile String lastResponse = null;

	public TelloImpl(String name, String ipAddress, DronesManager swarmManager) {
		this.name = name;
		this.ipAddress = ipAddress;
		this.dronesManager = swarmManager;
	}

	@Override
	public void send(String command) {
		try {
			lastResponse = null;
			dronesManager.send(ipAddress, command);
			if (dronesManager.getOutpuType() != DronesManager.OutpuType.NONE) {
				System.out.println(String.format("%s [%s] sent: %s", name, Utils.formatedFlightTime(this), command));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getResponse() {
		return lastResponse;
	}

	@Override
	public DronesManager getDronesManager() {
		return dronesManager;
	}

	public void status(String status) {
		if (dronesManager.getOutpuType() != NONE) {

			int divider = (dronesManager.getOutpuType() == FORMATED_FAST || dronesManager.getOutpuType() == NORMAL_FAST) ? 5 : 15;
			if (statusCounter % divider == 0) {

				if (dronesManager.getOutpuType() == FORMATED_FAST || dronesManager.getOutpuType() == FORMATED_SLOW) {
					System.out.println(String.format("%s [%s]: %s", name, Utils.formatedFlightTime(this), formatStatus(status)));
				} else {
					System.out.println(String.format("%s [%s]: %s", name, Utils.formatedFlightTime(this), status.trim()));
				}
			}

			statusCounter++;
		}
	}

	public void response(String response) {
		lastResponse = response;
		if (dronesManager.getOutpuType() != NONE) {
			System.out.println(String.format("%s [%s]: %s", name, Utils.formatedFlightTime(this), response.trim()));
		}
	}

	@Override
	public String toString() {
		return name;
	}

	private static String formatStatus(String status) {
		Map<String, String> values = Arrays
				.stream(status.trim().split(";"))
				.map(v -> v.split(":"))
				.collect(Collectors.toMap(v -> v[0], v -> v[1]));
		// if Tello Edu
		if (values.containsKey("mid")) {
			return String.format(
					"bat: %s mid: %s x: %s y: %s z: %s h: %s yaw: %s time: %s",
					values.get("bat"),
					values.get("mid"),
					values.get("x"),
					values.get("y"),
					values.get("z"),
					values.get("h"),
					values.get("yaw"),
					values.get("time")
			);
		} else {
			return String.format(
					"bat: %s h: %s yaw: %s time: %s",
					values.get("bat"),
					values.get("h"),
					values.get("yaw"),
					values.get("time")
			);
		}
	}
}
