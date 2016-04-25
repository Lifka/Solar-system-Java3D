/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.geometry.Sphere;
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
import javax.media.j3d.SceneGraphPath;
import javax.media.j3d.WakeupOnAWTEvent;

public class PickForStop extends Behavior{
    private WakeupOnAWTEvent condicion;
    private Canvas3D canvas;
    private PickCanvas pickCanvas;
    
    public PickForStop(Canvas3D canvas, BranchGroup bg){
        this.canvas = canvas;
        this.condicion = new WakeupOnAWTEvent(MouseEvent.MOUSE_CLICKED);
        pickCanvas = new PickCanvas ( canvas , bg ) ;
        pickCanvas.setTolerance(0.0f);
        pickCanvas.setMode(PickInfo.PICK_GEOMETRY);
        pickCanvas.setFlags(PickInfo.SCENEGRAPHPATH | PickInfo.CLOSEST_GEOM_INFO);
    }

    @Override
    public void initialize(){
        setEnable(true);
        wakeupOn(condicion);
    }

    @Override
    public void processStimulus(Enumeration cond) {

        WakeupOnAWTEvent c = (WakeupOnAWTEvent) cond.nextElement();
        AWTEvent[] e = c.getAWTEvent();
        MouseEvent m = (MouseEvent) e[0];
        pickCanvas.setShapeLocation(m);
        
        PickInfo pi = pickCanvas.pickClosest();
        
        if (pi != null){
            
            SceneGraphPath nodo = pi.getSceneGraphPath();
            Astro a = (Astro) nodo.getNode(0);
            Alpha timer = a.getTimerRotacion();
            
            if (timer != null){
                boolean parado = timer.isPaused();

                if (parado)
                    timer.resume();
                else
                    timer.pause();
            }
        }
        wakeupOn(condicion);
    }
}
