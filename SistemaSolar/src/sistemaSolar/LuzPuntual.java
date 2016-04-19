/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PointLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

/**
 *
 * @author jgallardo
 */
public class LuzPuntual extends BranchGroup{
    public LuzPuntual(Color3f c){
        PointLight luz_puntual = new PointLight();
        luz_puntual.setColor(c);
        luz_puntual.setPosition(0.0f,0.0f,0.0f);
        luz_puntual.setInfluencingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),8000.0));
        this.addChild(luz_puntual);
    }
}
