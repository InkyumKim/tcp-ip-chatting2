import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * TCP ���� ���α׷�
 */

public class TcpServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(49152);//��Ʈ����
			System.out.println(getTime()+": ������ �غ�Ǿ����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		
		while(true) {
			try {
				
				//���� ��û ����
				Socket socket = serverSocket.accept();
				
				//�����͸� �۽��ϱ� ���� ��½�Ʈ���� ���´�.
				OutputStream out = socket.getOutputStream();
				//��½�Ʈ���� ���ڴ��� ���� ��Ʈ������ ��ȯ
				DataOutputStream dos = new DataOutputStream(out);
				
				InputStream in = socket.getInputStream();
				DataInputStream dis = new DataInputStream(in);
				
				String rm = dis.readUTF();
				System.out.println("Ŭ���̾�Ʈ �۽� �޽���:" + rm);
				
				String outMessage ="[����]"+rm;
				dos.writeUTF(outMessage);
				System.out.println("���� ���� �޽���:" + outMessage);
				
				out.close();
				dos.close();  //��� ��Ʈ�� close
				in.close();
				dis.close();
				socket.close(); //��� ���� ����
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		}
	}
	
	/*
	 * ���� �ð��� ����ϴ� �޼ҵ�
	 */
	
	public static String getTime() {
		//����� �ð� ���� ����
		SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss] ");
		
		return sdf.format(new Date());
	}

}
