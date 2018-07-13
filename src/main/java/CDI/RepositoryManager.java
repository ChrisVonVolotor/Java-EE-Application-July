package CDI;

import java.util.List;

import accountapp.Account;

public interface RepositoryManager {
	public Account findAnAccount(Long aId);
	public List<Account> findAllAccounts();
	public Account createAnAccount(Account account);
	public Account updateAnAccount(Account account, String firstName, String lastName);
	public String deleteAnAccount( Account account);
}
