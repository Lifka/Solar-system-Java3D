/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.ViewingPlatform;
import java.util.ArrayList;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author lifka
 */
public class Camaras {
    
    ArrayList<View> views = new ArrayList();
    
    public Camaras(){
        
    }
    
    public View getView(int i){
        return views.get(i);
    }
    
       
    public TransformGroup getViewPlanta(){
        // TransformGroup para posicionar y orientar la vista
        Transform3D transformPlanta = new Transform3D ();
        transformPlanta.lookAt(new Point3d(0, 15, 0), new Point3d (0, 0, 0),
                new Vector3d(0, 0, -1));
        
        transformPlanta.invert();
        
        TransformGroup tgPlanta = new TransformGroup(transformPlanta);
        ViewPlatform vpPlanta = new ViewPlatform();
        
        tgPlanta.addChild(vpPlanta);
        
        // Definición de la vista paralela
        
        View viewPlanta = new View();
        viewPlanta.setPhysicalBody(new PhysicalBody());
        viewPlanta.setPhysicalEnvironment(new PhysicalEnvironment());
        viewPlanta.setProjectionPolicy(View .PARALLEL_PROJECTION);
        viewPlanta.setScreenScalePolicy(View.SCALE_EXPLICIT);
        viewPlanta.setScreenScale(0.01);
        viewPlanta.setFrontClipDistance(0.1);
        viewPlanta.setBackClipDistance(20);
        
        viewPlanta.attachViewPlatform(vpPlanta);
        
        tgPlanta.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tgPlanta.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        views.add(viewPlanta);
        
        return tgPlanta;
    }
    
    public TransformGroup getViewPerspective(Canvas3D aCanvas){
        Transform3D transformPersp = new Transform3D ( ) ;
        transformPersp.lookAt(new Point3d(10, 10, 10), new Point3d (0, 0, 0),
        new Vector3d (0, 1, 0)) ;
        transformPersp.invert();
        TransformGroup tgPersp = new TransformGroup(transformPersp);
        ViewingPlatform vpPersp = new ViewingPlatform();
        //**********************************
        vpPersp.getViewPlatform().setActivationRadius(100f);
        OrbitBehavior orbit = new OrbitBehavior (aCanvas, OrbitBehavior.REVERSE_ALL);
        orbit.setSchedulingBounds(new BoundingSphere (new Point3d (0.0f, 0.0f , 0.0f) , 100.0f));
        orbit.setZoomFactor(5.0f);
        vpPersp.setViewPlatformBehavior(orbit);
        //**********************************
        
        tgPersp.addChild(vpPersp);
        
        
        View viewPersp = new View();
        viewPersp.setPhysicalBody(new PhysicalBody ( ) ) ;
        viewPersp.setPhysicalEnvironment(new PhysicalEnvironment());
        viewPersp.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
        viewPersp.setFieldOfView(Math.toRadians(45));
        viewPersp.setFrontClipDistance(0.1);
        viewPersp.setBackClipDistance(35);
        
        
        viewPersp.attachViewPlatform(vpPersp.getViewPlatform());
        
        
        tgPersp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tgPersp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        
        views.add(viewPersp);
        
        return tgPersp;
        
    }
    
    public TransformGroup getViewMoon(){
        
        return null;
    }
    
    public TransformGroup getViewCamera(){
        
        return null;
    }
    
    
}
