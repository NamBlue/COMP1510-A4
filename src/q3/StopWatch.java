package q3;

import java.text.DecimalFormat;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Timer;

import images.ResourceLoader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * <p>A stop-watch with a display that shows the time in minutes, seconds and 
 * tenths of a second as it increments, includes buttons that allow the user to
 * start and stop the time and reset the display to zero.</p>
 *
 * @author Jia Qi Lee
 * @version 1.0
 */
public class StopWatch extends JFrame {
    
    /** Panel width. */
    private static final int WIDTH = 805;
    /** Panel height. */
    private static final int HEIGHT = 516;
    /** Timer for stop-watch. */
    private Timer timer;
    /** Amount of real time it takes to increment the timer. */
    private final int tDelay = 100;
    /** stores the number of minutes. */
    private int minutes;
    /** stores the number of seconds. */
    private int seconds;
    /** stores the number of tenths of the second. */
    private int tenthSeconds;
    /** Button to start or stop the stop-watch. */
    private JButton start;
    /** Button to reset the stop-watch. */
    private JButton reset;
    /** Background image for the stop-watch. */
    private Image bg = ResourceLoader.getImage("bg.jpg");
    /** Purple color for styling components. */
    private final Color purple = new Color(210, 157, 199);
    
    /**
     * Constructs the frame and adds the panel and buttons.
     */
    public StopWatch() {
        super("Jia Qi Lee's Stop-watch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new StopWatchPanel());
        setLayout(new BorderLayout());
        setResizable(false);
        
        JPanel bottomPanel = new JPanel();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(start);
        bottomPanel.add(reset);
        bottomPanel.setOpaque(false);
        
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
    
    /**
     * Represents the panel for the stop-watch display.
     */
    private final class StopWatchPanel extends JPanel {
        
        /**
         * Constructs the panel and adds Listeners to timer and buttons.
         */
        private StopWatchPanel() {
            
            timer = new Timer(tDelay, new TimeListener());
            
            start = new JButton("Start/Stop");
            start.addActionListener(new ButtonListener());
            start.setFocusPainted(false);
            start.setBackground(purple);
            start.setForeground(Color.white);
            reset = new JButton("   Reset   ");
            reset.addActionListener(new ButtonListener());
            reset.setFocusPainted(false);
            reset.setBackground(purple);
            reset.setForeground(Color.white);
        }
        
        /**
         * Draws the stop-watch digit display.
         * 
         * @param g The graphics component to draw on
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            final int seventy = 70;
            final int textX = 105;
            final int textY = 285;
            
            DecimalFormat fmt = new DecimalFormat("00");
            
            g.drawImage(bg, 0, 0, this);
            
            g.setColor(purple);
            g.setFont(new Font("TimesRoman", Font.PLAIN, seventy));
            g.drawString(fmt.format(minutes) + ":" + fmt.format(seconds) 
                + ":" + tenthSeconds, textX, textY);
        }
        
        /**
         * Listener for the timer thats increments the timer whenever the
         * timer fires an action event.
         */
        private class TimeListener implements ActionListener {
            
            /**
             * Increments the timer whenever the
             * event is received.
             * @param e increments the time when event is passed
             */
            public void actionPerformed(ActionEvent e) {
                final int tenth = 10;
                final int sixty = 60;
                tenthSeconds++;
                if (tenthSeconds == tenth) {
                    seconds++;
                    tenthSeconds = 0;
                }
                if (seconds == sixty) {
                    minutes++;
                    seconds = 0;
                }
                repaint();
            }
        }
        
        /**
         * Listener for the buttons thats starts and stops the timer and resets
         * the timer count to 0 when a button is pressed.
         */
        private class ButtonListener implements ActionListener {
            
            /**
             * Starts and stops the timer and resets
             * the timer count to 0 when the event is received.
             * @param e event that is passed and determines which button 
             * is pressed and fires that action
             */
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == start) {
                    if (timer.isRunning()) {
                    timer.stop();
                    } else {
                        timer.start();
                        }
                } else if (e.getSource() == reset) {
                    tenthSeconds = 0;
                    seconds = 0;
                    minutes = 0;
                    repaint();
                }
            }
        }   
    }      
    
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new StopWatch();
        System.out.println("Question three was called and ran sucessfully.");
    }
}