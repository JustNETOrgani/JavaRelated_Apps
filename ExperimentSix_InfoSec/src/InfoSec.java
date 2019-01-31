// This file can be used if the following conditions are met.
//  Path to the BMP image is set to "C:\\Images\\InfoSecPic.bmp" or be edited from Line 77.
//	This is an ongoing application.
//	It is to hide information in a picture. Help would be welcomed.

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;
import java.io.RandomAccessFile;
import java.io.BufferedReader;


public class InfoSec {

	// Global variable declarations begin.
	static int bmpWidth;	// 	Width of the BMP image
	static int bmpHeight;	// 	Height of the BMP image
	static int bpp;			// 	Bits per pixel.
	static double bmpSize;		// 	Size of the BMP image
	static int bmpbitCount;	//	Bit Count of the BMP image
	static int HEADERSIZE;
	
	static String userText;
	int lenText;
	static String binaryText;
	static String theExtractedText;
	BufferedImage image = null;
	File f = null;

	 FileInputStream   in = null;
     FileInputStream  msg = null;
     FileOutputStream out = null;

	// Global variable declarations end.
	
	// Interfaces begin.

	public interface BMPImage {
		void readImage();

		void processImage();

		void saveImage();
	}

	public interface InputText {
		void getText();

		void convertText();
		
		void extractText();
	}

	// Interfaces end.
	
	// Actual implementation of abstract methods begin here.
	
	public class BMP implements BMPImage {
		@Override
		public void readImage() {
			System.out.println("Inside read Image method");
			// Local variable declarations.
			
			int px, a, r, g, b;	// Pixel value and Alpha, Red, Green and Blue pixel values.
			int scanLineSize;
			ColorModel sz;
			
			// Local variable declarations end. 
			
			// read image from specified location or directory.
			try {
				f = new File("C:\\Images\\InfoSecPic.bmp"); // Path to the BMP Image.
				image 		= 	ImageIO.read(f);
				bmpWidth	= 	image.getWidth();
				bmpHeight 	= 	image.getHeight();				
				px			=	image.getRGB(0 , 0);
				a 			= 	(px >> 24) & 0xff;
				r 			= 	(px >> 16) & 0xff;
				g 			= 	(px >> 8) & 0xff;
				b			= 	px & 0xff;
				sz			=	image.getColorModel();
				bpp		=	sz.getPixelSize();
				
				//image = new BufferedImage(bmpWidth, bmpHeight, BufferedImage.TYPE_INT_ARGB);
			 
			
				// Scan line is padded with zeroes to be a multiple of four bytes.
				
                scanLineSize = ((bmpWidth * bpp + 31) / 32) * 4;

                bmpSize = scanLineSize * bmpHeight;
                //bmpSize = bmpWidth * bmpHeight *3 ;
                //bmpbitCount=  (int)bmpSize * 8;
				
				//bmpSize = (bmpHeight * ((bmpWidth * bpp / 32) * 32)) / 8 + 54;
				
				
				System.out.println("Reading of Image successfull.");
			} catch (IOException e) {
				System.out.println("Error: " + e);
			}

		}

