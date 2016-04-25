/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.j3d.Canvas3D;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

public class Gui extends JFrame{
    static final int PLANTA = 0;
    static final int VISTAS = 1;
    
    Gui(Canvas3D canvas, String title, int mode, Camaras camaras){
        
        setTitle(title);
        setLayout(new BorderLayout());
        setSize(800, 600);
        JPanel main_panel = new JPanel(new BorderLayout());
         
         
        if (mode == PLANTA){
            camaras.getView(0).addCanvas3D(canvas);
            main_panel.add(BorderLayout.NORTH, new Label("Vista planta fija:"));
            main_panel.add(BorderLayout.CENTER, canvas);
        } else if(mode == VISTAS){
            JRadioButton nave = new JRadioButton("Nave");
            JRadioButton luna = new JRadioButton("Luna");
            JRadioButton perspectiva = new JRadioButton("Perspectiva", true);
            camaras.getView(1).addCanvas3D(canvas);
            
            
            nave.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.getView().removeCanvas3D(canvas);
                    camaras.getView(3).addCanvas3D(canvas);
                }
            });
            
            luna.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.getView().removeCanvas3D(canvas);
                    camaras.getView(2).addCanvas3D(canvas);
                }
            });
            
            perspectiva.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.getView().removeCanvas3D(canvas);
                    camaras.getView(1).addCanvas3D(canvas);
                }
            });
            
            
            
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
            
            main_panel.add(BorderLayout.CENTER, canvas);
        }
         
        add(main_panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
         
    }
    
}
