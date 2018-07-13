package Repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import CDI.RepositoryManager;
import accountapp.Account;

@Alternative
public class AccountRepositoryMapImpl implements RepositoryManager {

	HashMap<Long, Account> base = new HashMap<Long, Account>();
	@Override
	public Account findAnAccount(Long aId) {
		
		return base.get(aId);
	}

	@Override
	public List<Account> findAllAccounts() {
		List<Account> listAccounts = null;
		for (Account acc : base.values()) {
			listAccounts.add(acc);
		}
		return listAccounts;
	}

	@Override
	public Account createAnAccount(Account account) {
		base.put(account.getaId(), account);
		return account;
	}

	@Override
	public Account updateAnAccount(Account account, String firstName, String lastName) {
		base.put(account.getaId(), account);
		return account;
	}

	@Override
	public String deleteAnAccount(Account account) {
		base.remove(account.getaId());
		return account.getaId() + " Deleted";
	}
}
