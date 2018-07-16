package Repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import CDI.RepositoryManager;
import accountapp.Account;
import util.JSONUtil;

@Alternative
@ApplicationScoped
public class AccountRepositoryMapImpl implements RepositoryManager {
	HashMap<Long, Account> base;

	@Inject
	private JSONUtil util;
	
	public AccountRepositoryMapImpl() {
		this.base = new HashMap<Long, Account>();
		initAccountMap();
	}

	private void initAccountMap() {
		Account account = new Account(1234L , "Moe", "Syzlack");
		base.put(1234L, account);

	}

	@Override
	public String findAnAccount(Long aId) {

		return util.getJSONForObject(base.get(aId));
	}

	@Override
	public String findAllAccounts() {

		return util.getJSONForObject(base.values());
	}

	@Override
	public String createAnAccount(String account) {
		Account thisAccount = util.getObjectForJSON(account, Account.class);
		base.put(thisAccount.getaId(), thisAccount);

		return account;
	}

	@Override
	public String updateAnAccount(Long aId, String account) {
		Account thisAccount = util.getObjectForJSON(account, Account.class);
		base.put(aId, thisAccount);
		return account;
	}

	@Override
	public String deleteAnAccount(Long aId) {
		base.remove(aId);
		return "{\"message\": \"accout sucessfully removed\"}";
	}
}
