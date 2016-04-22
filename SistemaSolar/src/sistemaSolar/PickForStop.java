/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;

import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Node;
import javax.media.j3d.PickInfo;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;

/**
 *
 * @author jgallardo
 */
public class PickForStop extends Behavior{
    private WakeupOnAWTEvent condicion;
    private Canvas3D canvas;
    private PickCanvas pickCanvas;
    
    public PickForStop(Canvas3D canvas){
        this.canvas = canvas;
        this.condicion = new WakeupOnAWTEvent(MouseEvent.MOUSE_CLICKED);
    }
    
    public void stopTransform(BranchGroup bg){
        pickCanvas = new PickCanvas ( canvas , bg ) ;
        pickCanvas.setTolerance(2.0f);
        pickCanvas.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvas.setFlags(PickInfo.NODE | PickInfo.CLOSEST_GEOM_INFO);
    }
    

    @Override
    public void initialize() {
        setEnable(true);
        wakeupOn(condicion);
    }

    @Override
    public void processStimulus(Enumeration cond) {
        System.out.println("****************** PROCESS STIMULUS ********************");
        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent m = (MouseEvent) e[0];
        pickCanvas.setShapeLocation(m);
        
        PickInfo pi = pickCanvas.pickClosest();
        
        if (pi != null){
            Node nodo_traslada = pi.getNode().getParent().getParent();
            Alpha timer = (Alpha)nodo_traslada.getUserData();
            boolean parado = timer.isPaused();
            if (parado)
                timer.resume();
            else
                timer.pause();
        }
        wakeupOn(condicion);
    }
    
}
