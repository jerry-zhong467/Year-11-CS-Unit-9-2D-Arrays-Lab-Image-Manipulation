package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage img = new APImage();
        img.draw();
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        for(Pixel p:img){
            int r=p.getRed();
            int g=p.getGreen();
            int b=p.getBlue();
            int average=(r+g+b)/3;
            p.setRed(average);
            p.setGreen(average);
            p.setBlue(average);
        }
        img.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int average=0;
        int r=pixel.getRed();
        int g=pixel.getGreen();
        int b=pixel.getBlue();
        average=(r+g+b)/3;
        return average;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        for(Pixel p:img){
            int average=getAverageColour(p);
            if(average<128){
                p.setRed(0);
                p.setGreen(0);
                p.setBlue(0);
            }
            else{
                p.setRed(255);
                p.setGreen(255);
                p.setBlue(255);
            }
        }
        img.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage img = new APImage(pathToFile);
        int w=img.getWidth();
        int h=img.getHeight();
        APImage copy = img.clone();
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                Pixel current=copy.getPixel(x,y);
                int currAver=getAverageColour(current);
                int leftAver=currAver;
                int beloAver=currAver;
                if(x>0) {
                    Pixel leftPi=copy.getPixel(x - 1,y);
                    leftAver=getAverageColour(leftPi);
                }
                if(y<h-1) {
                    Pixel botPix=copy.getPixel(x,y+1);
                    beloAver=getAverageColour(botPix);
                }
                if(Math.abs(currAver-leftAver)>threshold || Math.abs(currAver-beloAver)>threshold) {
                    current.setRed(0);
                    current.setGreen(0);
                    current.setBlue(0);
                }
                else {
                    current.setRed(255);
                    current.setGreen(255);
                    current.setBlue(255);
                }
            }
        }
        copy.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage img = new APImage(pathToFile);
        int w=img.getWidth();
        int h=img.getHeight();
        APImage reflected =img.clone();
        for(int y=0;y<h;y++) {
            for(int x=0;x<w;x++) {
                Pixel ogPix=img.getPixel(x, y);
                int reflectedX=w-1-x;
                Pixel refPix=reflected.getPixel(reflectedX, y);
                refPix.setRed(ogPix.getRed());
                refPix.setGreen(ogPix.getGreen());
                refPix.setBlue(ogPix.getBlue());
            }
        }
        reflected.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage img = new APImage(pathToFile);
        int w=img.getWidth();
        int h=img.getHeight();
        APImage rot =img.clone();
        for(int y=0;y<h;y++) {
            for(int x=0;x<w;x++) {
                Pixel ogPix=img.getPixel(x, y);
                int newX=y;
                int newY=w-x-1;
                rot.setPixel(newX,newY,ogPix);
            }
        }
        rot.draw();
    }

}