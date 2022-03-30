import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * TCP Ŭ���̾�Ʈ ���α׷�
 */

public class TcpClient {

	public static void main(String[] args) {
		String serverIp = "127.0.0.1";  //����ǻ���� ip �ּ�
		System.out.println("������ �������Դϴ�. ����ip: " +serverIp);
		
		//������ ���� ��û: ������ ip�ּҿ� ��Ʈ��ȣ ����
		try {
			Socket socket = new Socket(serverIp, 49152);
			
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			//�����͸� ������ �Է½�Ʈ���� ���´�.
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("������ ������ �����͸� �Է��ϼ���: ");
			String outMessage = scanner.nextLine();
			
			dos.writeUTF(outMessage);
			
			//�������� ���� �޽��� ����
			System.out.println("�������� ���� �޽���: " +dis.readUTF());
			
			out.close();
			dos.close();  //��� ��Ʈ�� close
			in.close();
			dis.close();
			socket.close(); //��� ���� ����
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
