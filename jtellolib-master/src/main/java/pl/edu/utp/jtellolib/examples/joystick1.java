package pl.edu.utp.jtellolib.examples;

import com.sun.xml.internal.messaging.saaj.util.CharReader;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import pl.edu.utp.jtellolib.DronesManager;
import pl.edu.utp.jtellolib.Tello;
import static pl.edu.utp.jtellolib.Utils.*;
import static pl.edu.utp.jtellolib.Tello.Direction.*;
import static pl.edu.utp.jtellolib.Tello.MissionPad.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Choreography of 3 Tello Edu in station mode. <br>
 * Each drone should start from Mission Pad. Drone T1 should start from Mission
 * Pad 1, adn so on. Mission Pads should be placed in line: <br>
 * M3-M2-M1 <br>
 * Distance between Pads should be 120 cm.
 */
public class joystick1 {

	private static DronesManager dm;
	private static Tello t1, t2, t3;
	private static Tello[] swarm;

	public static void main(String[] args) throws IOException {
            init();
            
            char c='z';
            char key='z';
            char dron='0';
            do
                {
                c=(char) System.in.read();
                if(key=='z')
                    {
                    key=c;
                    
                    switch(key)
                        {
                        case '1':
                            dron='1';
                            System.out.println("Sterowanie dronem 1 !!");
                            break;
                        case '2':
                            dron='2';
                            System.out.println("Sterowanie dronem 2 !!");
                            break;
                        case '3':
                            dron='3';
                            System.out.println("Sterowanie dronem 3 !!");
                            break;
                        case 'w':
                            goStraight(dron);
                            break;
                        case 's':
                            goBack(dron);
                            break;
                        case 'a':
                            goLeft(dron);
                            break;
                        case 'd':
                            goRight(dron);
                            break;
                        case 'q':
                            turnLeft(dron);
                            break;
                        case 'e':
                            turnRight(dron);
                            break;
                        case 'r':
                            goUp(dron);
                            break;
                        case 'f':
                            goDown(dron);
                            break;
                        case 't':
                            startDrone(dron);
                            break;
                        case 'g':
                            landDrone(dron);
                            break;
                        case '`':
                            emergency(dron);
                        }
                    key='z';
                    
                    
                    
                    
                    }
                }
            while(c!='x');
	}
        
    private static void init() {
            dm = new DronesManager();
            t1 = dm.connectEdu("T1", "192.168.43.44");
//          t2 = dm.connectEdu("T2", "192.168.20.52");
//          t3 = dm.connectEdu("T3", "192.168.20.53");
            swarm = new Tello[]{t1};
	}
        
    public static void goStraight(char dron){
        switch(dron)
            {
            case '1':
                t1.go(20, 0, 0, 50);
                t1.sync();
                break;
            case '2':
                t2.go(20, 0, 0, 50);
                t2.sync();
                break;
            case '3':
                t3.go(20, 0, 0, 50);
                t3.sync();
                break;         
            }
    }
    
    public static void goBack(char dron){
        switch(dron)
            {
            case '1':
                t1.go(-20, 0, 0, 50);
                t1.sync();
                break;
            case '2':
                t2.go(-20, 0, 0, 50);
                t2.sync();
                break;
            case '3':
                t3.go(-20, 0, 0, 50);
                t3.sync();
                break;         
            }
    }
    
    public static void goLeft(char dron){
        switch(dron)
            {
            case '1':
                t1.go(0, 20, 0, 50);
                t1.sync();
                break;
            case '2':
                t2.go(0, 20, 0, 50);
                t2.sync();
                break;
            case '3':
                t3.go(0, 20, 0, 50);
                t3.sync();
                break;         
            }
    }
    
    public static void goRight(char dron){
        switch(dron)
            {
            case '1':
                t1.go(0, -20, 0, 50);
                t1.sync();
                break;
            case '2':
                t2.go(0, -20, 0, 50);
                t2.sync();
                break;
            case '3':
                t3.go(0, -20, 0, 50);
                t3.sync();
                break;         
            }
    }

    public static void goUp(char dron){
        switch(dron)
            {
            case '1':
                t1.go(0, 0, 20, 50);
                t1.sync();
                break;
            case '2':
                t2.go(0, 0, 20, 50);
                t2.sync();
                break;
            case '3':
                t3.go(0, 0, 20, 50);
                t3.sync();
                break;         
            }
    }

    public static void goDown(char dron){
        switch(dron)
            {
            case '1':
                t1.go(0, 0, -20, 50);
                t1.sync();
                break;
            case '2':
                t2.go(0, 0, -20, 50);
                t2.sync();
                break;
            case '3':
                t3.go(0, 0, -20, 50);
                t3.sync();
                break;         
            }
    }

    public static void turnLeft(char dron){
        switch(dron)
            {
            case '1':
                t1.ccw(30);
                t1.sync();
                break;
            case '2':
                t2.ccw(30);
                t2.sync();
                break;
            case '3':
                t3.ccw(30);
                t3.sync();
                break;         
            }
    }
    
    public static void turnRight(char dron){
        switch(dron)
            {
            case '1':
                t1.cw(30);
                t1.sync();
                break;
            case '2':
                t2.cw(30);
                t2.sync();
                break;
            case '3':
                t3.cw(30);
                t3.sync();
                break;         
            }
    }
    
    public static void startDrone(char dron){
        switch(dron)
            {
            case '1':
                t1.takeoff();
                t1.sync();
                break;
            case '2':
                t2.takeoff();
                t2.sync();
                break;
            case '3':
                t3.takeoff();
                t3.sync();
                break;         
            }
    }
    
    public static void landDrone(char dron){
        switch(dron)
            {
            case '1':
                t1.land();
                t1.sync();
                break;
            case '2':
                t2.land();
                t2.sync();
                break;
            case '3':
                t3.land();
                t3.sync();
                break;         
            }
    }

    public static void emergency(char dron){
        switch(dron)
            {
            case '1':
                t1.emergency();
                t1.sync();
                break;
            case '2':
                t2.emergency();
                t2.sync();
                break;
            case '3':
                t3.emergency();
                t3.sync();
                break;         
            }
    }
}
