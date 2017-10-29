import java.io.File;
/*   
Author: Chan In Kou
Purpose: The Display will show a table of powers of 2. 
*/
public class PPMImageSubclass extends PPMImage {

	protected File fileSelected = null;

	public PPMImageSubclass(File arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public void hideMessage(String message) {
		// getting the data
		char[] bits = getPixelData();
		
		// add 00000000 at the end of the message
		message += "\0";
		
		// keeping track
		int index = 0;
		
		// finding the last number
		char mask = 1 << 0;
		
		// find the last number and turning it off
		char maskoff = (char)~(1 << 0);
		int x;
		for (int i = 0; i < message.length(); i++) {
			// getting each character
			x = message.charAt(i);
			for(int j = 8; j >  0; j--){
				// first check if the last number is not equal to 0, then make it to 1
				if((x & (1 << j - 1)) != 0){
					bits[index] = (char)(bits[index] | mask);
				}
				// first check if the last number is equal to 0, then make it to 0
				else if((x & (1 << j - 1)) == 0){
					bits[index] = (char)(bits[index] & maskoff);
				}
				// keeping track
				index++;
			}
		}
		// making the image
		writeImage("newImage.ppm");	
	}

	// reveal the hidding message
	public String recoverMessage() {
		// getting the last digit 
		char lastNumber = 1 << 0;
		
		// making a char[] array
		char[] answer = getPixelData();
		
		// creating a string for the message
		String word = "";
		
		//creat a string to turn it store binary
		String binaryString = "";
		
		// keeping in track
		int index = 0;
		
		// use this to stop the while loop
		boolean finish = false;
		
		while(!finish){
			// getting all 8 
			for (int j = 0; j < 8; j++) {
				// adding the character to the binaryString
				binaryString += (answer[index] & lastNumber);
				index++;
			}
			// stop the while loop when it finds 00000000
			if((char)Integer.parseInt(binaryString, 2) == '\0'){
				finish = true;
				// returning the string
				return word;
			}
			else{
				// add the string to the word
				word += (char)Integer.parseInt(binaryString, 2);
				// set the binarystring back to nothing
				binaryString = "";
			}
		}
		return word;
	}

	// To convert an image to grayscale coloring use the following formula.
	// NOTE: R, G, B stands for the original red, green, and blue values
	// respectively.
	public void grayscale() {
		char[] pixel = getPixelData();
		// name all the result
		char redResult;
		char greenResult;
		char blueResult;
		for (int i = 0; i < getPixelData().length - 2; i++) {
			int R = pixel[i];
			int G = pixel[i + 1];
			int B = pixel[i + 2];
			// using the formula to change the color
			redResult = (char) ((R * .299) + (G * .587) + (B * .114));
			greenResult = (char) ((R * .299) + (G * .587) + (B * .114));
			blueResult = (char) ((R * .299) + (G * .587) + (B * .114));
			pixel[i] = redResult;
			pixel[i + 1] = greenResult;
			pixel[i + 2] = blueResult;
		}
		writeImage("grayScaleFile.ppm");
	}

	// To convert an image to sepia tone coloring use the following formula.
	// NOTE: R, G, B stands for the original red, green, and blue values
	// respectively.
	public void sepia() {
		char[] pixel = getPixelData();
		char redResult;
		char greenResult;
		char blueResult;
		for (int i = 0; i < getPixelData().length - 2; i++) {
			int R = pixel[i];
			int G = pixel[i + 1];
			int B = pixel[i + 2];
			redResult = (char) ((R * 0.393) + (G * 0.769) + (B * 0.189));
			greenResult = (char) ((R * 0.349) + (G * 0.686) + (B * 0.168));
			blueResult = (char) ((R * 0.272) + (G * 0.534) + (B * 0.131));
			if (redResult > 255) {
				redResult = 255;
			}
			if (greenResult > 255) {
				greenResult = 255;
			}
			if (blueResult > 255) {
				blueResult = 255;
			}
			pixel[i] = redResult;
			pixel[i + 1] = greenResult;
			pixel[i + 2] = blueResult;
			i += 2;
		}
		writeImage("sepiaFile.ppm");
	}

	// To convert an image to its negative coloring, use the following formula.
	// NOTE: R, G, B stands for the original red, green, and blue values
	// respectively.
	public void negative() {
		char[] pixel = getPixelData();
		char redResult;
		char greenResult;
		char blueResult;
		for (int i = 0; i < getPixelData().length - 2; i++) {
			int R = pixel[i];
			int G = pixel[i + 1];
			int B = pixel[i + 2];
			redResult = (char) (255 - R);
			greenResult = (char) (255 - G);
			blueResult = (char) (255 - B);
			pixel[i] = redResult;
			pixel[i + 1] = greenResult;
			pixel[i + 2] = blueResult;
		}
		writeImage("negativeFile.ppm");

	}
}			