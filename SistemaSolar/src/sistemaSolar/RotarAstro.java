/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;

import java.awt.event.KeyEvent;
import java.util.Enumeration;
import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;

/**
 *
 * @author jgallardo
 */
public class RotarAstro extends Behavior{
    private TransformGroup referencia;
    WakeupOnAWTEvent condicion = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
    private Transform3D rotacion = new Transform3D();
    private double angulo = 0.0;
    
    RotarAstro(TransformGroup referencia){
        this.referencia = referencia;
    }

    @Override
    public void initialize() {
        wakeupOn(condicion);
    }

    @Override
    public void processStimulus(Enumeration enmrtn) {
        angulo += 0.1;
        rotacion.rotY(angulo);
        referencia.setTransform(rotacion);
        wakeupOn(condicion);
    }
    
}
