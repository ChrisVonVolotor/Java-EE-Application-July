package CDI;

import java.util.List;

import accountapp.Account;

public interface RepositoryManager {
	public String findAnAccount(Long aId);
	public String findAllAccounts();
	public String createAnAccount(String account);
	public String updateAnAccount(long id, String account);
	public String deleteAnAccount( Long aId);
}
