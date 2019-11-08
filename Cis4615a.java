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
	
	// RULE 02:
	// EXP02-J. Do not use the Object.equals() method to compare two arrays
	// INCORRECT
	// USES Object.EQUALS
	
	public void rule02b(int[] arr1, int[] arr2)
	{
		System.out.println(arr1.equals(arr2)); // Prints false
	}

	// RULE 03:
	// INCORRECT
	// DOESN'T FULLY REPRESENT POSSIBLE RANGE OF UNSIGNED DATA
	
	public static int rule03(DataInputStream is) throws IOException 
	{
		return is.readInt();
	}

	// RULE 03:
	// NUM01-J. Do not perform bitwise and arithmetic operations on the same data
	// INCORRECT
	// PERFORMS BITWISE AND ARITHMETIC OPERATIONS ON THE SAME DATA
	
	public static void rule03b() 
	{
		int x = 50;
		int y = x << 2;
		x += y + 1;
		
		System.out.println("When you perform bitwise and arithmetic operations you get x = " + x + "  , a value NOT intended");
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
	
	// RULE 05: Object Orientation (OBJ)
	// OBJ09-J. Compare classes and not class names
	// INCORRECT
	// COMPARES CLASS NAMES AND NOT ACTUAL CLASSSES
	
	public void rule05(Cis4615a x, Cis4615a y )
	{
		// Determine whether objects x and y have the same class name
		if (x.getClass().getName().equals(y.getClass().getName())) 
		{
			System.out.println("These objects have the same class, I checked comparing the class names (and not the classes themselves, WRONG!)");
		}
		
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

	// RULE 06:
	// MET00-J. Validate method arguments
	// INCORRECT
	// DOESNT VALIDATE METHOD ARGUMENTS
	
	public static int rule06b(int x, int y)
	{
		// Could possibly run into divide by 0 problem
		return x / y;
	}
	
	// RULE 49: MISCELLANEOUS (
	// MSC01-J. Do not use an empty infinite loop
	// INCORRECT
	// USES AN EMPTY INFINITE LOOP
	
	public void rule49()
	{
		while(true) {}
	}
	
    public static void main(String[] args) {
		
		Cis4615a a = new Cis4615a();
		Cis4615a aa = new Cis4615a();
		Cis4615a aaa = new Cis4615a();
		int b = 0;
		
		a.rule00();
		a.rule02();
		
		int[] arr1 = new int[20]; // Initialized to 0
		int[] arr2 = new int[20]; // Initialized to 0
		
		a.rule02b(arr1, arr2);

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
		
		a.rule03b();

		a.rule04();
		
		a.rule05(aa, aaa);
		
		b = a.rule06(5,4);
		
		b = a.rule06b(5,4);
		
		System.out.println("File returned an int value of: " + b);
					
        System.out.println("- from ANGEL CASTILLO");
		
		a.rule49();
    }

}
