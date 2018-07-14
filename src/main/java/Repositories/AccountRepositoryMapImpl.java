package Repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Alternative;

import CDI.RepositoryManager;
import accountapp.Account;

@Alternative
public class AccountRepositoryMapImpl implements RepositoryManager {

	HashMap<Long, String> base = new HashMap<Long, String>();
	@Override
	public String findAnAccount(Long aId) {
		
		return base.get(aId);
	}

	@Override
	public String findAllAccounts() {
		List<String> listAccounts = null;
		for (String acc : base.values()) {
			listAccounts.add(acc);
		}
		return "{\"Accounts\":"+listAccounts+"}";
	}

	@Override
	public String createAnAccount(String account) {
		base.put( (long)(base.size()), account);
		return "{\"new Account\":"+ account+"}";
	}

	@Override
	public String updateAnAccount(Long aId, String account) {
		base.put(aId, account);
		return "{\"Account\":"+ account+"}";
	}

	@Override
	public String deleteAnAccount(Long aId) {
		base.remove(aId);
		return "{\"Deleted\":"+aId+"}";
	}
}
