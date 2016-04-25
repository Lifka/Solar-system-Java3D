/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import javax.vecmath.Color3f;

public class Gui extends JFrame{
    private static final int PLANTA = 0;
    private static final int VISTAS = 1;
    
    Gui(Canvas3D canvas, String title, int mode){
        
        setTitle(title);
        setLayout(new BorderLayout());
        setSize(800, 600);
        JPanel main_panel = new JPanel(new BorderLayout());
         
         
        if (mode == PLANTA){
            main_panel.add("North", new Label("Vista planta fija:"));
            main_panel.add("Center", canvas);
        } else if(mode == VISTAS){
            JRadioButton nave = new JRadioButton("Nave");
            JRadioButton luna = new JRadioButton("Luna");
            JRadioButton perspectiva = new JRadioButton("Perspectiva", true);
            ButtonGroup botones = new ButtonGroup();
            botones.add(nave);
            botones.add(luna);
            botones.add(perspectiva);
            
            JPanel botones_panel = new JPanel(new BorderLayout());
            
              
            botones_panel.setLayout(new GridBagLayout());
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(1, 1, 1, 1);
            
            
            botones_panel.add(new Label("Seleccione la cámara:"), gbc);
            gbc.gridx++;
            botones_panel.add(perspectiva, gbc);
            gbc.gridx++;
            botones_panel.add(nave, gbc);    
            gbc.gridx++;
            botones_panel.add(luna, gbc);
            
            main_panel.add(BorderLayout.NORTH, botones_panel);
            
            main_panel.add("Center", canvas);
        }
         
        add(main_panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
         
    }

    
    public static void main(String[] args) {
        
        // *************** CANVAS
        // Crear canvas (pantalla)
        Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        Canvas3D canvas2 = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        
             
        // *************** UNIVERSE
        // Crear Simple Universe
        Universo universe = new Universo(new String(), canvas);
        // Sale de nuestra propia clase
        SimpleUniverse simpleUniverse = universe.createUniverso();
        
        // Crear los astros
        universe.crearSistemaSolar();
        HashMap<String,Astro> astros = universe.getAstros();
        ArrayList<Astro> astros_array = universe.getAstrosArray();
        
        // Hacemos visible la ventana
       // visualizationWindows.setVisible(true);
        
        // Creamos el árbol
        BranchGroup raiz = new BranchGroup();
        
        // Fondo
        BranchGroup background = universe.createBackground();
        
        // *************** SOL
        Astro sol = astros.get("sol");
        sol.makeTransform(canvas);

        // Transformaciones del objeto (van en un transform group)
        Transform3D t3d = new Transform3D();
        // grupoArotar --> conjunto de elementos que se rotan juntos
        TransformGroup grupoArotar = new TransformGroup(t3d);
        // Capabilities
        grupoArotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        grupoArotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        
        BoundingSphere bounds = new BoundingSphere();
        
        // Añadimos la luz puntual al sol
        LuzPuntual lp = new LuzPuntual(new Color3f(Color.WHITE));
        grupoArotar.addChild(lp);
        
        // Añadimos en el mismo nodo el objeto y el comportamiento
        grupoArotar.addChild(sol);
        
        // Lo añadimos a la raiz
        raiz.addChild(grupoArotar);
        
        // *************** PLANETAS
        for(int i = 0; i < astros_array.size(); i++){
            Astro astro = astros_array.get(i);
            if (astro instanceof Planeta){
                // TRANSFORMS
                astro.makeTransform(canvas);
                
                // AÑADIR ASTRO A LA RAIZ
                raiz.addChild(astro);
            }
        }
        
        ((Planeta)astros.get("tierra")).cloudEnabled(true, "src/texturas_planetas/cloud.png");
        
        
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();

       raiz.addChild(background);
       raiz.addChild(new Nave().getNaveBranch());
       raiz.compile();
       simpleUniverse.addBranchGraph(raiz);
       
       Gui windows1 = new Gui(canvas, "Planta fija", PLANTA);
       Gui windows2 = new Gui(canvas2, "Vistas", VISTAS);
    }
    
}
