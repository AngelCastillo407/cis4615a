import java.io.IOException; 
import java.util.logging.*;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.File; 
import java.util.Scanner;  // Import the Scanner classcl 
import java.math.BigInteger; 
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays; 

public class Cis4615a {

	// RULE 00:
	// CORRECT
	// SANITIZES DATA BEFORE LOGGING 
	
	public void rule00()
	{
		int loginSucessful = 0;
		
		Logger logger = Logger.getLogger( Cis4615a.class.getName());
		logger.setLevel(Level.SEVERE); 		 
		
		System.out.println("Enter your username then press enter \n(login is a success as long as length of username is not 0):");
		
		Scanner scanner = new Scanner(System.in);
		String userName = scanner.nextLine();  // Read user input
		
		if (userName.length() > 0)
			loginSucessful = 1;
		
		if (loginSucessful == 1)
			logger.severe("User Login succeeded for: " + sanitizeUser(userName));
		else 
			logger.severe("User login failed for: " + sanitizeUser(userName));
	}
	
	public String sanitizeUser (String username) 
	{
		boolean a = Pattern.matches("[a-zA-Z0-9_]+", username);
		
		if (a == false)
			return "unauthorized user";
		
		return username + " , a username that was sanitized";
	}
	
	// RULE 02:
	// CORRECT
	// DOESN'T IGNORE VALUE RETURNED BY METHOD
	
	public void rule02()
	{
		File someFile = new File("someFileName.txt");
		// Do something with someFile
		
		try
		{
			if (! someFile.createNewFile() )
				System.out.println("\nCouldn't create that file sir! \n[But we didn't ignore value returned by the method createNewFile]");
			else
				System.out.println("\nFile created sir! \n[And we didn't ignore value returned by the method createNewFile]");
		}
		
		catch(IOException e) 
		{
			// Do Nothing
		}
	}
	
	// RULE 02:
	// EXP02-J. Do not use the Object.equals() method to compare two arrays
	// CORRECT
	// DOESN'T USE Object.EQUALS
	
	public void rule02b(int[] arr1, int[] arr2)
	{
		System.out.println("\nIf i compare these 2 arrays using Array.equals and NOT use their respective Object.equals, \nI get the proper result: " + Arrays.equals(arr1, arr2)); // Prints true
	}

	// RULE 03:
	// CORRECT
	// FULLY REPRESENT POSSIBLE RANGE OF UNSIGNED DATA
	
	public static long rule03(DataInputStream is) throws IOException 
	{
		System.out.println("\nWhen I scan this datastream for an integer, \nI fully represent the possible range of unsigned data by masking readInt with & 0xFFFFFFFFL");
		return is.readInt() & 0xFFFFFFFFL; // Mask with 32 one-bits
	}

	// RULE 03:
	// NUM01-J. Do not perform bitwise and arithmetic operations on the same data
	// CORRECT
	// DOES NOT PERFORM BITWISE AND ARITHMETIC OPERATIONS ON THE SAME DATA
	
	public static void rule03b() 
	{
		int x = 50;
		x = 5 * x + 1;
		
		System.out.println("\nWhen you perform bitwise and arithmetic operations SEPERATELY vs SIMULTANEOUSLY you get x = " + x + ",\n a value that IS intended");
	}
	
	// RULE 04: 
	// CORRECT
	// DOES NOT ENCODE NONCHARACTER DATA AS A STRING
	
	public void rule04()
	{
		BigInteger x = new BigInteger("530500452766");
		String s = x.toString(); // Valid Character Data
		byte[] byteArray = s.getBytes();
		String ns = new String(byteArray);
		x = new BigInteger(ns);
		
		System.out.println("\n" + x + " is the Big Integer resultant when you DON'T encode noncharacter data as a string \n(thus following the proper practice)");
	}
	
	// RULE 05: Object Orientation (OBJ)
	// OBJ09-J. Compare classes and not class names
	// CORRECT
	// COMPARES ACTUAL CLASSSES AND NOT CLASS NAMES
	
	public void rule05(Cis4615a x, Cis4615a y )
	{
		// Determine whether objects x and y have the same class name
		if ( x.getClass() == y.getClass() ) 
		{
			System.out.println("\nThese objects have the same class, I checked comparing the ACTUAL classes \n(and not the class names which is WRONG!)");
		}
	}
	
	// RULE 06:
	// CORRECT
	// DOESN'T USE ASSERTIONS TO VALIDATE METHOD ARGUMENTS
	
	public static int rule06(int x, int y)
	{
		if (x == Integer.MIN_VALUE || y == Integer.MIN_VALUE) 
		{
			throw new IllegalArgumentException();
		}
		
		int absX = Math.abs(x);
		int absY = Math.abs(y);
		
		if (absX > Integer.MAX_VALUE - absY) 
		{
			throw new IllegalArgumentException();
		}
		
		System.out.println("\nThe absolute value sum of " + x + " and " + y + " is equal to " + (absX+absY));
		System.out.println("This summation does NOT use assertions to validate method arguments when using abs()");
		
		return absX + absY;
	}

	// RULE 06:
	// MET00-J. Validate method arguments
	// CORRECT
	// DOES VALIDATE METHOD ARGUMENTS
	
	public static int rule06b(int x, int y)
	{
		if (y == 0)
		{
				System.out.println("\nThe number " + x + " divided by " + y + " is undefined");
				System.out.println("This method checks if the second number is 0 because we VALIDATE METHOD ARGUMENTS");
				return 0;
		}
	
		
		// Could possibly run into divide by 0 problem
		
		System.out.println("\nThe number " + x + " divided by " + y + " is equal to " + (x / y));
		System.out.println("This method checks if the second number is 0 because we VALIDATE METHOD ARGUMENTS");
		return x / y;
	}
	
	// RULE 49: MISCELLANEOUS (
	// MSC01-J. Do not use an empty infinite loop
	// CORRECT
	// DOESN'T USE AN EMPTY INFINITE LOOP
	
	public void rule49()
	{
		
		System.out.println("\nMy favorite rule: do NOT use an empty infinite loop, \nthe compiler will sometimes ignore your infinite loop and skip over it,\nseeing it as taking up cpu cycles while doing nothing"); 
		System.out.println("\nIf you have your infinite loop do Thread.yield(), it will loop infinitely as intended :)");
		System.out.println("Since I did it properly you are now in an infinite loop, please close the program or use ctrl+c if on windows to exit");
		System.out.println("\n- from ANGEL CASTILLO");
		while(true) 
		{
			 Thread.yield();
		}
	}
	
    public static void main(String[] args) {
		
		Cis4615a a = new Cis4615a();
		Cis4615a aa = new Cis4615a();
		Cis4615a aaa = new Cis4615a();
		int b = 0;
		long bb = 0;
		
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

			bb = a.rule03(stream);
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
		
		 a.rule49();
    }

}
