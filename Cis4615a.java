import java.io.IOException; 
import java.util.logging.*;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.File; 
import java.util.Scanner;  // Import the Scanner classcl 

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

	// RULE 03
	// INCORRECT
	// DOESN'T FULLY REPRESENT POSSIBLE RANGE OF UNSIGNED DATA
	
	public static int rule03(DataInputStream is) throws IOException 
	{
		return is.readInt();
	}

	public void rule04()
	{
		
	}

	public void rule06()
	{
		
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

		
		System.out.println("File returned an int value of: " + b);
					
        System.out.println("- from ANGEL CASTILLO");
    }

}
