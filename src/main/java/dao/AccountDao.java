package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Account;

public class AccountDao {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/Documents/database/shopping";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	private List<Account> selectAll()
	{
		//list jdfao
		List<Account> list = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "SELECT * FROM ACCOUNT";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//result
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				Account sample = new Account(
						result.getString("USER_ID"),
						result.getString("PASS")
					);
				list.add(sample);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	/**ログイン確認。データベースに登録済みアカウントがある場合にtrue,そうでないならばfalseを返す。*/
	public boolean isCorrespondingAccout(Account account) 
	{
		//Accountリスト全取得
		List<Account> ac_list = selectAll();
		for(Account sample: ac_list) 
		{
			//idとpassが一致するときのみ
			if(account.equals(sample))
				return true;
		}
		return false;
	}
	
	public boolean registerAccount(Account account) 
	{
		List<Account> ac_list = selectAll();
		for(Account sample: ac_list) 
		{
			if(account.getUserId().strip().equals(sample.getUserId().strip()) ||
					account.getPass().strip().equals(sample.getPass().strip()))
				return false;
		}
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "INSERT INTO ACCOUNT(USER_ID,PASS,MAIL,NAME,AGE) VALUES(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getUserId());
			ps.setString(2, account.getPass());
			ps.setString(3, account.getMail());
			ps.setString(4, account.getName());
			ps.setInt(5, account.getAge());
			
			//execute
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
