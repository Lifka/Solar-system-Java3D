/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.PointLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

/**
 *
 * @author jgallardo
 */
public class LuzAmbiental extends BranchGroup{
    public LuzAmbiental(Color3f c){
        AmbientLight luz_ambiental = new AmbientLight();
        luz_ambiental.setColor(c);
        luz_ambiental.setInfluencingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),800.0));
        this.addChild(luz_ambiental);
    }
}
