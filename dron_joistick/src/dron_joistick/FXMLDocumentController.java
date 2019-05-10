/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dron_joistick;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

/**
 *
 * @author pawel
 */
public class FXMLDocumentController implements Initializable {
    
    //pola i obiekty
    public int dron=0;  //wybrany dron
    public int[] connect={0,0,0};
    public int[] started={0,0,0};
    
    
    private static DronesManager dm;
    private static Tello t1, t2, t3;
    
    
    
    //przyciski do wyboru i połączenia
    @FXML
    private Button connectButton;
    @FXML
    private RadioButton d1Radio;
    @FXML
    private RadioButton d2Radio;
    @FXML
    private RadioButton d3Radio;
    
    //przyciski do sterowania
    @FXML
    private Button startButton;
    @FXML
    private Button landButton;
    @FXML
    private Button emergencyButton;
    @FXML
    private Button forwardButton;
    @FXML
    private Button backButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button upButton;
    @FXML
    private Button downButton;
    @FXML
    private Button turnLeftButton;
    @FXML
    private Button turnRightButton;
    
    //wyświetlanie informacji
    @FXML
    private TextArea log;
    
    //obsługa przycisków wyboru i połączeń
    @FXML
    public void setD1(){
        dron=1;
        d2Radio.setSelected(false);
        d3Radio.setSelected(false);
        
        d1Radio.setDisable(true);
        d2Radio.setDisable(false);
        d3Radio.setDisable(false);
        setKeys();
    }
    
    @FXML
    public void setD2(){
        dron=2;
        d1Radio.setSelected(false);
        d3Radio.setSelected(false);
        
        d1Radio.setDisable(false);
        d2Radio.setDisable(true);
        d3Radio.setDisable(false);
        setKeys();
    }
    
    @FXML
    public void setD3(){
        dron=3;
        d1Radio.setSelected(false);
        d2Radio.setSelected(false);
        
        d1Radio.setDisable(false);
        d2Radio.setDisable(false);
        d3Radio.setDisable(true);
        setKeys();
    }
    
    @FXML
    public void connect(){
        if(connect[dron-1]==0) 
            {
            connect[dron-1]=1;
            connectButton.setDisable(true);
            switch(dron)
                {
                case 1:
                    t1 = dm.connectEdu("T1", "192.168.43.44");
                    break;
                case 2:
                    t2 = dm.connectEdu("T2", "192.168.20.52");
                    break;
                case 3:
                    t3 = dm.connectEdu("T3", "192.168.20.53");
                    break;
                }
                setKeys();
                
            }
        
    }
    
    //obsługa przycisków sterowania
    @FXML
    public void goForward(){
        switch(dron)
            {
            case 1:
                t1.forward(30);
                t1.sync();
                break;
            case 2:
                t2.forward(30);
                t2.sync();
                break;
            case 3:
                t3.forward(30);
                t3.sync();
                break;
            }
    }
    
    @FXML
    public void goBack(){
        switch(dron)
            {
            case 1:
                t1.back(30);
                t1.sync();
                break;
            case 2:
                t2.back(30);
                t2.sync();
                break;
            case 3:
                t3.back(30);
                t3.sync();
                break;
            }
    }
    
    @FXML
    public void goLeft(){
        switch(dron)
            {
            case 1:
                t1.left(30);
                t1.sync();
                break;
            case 2:
                t2.left(30);
                t2.sync();
                break;
            case 3:
                t3.left(30);
                t3.sync();
                break;
            }
    }
    
    @FXML
    public void goRight(){
        switch(dron)
            {
            case 1:
                t1.right(30);
                t1.sync();
                break;
            case 2:
                t2.right(30);
                t2.sync();
                break;
            case 3:
                t3.right(30);
                t3.sync();
                break;
            }
    }
    
    @FXML
    public void turnLeft(){
        switch(dron)
            {
            case 1:
                t1.ccw(30);
                t1.sync();
                break;
            case 2:
                t2.ccw(30);
                t2.sync();
                break;
            case 3:
                t3.ccw(30);
                t3.sync();
                break;
            }
    }
    
    @FXML
    public void turnRight(){
        switch(dron)
            {
            case 1:
                t1.cw(30);
                t1.sync();
                break;
            case 2:
                t2.cw(30);
                t2.sync();
                break;
            case 3:
                t3.cw(30);
                t3.sync();
                break;
            }
    }
    
    @FXML
    public void goLower(){
        switch(dron)
            {
            case 1:
                t1.down(20);
                t1.sync();
                break;
            case 2:
                t2.down(20);
                t2.sync();
                break;
            case 3:
                t3.down(20);
                t3.sync();
                break;
            }
    }
    
    @FXML
    public void goHigher(){
        switch(dron)
            {
            case 1:
                t1.up(20);
                t1.sync();
                break;
            case 2:
                t2.up(20);
                t2.sync();
                break;
            case 3:
                t3.up(20);
                t3.sync();
                break;
            }
    }
    
    @FXML
    public void emergency(){
        switch(dron)
            {
            case 1:
                t1.emergency();
                break;
            case 2:
                t2.emergency();
                break;
            case 3:
                t3.emergency();
                break;
            }
        connect[dron-1]=0;
    }
    
    @FXML
    public void start(){
        switch(dron)
            {
            case 1:
                t1.takeoff();
                t1.sync();
                break;
            case 2:
                t2.takeoff();
                t2.sync();
                break;
            case 3:
                t3.takeoff();
                t3.sync();
                break;
            }
    }
    
    @FXML
    public void land(){
        switch(dron)
            {
            case 1:
                t1.land();
                t1.sync();
                break;
            case 2:
                t2.land();
                t2.sync();
                break;
            case 3:
                t3.land();
                t3.sync();
                break;
            }
    }
    
    //pozostałe metody wspomagające
    public void setKeys(){
        if(dron>=1 && dron<=3)
            {
            connectButton.setDisable(false);
            
            if(connect[dron-1]==1)
                {
                startButton.setDisable(false);
                landButton.setDisable(false);
                forwardButton.setDisable(false);
                backButton.setDisable(false);
                leftButton.setDisable(false);
                rightButton.setDisable(false);
                turnLeftButton.setDisable(false);
                turnRightButton.setDisable(false);
                upButton.setDisable(false);
                downButton.setDisable(false);
                forwardButton.setDisable(false);
                emergencyButton.setDisable(false);
                }
            else 
                {
                startButton.setDisable(true);
                landButton.setDisable(true);
                forwardButton.setDisable(true);
                backButton.setDisable(true);
                leftButton.setDisable(true);
                rightButton.setDisable(true);
                turnLeftButton.setDisable(true);
                turnRightButton.setDisable(true);
                upButton.setDisable(true);
                downButton.setDisable(true);
                forwardButton.setDisable(true);
                emergencyButton.setDisable(true);
                }
                
            }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        
        dm = new DronesManager();
        setKeys();
        
        Thread logi=new Thread()
            {
            public void run()
                {    
                while(true)
                    {
                    log.setText(baos.toString());
                    }
                }    
            };
        
        logi.start(); 
        
    }    
    
}
