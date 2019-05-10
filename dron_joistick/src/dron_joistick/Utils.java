package dron_joistick;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class
 */
public final class Utils {

	/**
	 * Waits given time
	 *
	 * @param milis miliseconds
	 */
	public static void sleep(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Waits untill all given drones finish operation, bun not longer then
	 * default timeout
	 *
	 * @param drones drones
	 */
	public static void sync(Tello... drones) {
		sync(Tello.DEFAULT_SYNC_TIMEOUT, drones);
	}

	/**
	 * Waits untill all given drones finish operation, bun not longer then
	 * timeout
	 *
	 * @param timeout timeout in miliseconds
	 * @param drones drones
	 */
	public static void sync(long timeout, Tello... drones) {
		if (timeout <= 0) {
			throw new IllegalArgumentException("Timeout must be positive");
		}
		long startTime = System.currentTimeMillis();
		// drones without response
		List<Tello> dronesForWitchWaits = Arrays
				.stream(drones)
				.filter(d -> d.getResponse() == null)
				.collect(Collectors.toList());

		// not longer then timeout
		while (System.currentTimeMillis() - startTime < timeout && !dronesForWitchWaits.isEmpty()) {
			dronesForWitchWaits = dronesForWitchWaits
					.stream()
					.filter(d -> d.getResponse() == null)
					.collect(Collectors.toList());
			Utils.sleep(50);
		}

		// if any drone has timeout
		if (dronesForWitchWaits.stream().findAny().map(t -> t.getDronesManager()).map(d -> d.getOutpuType()).map(o -> o != DronesManager.OutpuType.NONE).orElse(false)) {
			System.err.println(
					String.format(
							"%s [%s] !!! sync timeout (>%s ms)", 
							dronesForWitchWaits.stream().map(t -> t.toString()).collect(Collectors.joining(", ")), 
							dronesForWitchWaits.stream().findAny().map(t -> formatedFlightTime(t)).orElse("0.0"), 
							timeout
					)
			);
		}
	}

	/**
	 * Applays operation (command) to all drones of swarm
	 *
	 * @param operation operation
	 * @param drones drones
	 */
	public static void applyAll(Consumer<Tello> operation, Tello... drones) {
		Arrays.stream(drones)
				.forEach(operation);
	}

	/**
	 * Applys operiation to all drones, and wait until done using default timeout
	 *
	 * @param operation operation
	 * @param drones drones
	 */
	public static void applyAndSync(Consumer<Tello> operation, Tello... drones) {
		applyAll(operation, drones);
		sync(drones);
	}
	
	/**
	 * Applys operiation to all drones, and wait until done
	 *
	 * @param operation operation
	 * @param timeout timeout in miliseconds
	 * @param drones drones
	 */
	public static void applyAndSync(Consumer<Tello> operation, long timeout, Tello... drones) {
		applyAll(operation, drones);
		sync(timeout, drones);
	}
	
	public static String formatedFlightTime(Tello tello) {
		if (tello.getDronesManager() != null) {
			long flightTime = tello.getDronesManager().getFlightTime();
			return String.format("%d.%d", flightTime / 1000, flightTime % 1000 / 100);
		} else {
			return "0.0";
		}
	}
}
