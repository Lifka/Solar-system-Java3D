/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

public class Punto {
    float x;
    float y;
    float z;
    
    public Punto(){
        x = 0;
        y = 0;
        z = 0;
    }
    
    public Punto(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void relocate(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }
    
    
}
