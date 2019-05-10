package dron_joistick;

/**
 * Tello drone interface <br>
 *
 * @see
 * <a href="https://dl-cdn.ryzerobotics.com/downloads/Tello/Tello%20SDK%202.0%20User%20Guide.pdf">Tello
 * SDK 2.0</a>
 */
public interface Tello {

	/**
	 * The default address with is used by Tello
	 */
	public static final String DEFAULT_IP_ADDRESS = "192.168.10.1";

	/**
	 * Default timeout for sync
	 */
	public static final long DEFAULT_SYNC_TIMEOUT = 12000;

	/**
	 * Sends command to Tello
	 *
	 * @param command command to send
	 */
	void send(String command);

	/**
	 * Retuns response of last command or null if not present
	 *
	 * @return response
	 */
	String getResponse();

	/**
	 * Returns drone manager
	 *
	 * @return drone manager or null if not present
	 */
	default DronesManager getDronesManager() {
		return null;
	}

	/**
	 * Wait until command done using DEFAULT_SYNC_TIMEOUT
	 *
	 * @return this object
	 */
	default Tello sync() {
		Utils.sync(DEFAULT_SYNC_TIMEOUT, this);
		return this;
	}

	/**
	 * Wait until command done
	 *
	 * @param timeout timeout
	 * @return this object
	 */
	default Tello sync(long timeout) {
		Utils.sync(timeout, this);
		return this;
	}

	/**
	 * Wait and returns response, but not longer then timeout
	 *
	 * @param timeout timeout in miliseconds
	 * @return response or null if not yet present
	 */
	default String getResponseSync(long timeout) {
		Utils.sync(timeout, this);
		return getResponse();
	}

	/**
	 * Enter SDK mode. <br>
	 * Automaticli send after connect to Tello
	 *
	 * @return this object
	 */
	default Tello command() {
		send("command");
		return this;
	}

	/**
	 * Auto takeoff.
	 *
	 * @return this object
	 */
	default Tello takeoff() {
		send("takeoff");
		return this;
	}

	/**
	 * Auto landing.
	 *
	 * @return this object
	 */
	default Tello land() {
		send("land");
		return this;
	}

	/**
	 * Enable video stream.
	 *
	 * @return this object
	 */
	default Tello streamon() {
		send("streamon");
		return this;
	}

	/**
	 * Disable video stream.
	 *
	 * @return this object
	 */
	default Tello straeamoff() {
		send("straeamoff");
		return this;
	}

	/**
	 * Stop motors immediately.
	 *
	 * @return this object
	 */
	default Tello emergency() {
		send("emergency");
		return this;
	}

	/**
	 * Ascend to “x” cm.
	 *
	 * @param x 20-500 cm
	 * @return this object
	 */
	default Tello up(int x) {
		send(String.format("up %d", x));
		return this;
	}

	/**
	 * Down “x” Descend to “x” cm.
	 *
	 * @param x 20-500 cm
	 * @return this object
	 */
	default Tello down(int x) {
		send(String.format("down %d", x));
		return this;
	}

	/**
	 * Fly left for “x” cm.
	 *
	 * @param x 20-500 cm
	 * @return this object
	 */
	default Tello left(int x) {
		send(String.format("left %d", x));
		return this;
	}

	/**
	 * Fly right for “x” cm.
	 *
	 * @param x 20-500 cm
	 * @return this object
	 */
	default Tello right(int x) {
		send(String.format("right %d", x));
		return this;
	}

	/**
	 * Fly forward for “x” cm.
	 *
	 * @param x 20-500 cm
	 * @return this object
	 */
	default Tello forward(int x) {
		send(String.format("forward %d", x));
		return this;
	}

	/**
	 * Fly backward for “x” cm.
	 *
	 * @param x 20-500 cm
	 * @return this object
	 */
	default Tello back(int x) {
		send(String.format("back %d", x));
		return this;
	}

	/**
	 * Rotate “x” degrees clockwise.
	 *
	 * @param x 1-360 degrees
	 * @return this object
	 */
	default Tello cw(int x) {
		send(String.format("cw %d", x));
		return this;
	}

	/**
	 * Rotate “x” degrees counterclockwise.
	 *
	 * @param x 1-360 degrees
	 * @return this object
	 */
	default Tello ccw(int x) {
		send(String.format("ccw %d", x));
		return this;
	}

	/**
	 * Flip in “x” direction. <br>
	 * “l” = left, “r” = righ,t “f” = forward, “b” = back,
	 *
	 * @param x direction
	 * @return this object
	 */
	default Tello flip(String x) {
		send(String.format("flip %s", x));
		return this;
	}

