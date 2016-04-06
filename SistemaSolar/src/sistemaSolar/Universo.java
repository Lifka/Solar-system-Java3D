/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.image.TextureLoader;
import java.util.ArrayList;
import javax.media.j3d.Texture;

public class Universo {
    private Nave nave;
    private ArrayList<Astro> astros;
    private Texture textura;
    
    public Universo (String archivo_text){
        this.textura = new TextureLoader(archivo_text, null).getTexture();
        this.nave = null;
        this.astros = null;
    }
    
    public int getNumComponentes(){
        int total = astros.size();
        if (nave != null)
            total++;
        return total;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public ArrayList<Astro> getAstros() {
        return astros;
    }

    public void setAstros(ArrayList<Astro> astros) {
        this.astros = astros;
    }
    
    public void addAstro(Astro ast){
        astros.add(ast);
    }
    
    public int numAstros(){
        return astros.size();
    }
}
