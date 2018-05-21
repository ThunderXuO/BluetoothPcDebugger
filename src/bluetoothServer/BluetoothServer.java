package bluetoothServer;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class BluetoothServer implements Runnable{
	
	
	private StreamConnectionNotifier streamConnectionNotifier = null;
	
	private StreamConnection streamConnection = null;
	
	private InputStream inputStream = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BluetoothServer();

	}
	public BluetoothServer(){
		try {
			streamConnectionNotifier = (StreamConnectionNotifier) Connector.open("btspp://localhost:0000110100001000800000805F9B34FB");
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		new Thread(this).start();
		
	}
	public void run() {
		try {
	
			while (true) {
				streamConnection = streamConnectionNotifier.acceptAndOpen();
				
				inputStream = streamConnection.openInputStream();
				byte[] recivedDatas = new byte[1024];
				
				while (inputStream.read(recivedDatas) != -1) {
					System.out.println(String.valueOf(recivedDatas));
				}
				inputStream.close();
			}
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
