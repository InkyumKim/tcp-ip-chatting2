import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * TCP 클라이언트 프로그램
 */

public class TcpClient {

	public static void main(String[] args) {
		String serverIp = "127.0.0.1";  //내컴퓨터의 ip 주소
		System.out.println("서버에 연결중입니다. 서버ip: " +serverIp);
		
		//서버에 연결 요청: 서버의 ip주소와 포트번호 지정
		try {
			Socket socket = new Socket(serverIp, 49152);
			
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			
			//데이터를 수신할 입력스트립을 얻어온다.
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("서버로 전송할 데이터를 입력하세요: ");
			String outMessage = scanner.nextLine();
			
			dos.writeUTF(outMessage);
			
			//서버에서 보낸 메시지 수신
			System.out.println("서버에서 받은 메시지: " +dis.readUTF());
			
			out.close();
			dos.close();  //출력 스트림 close
			in.close();
			dis.close();
			socket.close(); //통신 연결 해제
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
