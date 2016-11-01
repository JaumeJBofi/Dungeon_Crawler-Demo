/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

// Graphics imports begin
import Foundation.DIRECTIONS;
import Foundation.Options;
import com.sun.media.sound.AudioFileSoundbankReader;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
// Graphics imports end

/**
 *
 * @author Jauma
 */
public abstract class Stage implements Runnable, KeyListener,
        MouseListener, MouseMotionListener,
        MouseWheelListener, FocusListener,
        ComponentListener, WindowStateListener,
        WindowFocusListener, WindowListener {

    protected static int WIDTH = 1366;
    protected static int HEIGHT = 680;

    protected Thread _animator;
    protected Thread _movements;

    // Stop the animator
    protected volatile boolean running = false;
    protected long initTime;

    // Game Termination. The animation does not stop
    protected volatile boolean gameOver = false;

    // For game pause. Animation does not stop
    protected volatile boolean pause = false;

    protected BufferStrategy bs;

    /* Number of frames with a delay of 0 ms before the
     * animation thread yields to other running threads. */
    protected static int NO_SLEEPS_FOR_YIELD = 15;

    /* frames that can be skipped in any one animation loop
     * i.e the games state is updated but not rendered (UPS)*/
    protected static int MAX_FRAME_SKIPS = 8;

    // Util for calculate the period
    protected static int FPS;

    /* Period that indicate the FPS. 1000(1seg en miliS)/Period = FPS
     * This must to be represented in nanoS */
    protected static long period;

    /*Performance Code. Can be added later*/
    // Loaders... Custom lightweight for later.
    public enum SCREENMODE {

        FSEM, AFS, UFS, JFRAME, CANVAS, JPANEL;
    }

    protected JFrame _window;   // For FSEM, AFS,UFS,JFrame Modes
    protected Canvas _canvas;    // Paint in canvas Class
    protected JFrame _panel;    // For JPanel Mode

    protected SCREENMODE _mode;
    // Specifi component of the mode
    protected Component _component;

    // For Full-Screen Exclusive mode
    protected GraphicsEnvironment ge;
    protected GraphicsDevice screenDevice;
    protected DisplayMode defaultDisplay;

    public Stage(SCREENMODE mode) {
        initTime = System.currentTimeMillis();
        SetFPS(80);
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        screenDevice = ge.getDefaultScreenDevice();
        switchMode(mode);
    }

    public void switchMode(SCREENMODE mode) {
        _mode = mode;

        switch (mode) {
            case FSEM:
                initFSEM();
                break;
            case AFS:
                initAFS();
                break;
            case UFS:
                initUFS();
                break;
            case JFRAME:
                initJFrame();
                break;
            case CANVAS:
                initCanvas();
                break;
            case JPANEL:
                initJPanel();
                break;
            default:
                throw new IllegalArgumentException(
                        "The mode of the Stage is invalid.");
        }

        // Add the listeners for capture the EVENTS -------
        // listen for component mouse presses
        _component.addMouseListener(this);
        // listen for component key events
        _component.setFocusable(true);
        _component.requestFocus();
        _component.addKeyListener(this);
        // listen for componente mouse wheel
        _component.addMouseWheelListener(this);
        // listen for component
        _component.addComponentListener(this);
        // listen for component focus changed
        _component.addFocusListener(this);
        // listen for component mouse motion
        _component.addMouseMotionListener(this);

        _window.addWindowListener(this);
        _window.addWindowStateListener(this);
// end of capture components EVENTS ---------------
    }

    public void initFSEM() {
    }                    // No implemented.

    public void initAFS() {
    }

    public void initUFS() {
    }

    public void initJPanel() {
    }

    public void initCanvas() {
        _canvas = new Canvas() {
            private static final long serialVersionUID = 1L;

            public void addNotify() {
                super.addNotify();
                _canvas.createBufferStrategy(2);
                bs = _canvas.getBufferStrategy();
            }
        };
        _canvas.setIgnoreRepaint(true);
        try { // Sleep to give time for the buffer strategy to be done.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        _component = _canvas;
    }

    public void initJFrame() {
        initCanvas();
        _window = new JFrame("The 7 layers");
        _window.addWindowFocusListener(this);
        _window.addWindowListener(this);
        _window.addWindowStateListener(this);
        _window.setIgnoreRepaint(true);
        _window.getContentPane().add(_canvas);
    }

    public void startGame() {
        if (_animator != null || !running) {
            _animator = new Thread(this);
            _animator.start();
        }
    }

    /*Initialize and start the thread with the loop of the game.*/
    public void stopGame() {
        running = false;
    }

    private boolean modificarDireccion(String input, Options choice) {
        //Revisa en que direccion debe ir
        if (input.toLowerCase().endsWith("derecha")) {
            choice.SetPath(DIRECTIONS.RIGHT);
        } else if (input.toLowerCase().endsWith("izquierda")) {
            choice.SetPath(DIRECTIONS.LEFT);
        } else if (input.toLowerCase().endsWith("abajo")) {
            choice.SetPath(DIRECTIONS.BOT);
        } else if (input.toLowerCase().endsWith("arriba")) {
            choice.SetPath(DIRECTIONS.TOP);
        } //En caso que no de ninguna direccion devuelve falso
        else {
            return false;
        }
        //Se llego a definir una direccion
        return true;
    }

    public static String[] alternativas = {"Mover izquierda", "Mover derecha", "Mover abajo",
        "Mover arriba", "Interactuar izquierda", "Interactuar derecha", "Interactuar abajo", "Interactuar arriba",
        "Usar", "Guardar", "Salir"};
    public JFrame frame = new JFrame("Input Dialog");
    public JFrame frame2 = new JFrame("Input number");

    public void interactionUserBox() {
        Options choice = new Options(Options.ACTION.NULA);

        String input = (String) JOptionPane.showInputDialog(frame,
                "Que deseas hacer?",
                "Acciones",
                JOptionPane.QUESTION_MESSAGE,
                null,
                alternativas,
                alternativas[0]);

        if (input == null) {
            choice.SetAction(Options.ACTION.NULA);

        } else if (input.equalsIgnoreCase("Salir")) {
            System.out.println("\n\nSesionFinalizada");
            Exit();
            return;
        } //Si la instruccion comienza com mover
        else if (input.toLowerCase().startsWith("mover")) {
            choice.SetAction(Options.ACTION.MOVE);
            if (!modificarDireccion(input, choice)) //Si no se modifico la direccion se entiende
            //que no se dio bien la instruccion
            {
                choice.SetAction(Options.ACTION.NULA);
            }
        } else if (input.toLowerCase().startsWith("interactuar")) {
            choice.SetAction(Options.ACTION.INTERACT);
            //No es necesario ver si en verdad la direccion que se
            //da es correcta
            modificarDireccion(input, choice);
        } //En caso que se desee debugear
        //        else if (input.compareToIgnoreCase("sudo debug") == 0) {
        //            choice.SetAction(Options.ACTION.DEBUG);
        //        } //En caso que use teleport
        //        else if (input.toLowerCase().startsWith("sudo teleport")) {
        //            choice.SetAction(Options.ACTION.TELEPORT);
        //        } //En caso que se desee equipar un objeto
        else if (input.toLowerCase().startsWith("usar")) {

            String numero = JOptionPane.showInputDialog(
                    frame2,
                    "Ingresa el numero del item a usar",
                    "Escoger item",
                    JOptionPane.WARNING_MESSAGE
            );
            int num;
            try {
                num = Integer.parseInt(numero);
                choice.indice_item = num;
                choice.SetAction(Options.ACTION.EQUIP);
            } catch (NumberFormatException except) {
                System.out.println("Indice no valido.");
                choice.SetAction(Options.ACTION.NULA);
                SetInteraccion(choice);
            }

        } else if (input.toLowerCase().startsWith("guardar")) {
            choice.SetAction(Options.ACTION.SAVE);
        } else {
            choice.SetAction(Options.ACTION.NULA);
        }

        SetInteraccion(choice);
    }

    @Override
    public void run() {
        if (bs == null) {
            throw new NullPointerException("Bufffer Strategy is Null");
        }
        long beforeTime = 0, afterTime, diff, sleepTime;
        long extraSleepTime = 0L, excessTime = 0L;
        int noSleeps = 0;
        InitStage();
        initTime = System.nanoTime();

        running = true;
        while (running) {
            beforeTime = System.nanoTime();

            int skips = 0;

            while (skips < MAX_FRAME_SKIPS && excessTime > period) {
                excessTime -= period;
                UpdateStage(); // Only update. not Render
                skips++;
            }
            // Loop Game!
            clearInteraccion();
            UpdateStage();
            UpdateScreen();
            interactionUserBox();
            if (!running) {
                System.exit(0);
            }

            afterTime = System.nanoTime();
            diff = afterTime - beforeTime;
            sleepTime = (period - diff) - extraSleepTime;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000L);
                } catch (InterruptedException e) {
                }
                extraSleepTime = System.nanoTime() - afterTime - sleepTime;
            } else {
                excessTime -= sleepTime; // store excess time value
                extraSleepTime = 0L;
                if (++noSleeps >= NO_SLEEPS_FOR_YIELD) {
                    // give another thread a chance to run
                    Thread.yield();
                    noSleeps = 0;
                }
            }

        }
        if (_mode == SCREENMODE.FSEM) // CloseFSem();
        {
            System.exit(0);
        }
    }

    public void UpdateScreen() {
        try {
            Graphics g = bs.getDrawGraphics();
            RenderStage(g);

            if (bs.contentsLost()) {
                System.out.println("Contents of the buffer lost");
            } else {
                bs.show();
            }
            g.dispose();

            Toolkit.getDefaultToolkit().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void clearInteraccion();

    public abstract void SetInteraccion(Options c);

    public abstract void UpdateStage();

    public abstract void RenderStage(Graphics g);

    public abstract void InitStage();

    public void GameOver() {
        gameOver = true;
    }

    public void Exit() {
        running = false;
    }

    public void SetWindowVisible(boolean v) {
        if (_window != null) {
            _window.setVisible(v);
        }
    }

    // Loaders
    public JFrame GetWindow() {
        return _window;
    }

    public long GetTimeRunning() {
        return System.nanoTime() - initTime;
    }

    public Component getComponent() {
        return _component;
    }

    public int GetFPS() {
        return FPS;
    }

    public int GetWidth() {
        return WIDTH;
    }

    public int GetHeight() {
        return HEIGHT;
    }

    // Boolean Methods
    public boolean IsPause() {
        return pause;
    }

    public boolean IsRunning() {
        return running;
    }

    public void SetFPS(int fps) {
        FPS = fps;
        period = 1000000000L / FPS;
    }

    public boolean IsGameOver() {
        return gameOver;
    }

    public void updateSize() {
        WIDTH = _component.getWidth();
        HEIGHT = _component.getHeight();
    }  // end of updateSize();

    public void setSize(int w, int h) {
        _component.setPreferredSize(new Dimension(
                w, h));
        if (_mode == SCREENMODE.JFRAME) {
            _window.pack();
        } else {
            updateSize();
        }
    }  // end of setSize(int, int);

    public boolean isDisplayModeAvailable(DisplayMode d) {
        DisplayMode[] dm = screenDevice.getDisplayModes();
        for (DisplayMode dm1 : dm) {
            if (dm1.getWidth() == d.getWidth() && dm1.getHeight() == d.getHeight()
                    && dm1.getBitDepth() == d.getBitDepth()
                    && dm1.getRefreshRate() == d.getRefreshRate()) {
                return true;
            }
        }
        return false;
    }  // end of isDisplayModeAv

    // Changes the DisplayModel
    public boolean setDisplayMode(DisplayMode dm) {
        if (_mode != SCREENMODE.FSEM) {
            return false;
        }
        if (!screenDevice.isDisplayChangeSupported()
                && isDisplayModeAvailable(dm)) {
            return false;
        }
        defaultDisplay = screenDevice.getDisplayMode();
        try {
            screenDevice.setDisplayMode(dm);
        } catch (Exception e) {
            System.err.println("Error setting DisplayMode.");
            e.printStackTrace();
            screenDevice.setDisplayMode(defaultDisplay);
        }
        return true;
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if ((keyCode == KeyEvent.VK_ESCAPE)
                || (keyCode == KeyEvent.VK_END)
                || ((keyCode == KeyEvent.VK_C) && e.isControlDown())) {
            Exit();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
    }

    public void focusGained(FocusEvent e) {
    }

    public void focusLost(FocusEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowGainedFocus(WindowEvent e) {
    }

    public void windowLostFocus(WindowEvent e) {
    }

    public void windowStateChanged(WindowEvent e) {
    }
}
