package Code.HandDetection;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class HandDetection implements Runnable {
    public static int x_out;

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public int getX_out() {
        return x_out;
    }

    private VideoCapture capture ;
    public volatile boolean running = true;

    public void run() {
        String handCascadePath = "src/Code/HandDetection/fist.xml";
        CascadeClassifier handCascade = new CascadeClassifier(handCascadePath);
        capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("Error: Failed to open camera.");
            return;
        }

        Mat frame = new Mat();
        HighGui.namedWindow("Hand Detection", HighGui.WINDOW_AUTOSIZE);

        while (running) {
            if (!capture.read(frame)) {
                System.out.println("Error: Failed to capture frame.");
                break;
            }

            // Get the center position of the window
            int windowHeight = frame.rows();
            int windowWidth = frame.cols();
            double centerX = windowWidth / 2.0;
            double centerY = windowHeight / 2.0;

            // Perform hand detection
            Mat gray = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);
            MatOfRect hands = new MatOfRect();
            handCascade.detectMultiScale(gray, hands, 1.1, 5, 0, new Size(30, 30), new Size());

            // Avoid too much hand on the camera
            Rect[] handsArray = hands.toArray();
            double max = 0;
            int x_new = (int)centerX;
            int y_new = 0;
            int w_x = 0;
            int h_y = 0;

            // Eliminating noise point (taking the farthest point to the center)
            for (Rect hand : handsArray) {
                double distance = (Math.abs(hand.x - centerX) * Math.abs(hand.x - centerX))
                        + (Math.abs(hand.y - centerY) * Math.abs(hand.y - centerY));
                if (distance > max) {
                    max = distance;
                    x_new = hand.x;
                    y_new = hand.y;
                    w_x = hand.width;
                    h_y = hand.height;
                }
            }

            x_out = x_new - (int) centerX + w_x / 2;
            System.out.println("Position: x = " + x_out);
            // Display the last point after eliminating noise point
            Imgproc.rectangle(frame, new Point(x_new, y_new), new Point(x_new + w_x, y_new + h_y), new Scalar(0, 255, 0), 2);
            Core.flip(frame, frame, 1);
            // Display the frame
            HighGui.imshow("Hand Detection", frame);

            // Exit the loop if 'q' is pressed
            int keyPressed = HighGui.waitKey(1);
            if (keyPressed == 'q' || keyPressed == 27) { // 27 is the ASCII code for the Esc key
                running = false;
            }
        }

        closeWindow();
    }

    public void closeWindow() {
        running = false;
        if (capture != null && capture.isOpened()) {
            capture.release();
        }
        HighGui.destroyAllWindows();
    }
}