		public void processImage() {
			System.out.println("Inside processImage method");
			
			 try {
			      RandomAccessFile raf = new RandomAccessFile(f ,"r");
			      raf.seek(14);
			 int headerSize = raf.readInt();
			      System.out.println("Header size " + headerSize);
			      HEADERSIZE = headerSize;
			      int width = raf.readInt();
			      System.out.println("Width " + width);
			      int height = raf.readInt();
			      System.out.println("Height " + height);

			 char planes = raf.readChar();
			      System.out.println("Number of planes " + (int)planes);
			      char bitsPixel = raf.readChar();
			      System.out.println("Bits per pixel " + (int)bitsPixel);
			      int compressionType = raf.readInt();
			      System.out.println("Compression type " + compressionType);
			      int imageSize = raf.readInt();
			      System.out.println("Image size " + imageSize);
			      int horzRes = raf.readInt();
			      System.out.println("Horizontal resolution " + horzRes);
			      int vertRes = raf.readInt();
			      System.out.println("Vertical resolution " + vertRes);
			      int numColors = raf.readInt();
			      System.out.println("Number of colors " + numColors);
			      int numImpColors = raf.readInt();

			      System.out.println("Number of important colors " + numImpColors);
			      raf.close();
			      
			      WritableRaster raster = image.getRaster();
			      DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
			      bmpbitCount = data.getSize();
			     
			    }
			    catch(IOException e){
			      e.printStackTrace();
			    }
			
			 
			 try {
		            in  = new FileInputStream("C:\\Images\\InfoSecPic.bmp");
		            //msg = new FileInputStream(userText);
		            out = new FileOutputStream("Output.bmp");
		            int c,mb;
		            byte clearBit1 = (byte) 0xFE; //254; // 11111110

		            for (int i=1;i<=HEADERSIZE;i++) 
		            	out.write(in.read()); //copy header

		            while ((mb = msg.read()) != -1) 
		            	{  // for all byte in message

		                for (int bit=7; bit>=0; bit--) 
		                {  c = in.read() & clearBit1;  
		                   c = (c | ((mb >> bit) & 1));
		                   out.write(c);               // add picture-byte in new file
		                }
		            }

		            for (int bit=7; bit>=0; bit--) // add 8 zeroes as stop-byte of msg
		            {  c = in.read() & clearBit1;  // get picture byte,clear last bit
		               out.write(c);               // add picture-byte in new file
		            }

		            while ((c = in.read()) != -1) 
		            out.write(c);// copy rest of file
		        }
			 	catch(IOException e){
			      e.printStackTrace();
			    }
		        finally {
		            if (in  != null)
						try {
							in.close();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
		            if (msg != null)
						try {
							msg.close();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
		            if (out != null)
						try {
							out.close();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
		        }
		    }

		public void saveImage() {
			System.out.println("Inside saveImage Image method");

			try {
				f = new File("F:\\Image\\Output.bmp"); // Destination path.
				ImageIO.write(image, "bmp", f);
				System.out.println("Writing of Image completed successfully.");
			} catch (IOException e) {
				System.out.println("Error: " + e);
			}

		}
	}

	public class Text implements InputText {
		@Override
		public void getText() {
			System.out.println("Inside gettext method");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Ni hao...Please enter text to hide in a picture.");
			
			
			try {
				userText = br.readLine();
				lenText = userText.length();
				//System.out.println("You entered " + userText);
			//System.out.println("Length of the text is " + lenText);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			 //Scanner in = new Scanner(System.in);
			//String enteredText = in.next();
			//userText = enteredText;
			// Close Scanner.in stream.
			//in.close();  
		}

		public void convertText() {
			System.out.println("Inside convert text method");

			 binaryText="";
		     String var="";
		     for(int i=0;i<userText.length();i++)
		     {
		    	 var=Integer.toBinaryString(userText.charAt(i));
		         for(int j=var.length();j<8;j++)
		         {
		        	 var="0"+var;
		         }
		         binaryText += var +" ";
		     }
			
		}
		
		public void extractText() {
			String binaryCode = binaryText; // This code would be improved to extract the binary code from the Image
			String[] hcode = binaryCode.split(" ");
			theExtractedText =" ";
		     for(int i=0;i<hcode.length;i++)
		     {
		    	 theExtractedText+= (char)Integer.parseInt(hcode[i],2);
		     }
		     System.out.println("The hidden text in the Image was: " + theExtractedText);
			
		}

	}
	
	// Actual implementation of abstract methods end here.
	
	
	// Various factory creations begin here. 
	
	public abstract class AbstractFactory {
		abstract BMPImage getImage_attr(String BMP);

		abstract InputText getText_attr(String Text);

	}

	public class ImageFactory extends AbstractFactory {

		@Override
		public BMPImage getImage_attr(String img) {

			if (img == null) {
				return null;
			} else if (img.contains("BMPImage")) {
				return new BMP();

			}

			return null;
		}

		@Override
		Text getText_attr(String Text) {
			return null;
		}

	}

	public class TextFactory extends AbstractFactory {

		@Override
		public BMPImage getImage_attr(String img) {
			return null;
		}

		@Override
		InputText getText_attr(String Text) {

			if (Text == null) {
				return null;
			}

			else if (Text.contains("Text")) {
				return new Text();

			}

			return null;
		}
	}

	public static class FactoryProducer {

		public static AbstractFactory getFactoryType(String option) {

			InfoSec job = new InfoSec();

			if (option.contains("BMPImage")) {

				return job.new ImageFactory();

			} else if (option.contains("Text")) {
				return job.new TextFactory();

			}

			return null;
		}

	}
	
	// Various factory creations end here. 

	public static void main(String[] args) {

		AbstractFactory ImgFactory = FactoryProducer.getFactoryType("BMPImage");

		BMPImage img = ImgFactory.getImage_attr("BMPImage");

		AbstractFactory TxtFactory = FactoryProducer.getFactoryType("Text");

		InputText txt = TxtFactory.getText_attr("Text");

		// Call desired methods if needed to work on Text

		txt.getText();
		txt.convertText();
		System.out.println(binaryText);
		txt.extractText();

		// Creating a gap just for readability.
		System.out.println("\n");

		// Call desired methods if needed to work on Image

		img.readImage();
		
		System.out.println(bmpHeight);
		System.out.println(bmpWidth);
		System.out.println(bpp);
		System.out.println(bmpSize + " bytes");
		System.out.println("BMP Bit Count is " + bmpbitCount);
		
		img.processImage();
		
		img.saveImage();

	}
}
