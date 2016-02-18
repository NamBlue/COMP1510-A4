package q2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <p>Draws an equilateral triangle using a rubber banding technique. Size 
 * and orientation is determined by mouse drag. Original mouse press 
 * location is the center and the dragged position as one of its corners.</p>
 * 
 * @author Jia Qi Lee
 * @version 1.0
 */
public class DrawTriangle2 extends JFrame {
    
    /** Panel height. */
    private static final int HEIGHT = 800;
    /** Panel width. */
    private static final int WIDTH = 1000;
    /** Center point for triangle. */
    private Point center;
    /** Corner point for triangle on mouse drag. */
    private Point point1;
    
    /**
     * Constructs the frame and adds the panel.
     */
    public DrawTriangle2() {
        super("Jia Qi Lee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new DrawTrianglePanel());
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    /**
     * Represents the drawing panel for the DrawTriangle program.
     */
    private final class DrawTrianglePanel extends JPanel 
        implements MouseListener, MouseMotionListener {

        /**
         * Constructor to add Mouse Listeners.
         */
        private DrawTrianglePanel() {
            addMouseListener(this);
            addMouseMotionListener(this);
            setBackground(Color.black);
        }

        /**
         * Draws the Triangle.
         * 
         * @param g The graphics component to draw on
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);                                    
            g.setColor(Color.blue);
            final int two = 2;
            final int three = 3;
            final int four = 4;

            //Calculates the other 2 points of the triangle
            if (center != null && point1 != null) {
                int x2 = (int) (Math.cos(two * Math.PI / three) 
                        * (point1.x - center.x) 
                        - Math.sin(two * Math.PI / three) 
                        * (point1.y - center.y));
                int y2 = (int) (Math.sin(two * Math.PI / three) 
                        * (point1.x - center.x) 
                        + Math.cos(two * Math.PI / three) 
                        * (point1.y - center.y));
                int x3 = (int) (Math.cos(four * Math.PI / three) 
                        * (point1.x - center.x) 
                        - Math.sin(four * Math.PI / three) 
                        * (point1.y - center.y));
                int y3 = (int) (Math.sin(four * Math.PI / three) 
                        * (point1.x - center.x) 
                        + Math.cos(four * Math.PI / three) 
                        * (point1.y - center.y));
                Point point2 = new Point(x2 + center.x, y2 + center.y);
                Point point3 = new Point(x3 + center.x, y3 + center.y);
                g.drawLine(point1.x, point1.y, point2.x, point2.y);
                g.drawLine(point2.x, point2.y, point3.x, point3.y);
                g.drawLine(point3.x, point3.y, point1.x, point1.y);
                System.out.println("Paint called");
            }
        }
        
        /** 
         * Sets the center point for the triangle on mouse 
         * point when pressed.
         * 
         * @param e Contains position where mouse is pressed
         */
        public void mousePressed(MouseEvent e) {
            center = e.getPoint();
            System.out.println("Mouse pressed called");
        }
        
        /** 
         * Sets the point for one the triangles corners to be drawn.
         * 
         * @param e Contains position where mouse is dragged
         */
        public void mouseDragged(MouseEvent e) {
            point1 = e.getPoint();
            repaint();
            System.out.println("Mouse dragged called");
        }
        
        /** {@inheritDoc} */
        public void mouseReleased(MouseEvent e) {
        }

        /** {@inheritDoc} */
        public void mouseEntered(MouseEvent e) {
        }

        /** {@inheritDoc} */
        public void mouseExited(MouseEvent e) {
        }
        
        /** {@inheritDoc} */
        public void mouseClicked(MouseEvent e) {
        }
        
        /** {@inheritDoc} */
        public void mouseMoved(MouseEvent e) {
        }
    }

    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new DrawTriangle2();
    }
}
