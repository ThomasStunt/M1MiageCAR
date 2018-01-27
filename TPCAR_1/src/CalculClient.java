import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Calculator Client
 * Connects to a Calculator Server on port 4081. You need to send operations to the server (+, -, *, /).
 */
public class CalculClient {

	public static void main(String[] args) {
		System.out.println("CalculClient launch...");
		Socket as = null;
		DataOutputStream out = null;
		BufferedReader in = null;
		Scanner sc = null;
		String sentence = new String();
		boolean exit = false;
		double res = 0;
		try {
			as = new Socket(InetAddress.getLocalHost(), 4081);
			System.out.println("Client : Connection established");
			out = new DataOutputStream(as.getOutputStream());
			in = new BufferedReader(new InputStreamReader(as.getInputStream()));
			sc = new Scanner(System.in);
			while (!exit) {
				System.out.print("Print your calcul (enter 'leave' or  'exit' to stop the communication with the server) : ");
				sentence = sc.nextLine();
				// Leaving conditions
				if (sentence.contentEquals("exit") || sentence.contentEquals("leave")) {
					exit = true;
					sentence += '\n';
					out.writeBytes(sentence);
					System.out.println("Leaving...");
				} else {
					// Checks if the user wants to add something to the result he already had. If he starts with it, the starting result will be 0.0.
					if (sentence.charAt(0) == '+' || sentence.charAt(0) == '-' || sentence.charAt(0) == '/'
							|| sentence.charAt(0) == '*')
						sentence = String.valueOf(res).concat(sentence);
					sentence += '\n';
					out.writeBytes(sentence);
					System.out.println("Message sent");
					String response = in.readLine();
					try {
						res = Double.parseDouble(response);
					} catch (NumberFormatException e) {
						//You haven't entered a number.
						System.err.println("This isn't a number...");
					}
					System.out.println("Message received : " + response);
				}
			}
			sc.close();
		} catch (IOException ex) { // Server is not launched
			System.err.println("An error occured, CalculClient will shut down...");
			System.err.println(ex.getMessage());
		} finally {
			try {
				as.close();
				out.close();
				in.close();
				sc.close();
			} catch (IOException e) {
			}
			System.exit(-1);
		}
	}

}
