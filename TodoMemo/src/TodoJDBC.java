import java.sql.Connection;
import java.sql.DriverManager;

public class TodoJDBC {

	 public static void main(String[] args) {
	        // DB 접속 정보 설정
	        String url = "jdbc:oracle:thin:@localhost:1521:xe";  // SID가 xe인 경우
	        String user = "C##TODO";        // 너가 만든 오라클 사용자명
	        String password = "TODO";    // 설정한 비밀번호

	        try {
	            // 드라이버 로딩 (ojdbc 드라이버)
	            Class.forName("oracle.jdbc.driver.OracleDriver");

	            // DB 연결 시도
	            Connection conn = DriverManager.getConnection(url, user, password);
	            System.out.println("Oracle DB 연결 성공!");

	            // 연결 종료
	            conn.close();
	        } catch (Exception e) {
	            System.out.println("연결 실패:");
	            e.printStackTrace();
	        }
	    }
}
