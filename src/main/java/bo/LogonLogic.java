package bo;

import dao.AccountDao;
import entity.Account;

public class LogonLogic {
	public static boolean execute(Account ac) {
		AccountDao dao = new AccountDao();
		return dao.registerAccount(ac);
	}
}
