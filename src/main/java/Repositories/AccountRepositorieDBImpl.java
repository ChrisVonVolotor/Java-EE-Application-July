package Repositories;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;
import org.json.JSONObject;

import CDI.RepositoryManager;
import accountapp.Account;
import util.JSONUtil;

@Transactional(SUPPORTS)
public class AccountRepositorieDBImpl implements RepositoryManager {
	 JSONObject json = new JSONObject();

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private JSONUtil util;


	public String findAnAccount(Long aId) {
		return util.getJSONForObject(em.find(Account.class, aId));
	}
	
	public Account getAnAccount(Long aId) {
		return em.find(Account.class, aId);
	}

	public String findAllAccounts() {
		Query query = em.createQuery("SELECT m FROM Account m");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED)
	public String createAnAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		em.persist(anAccount);

		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateAnAccount(Long aId, String account) {
		Account thisAccount = util.getObjectForJSON(account, Account.class);
		Account thenAccount = getAnAccount(aId);
		if(thenAccount != null) {
		thenAccount = thisAccount;
		em.merge(thenAccount);
		return "{\"message\": \"account sucessfully updated\"}";
		}else {
			return "{\"message\": \"account not updated\"}";

		}
	}

	@Transactional(REQUIRED)
	public String deleteAnAccount(Long aId) {
		Account accountInDB = getAnAccount(aId);
		em.remove(accountInDB);
		return "{\"message\": \"account sucessfully deleted\"}";
	}

}
