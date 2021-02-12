/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanipapplication;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author merna
 */
public class FaceDetector {
    private String outImg;  // mainpulated image
    private String input;   // input image
    private CascadeClassifier faceDetector; // the xml file with the pretrain face detection model
    
    /**
     * this a constructor for the face detection
     * @param outImg
     * @param input
     * @param faceDetector 
     */
    FaceDetector(String input, String outImg, CascadeClassifier faceDetector){
        this.outImg = outImg;
        this.input = input;
        this.faceDetector = faceDetector;   
    }
    /**
     * detects a face and draws a rectangle over it using opencv classes
     */  
    public void detectAndDrawFace() {
        Mat src = Imgcodecs.imread(input);  // raed file
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(src, faceDetections); // detects faces
        
        //draws rectangles around it
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x
                    + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 3);
        }
        
        //writes into the manipulated image file
        Imgcodecs.imwrite(outImg, src);
    }

}