	/**
	 * Flip in “x” direction.
	 *
	 * @param x flip direction
	 * @return this object
	 */
	default Tello flip(Direction x) {
		send(String.format("flip %s", x));
		return this;
	}

	/**
	 * Fly to “x” “y” “z” at “speed” (cm/s).
	 *
	 * @param x -500-500 cm
	 * @param y -500-500 cm
	 * @param z -500-500 cm
	 * @param speed 10-100 cm/s
	 * @return this object
	 */
	default Tello go(int x, int y, int z, int speed) {
		send(String.format("go %d %d %d %d", x, y, z, speed));
		return this;
	}

	/**
	 * Hovers in the air.
	 *
	 * @return this object
	 */
	default Tello stop() {
		send("stop");
		return this;
	}

	/**
	 * Fly at a curve according to the two given coordinates at “speed” (cm/s).
	 * If the arc radius is not within a range of 0.5-10 meters, it will respond
	 * with an error.
	 *
	 * @param x1 -500-500 cm
	 * @param y1 -500-500 cm
	 * @param z1 -500-500 cm
	 * @param x2 -500-500 cm
	 * @param y2 -500-500 cm
	 * @param z2 -500-500 cm
	 * @param speed 10-60 cm/s
	 * @return this object
	 */
	default Tello curve(int x1, int y1, int z1, int x2, int y2, int z2, int speed) {
		send(String.format("curve %d %d %d %d %d %d %d", x1, y1, z1, x2, y2, z2, speed));
		return this;
	}

	/**
	 * Fly to the “x”, “y”, and “z” coordinates of the Mission Pad. <br>
	 * Tello EDU only
	 *
	 * @param x -500-500 cm
	 * @param y -500-500 cm
	 * @param z -500-500 cm
	 * @param speed 10-100 cm/s
	 * @param mid m1-m8
	 * @return this object
	 */
	default Tello go(int x, int y, int z, int speed, String mid) {
		send(String.format("go %d %d %d %d %s", x, y, z, speed, mid));
		return this;
	}

	/**
	 * Fly to the “x”, “y”, and “z” coordinates of the Mission Pad. <br>
	 * Tello EDU only
	 *
	 * @param x -500-500 cm
	 * @param y -500-500 cm
	 * @param z -500-500 cm
	 * @param speed 10-100 cm/s
	 * @param mid mission pad
	 * @return this object
	 */
	default Tello go(int x, int y, int z, int speed, MissionPad mid) {
		send(String.format("go %d %d %d %d %s", x, y, z, speed, mid));
		return this;
	}

	/**
	 * Fly at a curve according to the two given coordinates of the Mission Pad
	 * ID at “speed” (cm/s). If the arc radius is not within a range of 0.5-10
	 * meters, it will respond with an error. <br>
	 * Tello EDU only
	 *
	 * @param x1 -500-500 cm
	 * @param y1 -500-500 cm
	 * @param z1 -500-500 cm
	 * @param x2 -500-500 cm
	 * @param y2 -500-500 cm
	 * @param z2 -500-500 cm
	 * @param speed 10-60 cm/s
	 * @param mid m1-m8
	 * @return this object
	 */
	default Tello curve(int x1, int y1, int z1, int x2, int y2, int z2, int speed, String mid) {
		send(String.format("curve %d %d %d %d %d %d %d %s", x1, y1, z1, x2, y2, z2, speed, mid));
		return this;
	}

	/**
	 * Fly at a curve according to the two given coordinates of the Mission Pad
	 * ID at “speed” (cm/s). If the arc radius is not within a range of 0.5-10
	 * meters, it will respond with an error. <br>
	 * Tello EDU only
	 *
	 * @param x1 -500-500 cm
	 * @param y1 -500-500 cm
	 * @param z1 -500-500 cm
	 * @param x2 -500-500 cm
	 * @param y2 -500-500 cm
	 * @param z2 -500-500 cm
	 * @param speed 10-60 cm/s
	 * @param mid mission pad
	 * @return this object
	 */
	default Tello curve(int x1, int y1, int z1, int x2, int y2, int z2, int speed, MissionPad mid) {
		send(String.format("curve %d %d %d %d %d %d %d %s", x1, y1, z1, x2, y2, z2, speed, mid));
		return this;
	}

	/**
	 * Fly to coordinates “x”, “y”, and “z” of Mission Pad 1, and recognize
	 * coordinates 0, 0, “z” of Mission Pad 2 and rotate to the yaw value. <br>
	 * Tello EDU only
	 *
	 * @param x -500-500 cm
	 * @param y -500-500 cm
	 * @param z -500-500 cm
	 * @param speed 10-100 cm/s
	 * @param yaw 1-360 degrees
	 * @param mid1 m1-m8
	 * @param mid2 m1-m8
	 * @return this object
	 */
	default Tello jump(int x, int y, int z, int speed, int yaw, String mid1, String mid2) {
		send(String.format("jump %d %d %d %d %d %s %s", x, y, z, speed, yaw, mid1, mid2));
		return this;
	}

