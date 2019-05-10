package pl.edu.utp.jtellolib;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Drone communications manager
 */
public class DronesManager implements AutoCloseable {

	private final DatagramSocket commandSocket;
	private final DatagramSocket statusSocket;
	private final Map<String, TelloImpl> tellos = new HashMap<>();
	private final long startTime = System.currentTimeMillis();
	private OutpuType outpuType = OutpuType.FORMATED_SLOW;
	

	/**
	 * Opens ports for communicate with Tello(s).
	 */
	public DronesManager() {
		try {
			// TODO closing connections on exit
			this.commandSocket = new DatagramSocket(8889);
			this.statusSocket = new DatagramSocket(8890);
			startResponseThread();
			startStatusThread();
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Opens ports for communicate with Tello(s).
	 *
	 * @param outpuType type of console logs
	 */
	public DronesManager(OutpuType outpuType) {
		this();
		this.outpuType = outpuType;
	}

	/**
	 * Connect to Tello drone
	 *
	 * @param name drone unique name
	 * @param ipAddress drone IP address
	 * @return Tello object
	 */
	public Tello connect(String name, String ipAddress) {
		Tello tello = create(name, ipAddress);
		tello.command();
		tello.sync(2000);
		return tello;
	}

	/**
	 * Connect to Tello Edu drone, and enable mission pads support
	 *
	 * @param name drone unique name
	 * @param ipAddress drone IP address
	 * @return Tello object
	 */
	public Tello connectEdu(String name, String ipAddress) {
		Tello tello = create(name, ipAddress);
		tello.command();
		tello.sync(2000);
		tello.mon();
		tello.sync(2000);
		tello.mdirection(0);
		tello.sync(2000);
		return tello;
	}

	/**
	 * Connects to Tello drone using defaults
	 *
	 * @return
	 */
	public Tello connect() {
		return connect("Tello", Tello.DEFAULT_IP_ADDRESS);
	}

	public void send(String address, String command) throws IOException {
		if (tellos.containsKey(address)) {

			DatagramPacket packet = new DatagramPacket(command.getBytes(), command.getBytes().length, InetAddress.getByName(address), 8889);
			commandSocket.send(packet);
		}
	}

	@Override
	public void close() {
		try {
			commandSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			statusSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets output type
	 *
	 * @param outpuType
	 */
	public void setOutpuType(OutpuType outpuType) {
		this.outpuType = outpuType;
	}

	/**
	 * Returns time from this object creation
	 * @return 
	 */
	public long getFlightTime() {
		return System.currentTimeMillis() - startTime;
	}
	
	public OutpuType getOutpuType() {
		return outpuType;
	}

	private Tello create(String name, String address) {
		TelloImpl tello = new TelloImpl(name, address, this);
		tellos.put(address, tello);
		return tello;
	}

	private void startResponseThread() {
		Thread t = new Thread(() -> {
			try {
				for (;;) {
					receiveResponse();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		t.setDaemon(true);
		t.start();
	}

	private void receiveResponse() throws IOException {
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		commandSocket.receive(packet);

		byte[] buf2 = new byte[packet.getLength()];
		System.arraycopy(buf, 0, buf2, 0, packet.getLength());
		String message = new String(buf2);

		if (tellos.containsKey(packet.getAddress().getHostAddress())) {
			tellos.get(packet.getAddress().getHostAddress()).response(message);
		}
	}

	private void startStatusThread() {
		Thread t = new Thread(() -> {
			try {
				for (;;) {
					receiveStatus();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		t.setDaemon(true);
		t.start();
	}

	private void receiveStatus() throws IOException {
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		statusSocket.receive(packet);

		byte[] buf2 = new byte[packet.getLength()];
		System.arraycopy(buf, 0, buf2, 0, packet.getLength());
		String message = new String(buf2);

		if (tellos.containsKey(packet.getAddress().getHostAddress())) {
			tellos.get(packet.getAddress().getHostAddress()).status(message);
		}
	}

	/**
	 * Type and frequency of the console logs
	 */
	public static enum OutpuType {
		/**
		 * No uutput on console
		 */
		NONE,
		/**
		 * Standard Tello satus
		 */
		NORMAL_FAST,
		/**
		 * Standard Tello satus
		 */
		NORMAL_SLOW,
		/**
		 * Most important information only
		 */
		FORMATED_FAST,
		/**
		 * Most important information only
		 */
		FORMATED_SLOW
	}

}
