 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class MyConnect {
 
        private final String className = "com.mysql.jdbc.Driver";
        private final String url = "jdbc:mysql://localhost:3306/oop";
        private final String user ="root";
        private final String pass ="root";
        private String table = "khach_hang";
        private Connection connection;
        
        // kết nối đến cơ sở dữ liệu
        public void connect() {
        
                try {
                        Class.forName(className);
                        connection = DriverManager.getConnection(url, user, pass);
                        System.out.println("Connect success!");
                } catch (ClassNotFoundException e) {
                        
                } catch (SQLException e) {
                        System.out.println("Error connection!");
                }
        }
        
        // hiển thị dữ liệu 
        public void showData(ResultSet rs) {
    		try {
    			while (rs.next()) {
    				System.out.printf("%-10s %-20s %-5.2f \n", rs.getString(1),
    						rs.getString(2), rs.getDouble(3), rs.getString(4));
    			}
    		} catch (SQLException e) {
    		}
    	}
        
        //  lấy dữ liệu và trả về Resultset với lệnh select
        public ResultSet getData() {
    		ResultSet rs = null;
    		String sqlCommand = "select * from " + table;
    		Statement st;
    		try {
    			st = connection.createStatement();
    			rs = st.executeQuery(sqlCommand);
    		} catch (SQLException e) {
    			System.out.println("select error \n" + e.toString());
    		}
    		return rs;
    	}
        
        // lấy dữ liệu và trả về Resultset với lệnh được chọn bởi Id
      /*  public ResultSet getDataId(String id) {
    		ResultSet rs = null;
    		String sqlCommand = "select * from " + table + " where id = ?";
    		PreparedStatement pst = null;
    		try {
    			pst = connection.prepareStatement(sqlCommand);
    			// replace "?" by id
    			pst.setString(1, id);
    			rs = pst.executeQuery();
    		} catch (SQLException e) {
    			System.out.println("select error \n" + e.toString());
    		}
    		return rs;
    	} */
        
        // xóa bởi id
        public void deleteId(String id) {
    		String sqlCommand = "delete from " + table + " where id = ?";
    		PreparedStatement pst = null;
    		try {
    			pst = connection.prepareStatement(sqlCommand);
    			pst.setString(1, id);
    			if (pst.executeUpdate() > 0) {
    				System.out.println("delete success");
    			} else {
    				System.out.println("delete error \n");
    			}
    		} catch (SQLException e) {
    			System.out.println("delete error \n" + e.toString());
    		}
    	}
        
        // thêm khách hàng vào cơ sở dữ liệu
        public void insert(Khachhang s) {
    		String sqlCommand = "insert into " + table + " value(?, ?, ?, ?)";
    		PreparedStatement pst = null;
    		try {
    			pst = connection.prepareStatement(sqlCommand);
    			
    			pst.setString(1, s.getId());
    			pst.setString(2, s.getName());
    			pst.setDouble(3, s.getPhone());
    			pst.setString(4, s.getDateOfBirth());
    			if (pst.executeUpdate() > 0) {
    				System.out.println("insert success");
    			} else {
    				System.out.println("insert error \n");
    			}
    		} catch (SQLException e) {
    			System.out.println("insert error \n" + e.toString());
    		}
    	}
        
        // update thông tin khách hàng
        public void updateId(String id, Khachhang s) {
    		String sqlCommand = "update " + table
    				+ " set name = ?, phone = ? " + " where id = ?";
    		PreparedStatement pst = null;
    		try {
    			pst = connection.prepareStatement(sqlCommand);
    			
    			pst.setString(1, s.getName());
    			pst.setDouble(2, s.getPhone());
    			
    			pst.setString(3, s.getId());
    			pst.executeUpdate();
    			if (pst.executeUpdate() > 0) {
    				System.out.println("update success");
    			} else {
    				System.out.println("update error \n");
    			}
    		} catch (SQLException e) {
    			System.out.println("update error \n" + e.toString());
    		}
    	}
        public static void main(String[] args) {
                MyConnect myConnect = new MyConnect();
                myConnect.connect();
              //  myConnect.showData(myConnect.getData());
                
        }
}