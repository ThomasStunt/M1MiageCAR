import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Calculator Server
 * Accept connections on the port 4081 and computes the result of the operations he is given.
 */
public class CalculServeur {

	public static void main(String[] args) {
		System.out.println("CalculServer launch...");
		ServerSocket ps = null;
		Socket as = null;
		BufferedReader in = null;
		DataOutputStream out = null;
		Boolean exit = false;
		try {
			ps = new ServerSocket(4081);
			while (!exit) {
				as = ps.accept();
				System.out.println("Connection established");
				in = new BufferedReader(new InputStreamReader(as.getInputStream()));
				out = new DataOutputStream(as.getOutputStream());
				try {
					String msg = in.readLine();
					while (!exit) {
						try {
							System.out.println("Message received : " + msg);
							String resp = null;
							if (msg.contentEquals("exit")) {
								exit = true;
								System.out.println("Connection over");
							} else if (msg.contentEquals("leave")) {
								System.out.println("Client leave...");
								break;
							} else {
								resp = String.valueOf(ExpressionParser.eval(msg));
								out.writeBytes(resp + '\n');
								System.out.println("Response \"" + resp + "\" has been sent.");
								msg = in.readLine();
							}
						} catch (RuntimeException e) {
							System.err.println("Can't handle this calcul...");
							out.writeBytes("Error\n");
							System.err.println("Error has been sent.");
							msg = in.readLine();
						}
					}
				} catch (SocketException e) {
					System.out.println("Client leave...");
				}
			}
		} catch (IOException ex) {
			System.err.println("An error occured, CalculServer will shut down...");
			ex.printStackTrace();
		} finally {
			try {
				ps.close();
				as.close();
				in.close();
				out.close();
			} catch (IOException e) {
			}
			System.exit(-1);
		}
	}

}