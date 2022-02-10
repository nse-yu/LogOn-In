package bo;

import dao.AccountDao;
import entity.Account;

public class LoginLogic {
	public static boolean execute(Account ac) {
		AccountDao dao = new AccountDao();
		return dao.isCorrespondingAccout(ac);
	}
}
