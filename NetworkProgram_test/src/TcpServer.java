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
 * TCP 서버 프로그램
 */

public class TcpServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(49152);//포트지정
			System.out.println(getTime()+": 서버가 준비되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} 	
		
		while(true) {
			try {
				
				//연결 요청 수락
				Socket socket = serverSocket.accept();
				
				//데이터를 송신하기 위한 출력스트림을 얻어온다.
				OutputStream out = socket.getOutputStream();
				//출력스트림을 글자단위 전송 스트림으로 전환
				DataOutputStream dos = new DataOutputStream(out);
				
				InputStream in = socket.getInputStream();
				DataInputStream dis = new DataInputStream(in);
				
				String rm = dis.readUTF();
				System.out.println("클라이언트 송신 메시지:" + rm);
				
				String outMessage ="[응답]"+rm;
				dos.writeUTF(outMessage);
				System.out.println("서버 응답 메시지:" + outMessage);
				
				out.close();
				dos.close();  //출력 스트림 close
				in.close();
				dis.close();
				socket.close(); //통신 연결 해제
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		}
	}
	
	/*
	 * 현재 시각을 출력하는 메소드
	 */
	
	public static String getTime() {
		//출력할 시간 포맷 생성
		SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss] ");
		
		return sdf.format(new Date());
	}

}
