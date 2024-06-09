package Code.HandDetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.MatOfRect;

public class PalmDetection {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoCapture cap = new VideoCapture(0);

        if (!cap.isOpened()) {
            System.out.println("Could not open camera stream!");
            return;
        }

        CascadeClassifier openPalmCascade = new CascadeClassifier("D:\\Java\\OOP_Project\\src\\Code\\HandDetection\\open_palm.xml");
        CascadeClassifier closedPalmCascade = new CascadeClassifier("D:\\Java\\OOP_Project\\src\\Code\\HandDetection\\closed_palm.xml");

        if (openPalmCascade.empty()) {
            System.out.println("Could not load open_palm configuration file! Check directory!");
            System.out.println("Press Q to Quit!");
            return;
        }

        if (closedPalmCascade.empty()) {
            System.out.println("Could not load closed_palm configuration file! Check directory!");
            System.out.println("Press Q to Quit!");
            return;
        }

        MatOfRect openPalms = new MatOfRect();
        MatOfRect closedPalms = new MatOfRect();

        while (true) {
            Mat frame = new Mat();
            cap.read(frame);
            if (frame.empty()) {
                System.out.println("Video over!");
                break;
            }
            Core.flip(frame, frame, 1);
            openPalmCascade.detectMultiScale(frame, openPalms, 1.3, 4, 0, new Size(50, 50), new Size());
            closedPalmCascade.detectMultiScale(frame, closedPalms, 1.3, 4, 0, new Size(50, 50), new Size());

            for (Rect closedPalm : closedPalms.toArray()) {
                System.out.println("=============Detected a closed_palm!=============");
                Point closedPalmRectP1 = new Point(closedPalm.x, closedPalm.y);
                Point closedPalmRectP2 = new Point(closedPalm.x + closedPalm.width, closedPalm.y + closedPalm.height);

                Imgproc.rectangle(frame, closedPalmRectP1, closedPalmRectP2, new Scalar(0, 255, 0));
                Imgproc.putText(frame, "Closed Palm", closedPalmRectP1, Imgproc.FONT_HERSHEY_SIMPLEX,
                        1, new Scalar(0, 255, 0), 1, Imgproc.LINE_AA, false);
            }

            for (Rect openPalm : openPalms.toArray()) {
                System.out.println("=============Detected an open_palm!=============");
                Point openPalmRectP1 = new Point(openPalm.x, openPalm.y);
                Point openPalmRectP2 = new Point(openPalm.x + openPalm.width, openPalm.y + openPalm.height);

                Imgproc.rectangle(frame, openPalmRectP1, openPalmRectP2, new Scalar(255, 0, 0));
                Imgproc.putText(frame, "Open Palm", openPalmRectP1, Imgproc.FONT_HERSHEY_SIMPLEX,
                        1, new Scalar(255, 0, 0), 1, Imgproc.LINE_AA, false);
            }

            HighGui.imshow("Video Capture", frame);

            if (HighGui.waitKey(1) == 'q') {
                break;
            }
        }

        cap.release();
        HighGui.destroyAllWindows();
    }
}
