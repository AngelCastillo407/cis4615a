import java.io.IOException; 
import java.util.logging.*;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.File; 
import java.util.Scanner;  // Import the Scanner classcl 
import java.math.BigInteger; 

public class Cis4615a {

	// RULE 00:
	// INCORRECT
	// DOESN'T SANITIZE DATA BEFORE LOGGING 
	
	public void rule00()
	{
		int loginSucessful = 0;
		
		Logger logger = Logger.getLogger( Cis4615a.class.getName());
		logger.setLevel(Level.SEVERE); 		 
		
		System.out.println("Press any key then press enter to sucessfully login:");
		
		Scanner scanner = new Scanner(System.in);
		String userName = scanner.nextLine();  // Read user input
		
		if (userName.length() > 0)
			loginSucessful = 1;
		
		if (loginSucessful == 1)
			logger.severe("User Login succeeded for: " + userName);
		else 
			logger.severe("User login failed for: " + userName);
	}
	
	// RULE 02:
	// INCORRECT
	// IGNORES VALUE RETURNED BY METHOD
	
	public void rule02()
	{
		File someFile = new File("someFileName.txt");
		// Do something with someFile
		someFile.delete();
	}

	// RULE 03:
	// INCORRECT
	// DOESN'T FULLY REPRESENT POSSIBLE RANGE OF UNSIGNED DATA
	
	public static int rule03(DataInputStream is) throws IOException 
	{
		return is.readInt();
	}

	// RULE 04: 
	// INCORRECT
	// ENCODES NONCHARACTER DATA AS A STRING
	
	public void rule04()
	{
		BigInteger x = new BigInteger("530500452766");
		byte[] byteArray = x.toByteArray();
		String s = new String(byteArray);
		byteArray = s.getBytes();
		x = new BigInteger(byteArray);
	}

	// RULE 06:
	// INCORRECT
	// USES ASSERTIONS TO VALIDATE METHOD ARGUMENTS
	
	public static int rule06(int x, int y)
	{
		assert x != Integer.MIN_VALUE;
		assert y != Integer.MIN_VALUE;
		int absX = Math.abs(x);
		int absY = Math.abs(y);
		assert ( (absX) <= (Integer.MAX_VALUE - absY) );
		return absX + absY;
	}	
	
    public static void main(String[] args) {
		
		Cis4615a a = new Cis4615a();
		int b = 0;
		a.rule00();
		a.rule02();
				
		
		try
		{
			DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("NumText.txt"));
			dataOut.writeInt(5);
			
			DataInputStream stream = new DataInputStream(new FileInputStream("NumText.txt"));

			b = a.rule03(stream);
		}
		
		catch(IOException e) 
		{
			 e.printStackTrace();
		}

		a.rule04();
		
		b = a.rule06(5,4);
		
		System.out.println("File returned an int value of: " + b);
					
        System.out.println("- from ANGEL CASTILLO");
    }

}
