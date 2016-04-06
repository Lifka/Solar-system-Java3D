/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;

public class Universo {
    private Nave nave;
    private HashMap<String,Astro> astros;
    private Texture textura;
    private Canvas3D aCanvas;
    

    public Universo (String archivo_text,Canvas3D aCanvas){
        this.textura = new TextureLoader(archivo_text, null).getTexture();
        this.nave = null;
        this.astros = new HashMap<String,Astro>();
        this.aCanvas = aCanvas;
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

    public HashMap<String,Astro> getAstros() {
        return astros;
    }

    public void setAstros(HashMap<String,Astro> astros) {
        this.astros = astros;
    }
    
    public void addAstro(Astro ast){
        astros.put(ast.getNombre(),ast);
    }
    
    public int numAstros(){
        return astros.size();
    }
    
    public void crearSistemaSolar(){
        // ESTRELLAS --> SOL
        String dir_text_sol = "texturas_estrellas/";
        Astro sol = new Estrella("Sol", 696342, 0.0, dir_text_sol + "sol.jpg", new Material(), Color.white, 26, 10);
        astros.put(sol.getNombre(),sol);
        
        // SATÉLITES
        String dir_text_sat = "texturas_satelites/";
        
        Astro luna = new Satelite("Luna", 1737, 370300, dir_text_sat + "luna.jpg", new Material(),Color.white, 27.32, 27.32);
        astros.put(luna.getNombre(), luna);
        
        Astro fobos = new Satelite("Fobos", 11, 9380, dir_text_sat + "fobos.jpg", new Material(), Color.white, 0.3 , 0.3);
        astros.put(fobos.getNombre(), fobos);
        
        Astro deimos = new Satelite("Deimos",7,23460, dir_text_sat + "deimos.jpg", new Material(), Color.white, 1.25,1.25); 
        astros.put(deimos.getNombre(), deimos);
        
        Astro io = new Satelite("Io",1820,422000, dir_text_sat + "io.jpg", new Material(), Color.white, 1,1);
        
    }

    
    
    
    public void createUniverso(){

        ViewingPlatform viewnzgPlatform = new ViewingPlatform ( ) ;

      

       // Branch de planetas
        BranchGroup planetas = new BranchGroup();
        
        // Enganchar los planetas al universo
        universe.addBranchGraph(planetas);
        
        /*************/
        /* PLANETAS */
        /***********/
        
        /* Mercurio */
        
        // Mercurio es transformable
        TransformGroup conjuntoMercurio = new TransformGroup ();
        conjuntoMercurio.setCapability(TransformGroup .ALLOW_TRANSFORM_WRITE);
        
        // Enganchar conjuntoMercurio del branch de planetas
        planetas.addChild(conjuntoMercurio);
        //conjuntoMercurio.setTransform(Transformacion);
        
        /* Venus */
        
        // Venus es transformable
        TransformGroup conjuntoVenus = new TransformGroup ();
        conjuntoMercurio.setCapability(TransformGroup .ALLOW_TRANSFORM_WRITE);
        
        // Enganchar conjuntoVenus del branch de planetas
        planetas.addChild(conjuntoVenus);
        //conjuntoVenus.setTransform(Transformacion);
        
        /* Tierra */
        
        // Tierra es transformable
        TransformGroup conjuntoTierra = new TransformGroup ();
        conjuntoMercurio.setCapability(TransformGroup .ALLOW_TRANSFORM_WRITE);
        
        // Enganchar conjuntoTierra del branch de planetas
        planetas.addChild(conjuntoTierra);
        //conjuntoTierra.setTransform(Transformacion);
        
    }
}