	/**
	 * Fly to coordinates “x”, “y”, and “z” of Mission Pad 1, and recognize
	 * coordinates 0, 0, “z” of Mission Pad 2 and rotate to the yaw value. <br>
	 * Tello EDU only
	 *
	 * @param x -500-500 cm
	 * @param y -500-500 cm
	 * @param z -500-500 cm
	 * @param speed 10-100 cm/s
	 * @param yaw 1-360 degrees
	 * @param mid1 mission pad
	 * @param mid2 mission pad
	 * @return this object
	 */
	default Tello jump(int x, int y, int z, int speed, int yaw, MissionPad mid1, MissionPad mid2) {
		send(String.format("jump %d %d %d %d %d %s %s", x, y, z, speed, yaw, mid1, mid2));
		return this;
	}

	/**
	 * Set speed to “x” cm/s.
	 *
	 * @param x 10-100 cm/s
	 * @return this object
	 */
	default Tello speed(int x) {
		send(String.format("speed %d", x));
		return this;
	}

	/**
	 * Set remote controller control via four channels.
	 *
	 * @param a left/right (-100-100)
	 * @param b forward/backward (-100-100)
	 * @param c up/down (-100-100)
	 * @param d yaw (-100-100)
	 * @return this object
	 */
	default Tello rc(int a, int b, int c, int d) {
		send(String.format("rc %d %d %d %d", a, b, c, d));
		return this;
	}

	/**
	 * Set Wi-Fi password.
	 *
	 * @param ssid updated Wi-Fi name
	 * @param pass updated Wi-Fi password
	 * @return this object
	 */
	default Tello ssid(String ssid, String pass) {
		send(String.format("ssid %s %s", ssid, pass));
		return this;
	}

	/**
	 * Enable mission pad detection (both forward and downward detection). <br>
	 * Tello EDU only
	 *
	 * @return this object
	 */
	default Tello mon() {
		send("mon");
		return this;
	}

	/**
	 * Disable mission pad detection. <br>
	 * Tello EDU only
	 *
	 * @return this object
	 */
	default Tello moff() {
		send("moff");
		return this;
	}

	/**
	 * Mission pad detection direction. <br>
	 * Tello EDU only
	 *
	 * @param x 0 - Enable downward detection only, 1 - Enable forward detection
	 * only, 2 - Enable both forward and downward detection
	 * @return this object
	 */
	default Tello mdirection(int x) {
		send(String.format("mdirection %d", x));
		return this;
	}

	/**
	 * Set the Tello to station mode, and connect to a new access point with the
	 * access point’s ssid and password. <br>
	 * Tello EDU only
	 *
	 * @param ssid updated Wi-Fi name
	 * @param pass updated Wi-Fi password
	 * @return this object
	 */
	default Tello ap(String ssid, String pass) {
		send(String.format("ap %s %s", ssid, pass));
		return this;
	}

	/**
	 * Obtain current speed (cm/s).
	 *
	 * @return this object
	 */
	default Tello speed() {
		send("speed?");
		return this;
	}

	/**
	 * Obtain current battery percentage.
	 *
	 * @return this object
	 */
	default Tello battery() {
		send("battery?");
		return this;
	}

	/**
	 * Obtain current flight time.
	 *
	 * @return this object
	 */
	default Tello time() {
		send("time?");
		return this;
	}

	/**
	 * Obtain Wi-Fi SNR.
	 *
	 * @return this object
	 */
	default Tello wifi() {
		send("wifi?");
		return this;
	}

	/**
	 * Obtain the Tello SDK version.
	 *
	 * @return this object
	 */
	default Tello sdk() {
		send("sdk?");
		return this;
	}

	/**
	 * Obtain the Tello serial number.
	 *
	 * @return this object
	 */
	default Tello sn() {
		send("sn?");
		return this;
	}

	public static enum Direction {

		LEFT("l"),
		RIGHT("r"),
		FORWARD("f"),
		BACK("b");
		private final String symbol;

		private Direction(String direction) {
			this.symbol = direction;
		}

		@Override
		public String toString() {
			return symbol;
		}

	}

	public static enum MissionPad {
		M1("m1"),
		M2("m2"),
		M3("m3"),
		M4("m4"),
		M5("m5"),
		M6("m6"),
		M7("m7"),
		M8("m8");
		private final String symbol;

		private MissionPad(String symbol) {
			this.symbol = symbol;
		}

		@Override
		public String toString() {
			return symbol;
		}

	}

}
