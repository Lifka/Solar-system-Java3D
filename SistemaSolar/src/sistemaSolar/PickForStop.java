/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;

import com.sun.j3d.utils.pickfast.PickCanvas;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PickInfo;
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
    

    @Override
    public void initialize() {
        setEnable(false);
        wakeupOn(condicion);
    }
    
    public void initSearch(BranchGroup bg){
        pickCanvas = new PickCanvas(canvas,bg);
        pickCanvas.setTolerance(0.0f);
        pickCanvas.setMode(PickInfo.PICK_BOUNDS);
        //pickCanvas.setFlags();
        setEnable(true);
    }

    @Override
    public void processStimulus(Enumeration enmrtn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
