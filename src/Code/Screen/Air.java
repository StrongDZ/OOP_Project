package Code.Screen;

import Code.Object.Objectss;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Air extends JPanel {
    private ArrayList<MovingObject> objects;
    private Timer timerCreate, timerRun;
    private final int numCopies; // Number of copies to create
    private final Random random; // Random number generator for placement
    private float overallSpeed; // Overall speed for direction
    private MainScreen screen;

    public Air(MainScreen screen ,int numCopies, int overallSpeed) {
        this.screen = screen;
        this.objects = new ArrayList<>();
        this.timerCreate = new Timer();
        this.timerRun = new Timer();
        this.numCopies = numCopies;
        this.random = new Random(); // Initialize random number generator
        this.overallSpeed = overallSpeed;

        setOpaque(false);
        setSize(1920, 800);
        stopTimers();
        setVisible(false);
    }

    private void createImageCopies() throws IOException {
        // Load image from URL (replace with your image URL)
        URL imageURL = new URL("file:D:\\Java\\OOP_Project\\src\\Code\\Utils\\Air.png");
        BufferedImage image = ImageIO.read(imageURL);

        // Get image dimensions
        int width = image.getWidth();
        int height = image.getHeight();
        float objectspeed = Math.max(Math.abs(overallSpeed) / 10, 5);
        objectspeed = Math.min(objectspeed, 30);

        for (int i = 0; i < numCopies; i++) {
            int x = (overallSpeed > 0) ? 1600 : 0;
            int y = random.nextInt(getHeight() - height);

            objects.add(new MovingImageObject(image, x, y, width, height, (overallSpeed > 0) ? objectspeed : -objectspeed));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (MovingObject object : objects) {
            object.draw(g);
        }
    }

    public void startTimers() {
        timerCreate.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Create new copies on each timer tick
                try {
                    createImageCopies();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
            }
        }, 0, 200); // 200ms interval for creating images

        timerRun.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Iterator<MovingObject> it = objects.iterator(); it.hasNext(); ) {
                    MovingObject object = it.next();
                    object.move();
                    if ((overallSpeed > 0 && object.getX() < 0) || (overallSpeed < 0 && object.getX() > 1600)) {
                        it.remove(); // Remove object if x < 0
                    }
                }
                Objectss mainChar = screen.mainCharacter.mainCharacter;
                if(mainChar != null){
                    setSpeed(mainChar.getSpeed());
                    mainChar.setAirfric(-(float)0.01* mainChar.getSpeed()* mainChar.getSpeed());
                }

                repaint();
            }
        }, 0, 16); // 16ms = 60fps
    }

    public void stopTimers() {
        timerCreate.cancel();
        timerRun.cancel();
        Objectss mainChar = screen.mainCharacter.mainCharacter;
        if(mainChar != null){
            mainChar.setAirfric(0);
        }
        timerCreate = new Timer();
        timerRun = new Timer();
    }

    public void setSpeed(float newSpeed) {
        this.overallSpeed = newSpeed;
        // Adjust speed for existing objects
        float objectspeed = Math.max(Math.abs(overallSpeed) / 10, 5);
        objectspeed = Math.min(objectspeed, 30);
        for (MovingObject object : objects) {
            object.setSpeed((overallSpeed > 0) ? objectspeed : -objectspeed);
        }
    }
}

class MovingObject {
    protected int x, y;
    protected int width, height;
    protected float speed;

    public MovingObject(int x, int y, int width, int height, float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void move() {
        x -= speed;
    }

    public void draw(Graphics g) {
        // Implement drawing logic for non-image objects (optional)
        g.fillRect(x, y, width, height); // Default for non-image objects
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public void setSpeed(float newSpeed) {
        this.speed = newSpeed;
    }
}

class MovingImageObject extends MovingObject {
    private final BufferedImage image;

    public MovingImageObject(BufferedImage image, int x, int y, int width, int height, float speed) {
        super(x, y, width, height, speed);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
