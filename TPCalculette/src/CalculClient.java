import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CalculClient {

	public static void main(String[] args) {
		Socket as = null;
		try {
			as = new Socket(InetAddress.getLocalHost(), 4000);
			System.out.println("Client: Connection established");
			DataOutputStream out = new DataOutputStream(as.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(as.getInputStream()));
			Scanner sc = new Scanner(System.in);
			System.out.print("Veuillez entrer une phrase : ");
			String sentence = sc.nextLine();
			while(!sentence.contentEquals("stop")) {
				sentence += '\n';
				out.writeBytes(sentence);
				System.out.println("Message sent");
				String response = in.readLine();
				System.out.println("Message received: " + response);
				System.out.print("Veuillez entrer une phrase : ");
				sentence = sc.nextLine();
				if(sentence.contentEquals("stop")) {
					sentence += '\n';
					out.writeBytes(sentence);
					System.out.println("Leaving");
					as.close();
				}
			}
			sc.close();
		} catch (UnknownHostException ex) {
			System.exit(-1);
		} catch (IOException ex) {
			System.exit(-1);
		}
	}
	
}
