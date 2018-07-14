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
		return "{\"Account\":"+ em.find(Account.class, aId)+"}";
	}

	public String findAllAccounts() {
		Query query = em.createQuery("SELECT m FROM Account m ORDER BY m.aId DESC", Account.class);
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED)
	public String createAnAccount(String account) {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		return "{\"new Account\":"+ account+"}";
	}

	@Transactional(REQUIRED)
	public String updateAnAccount(Long aId, String account) {
		em.find(Account.class, aId);
		JSONObject obj = new JSONObject(account);		
		em.getTransaction().begin();
		//account.setFirstName(obj.firstName);
		//account.setLastName(obj.lastName);
		em.getTransaction().commit();
		return "{\"Account\":"+ em.find(Account.class, aId)+"}";
	}

	@Transactional(REQUIRED)
	public String deleteAnAccount(Long aId) {
		

		em.getTransaction().begin();
		em.remove(em.find(Account.class, aId));
		em.getTransaction().commit();
		return "{\"Deleted\":"+aId+"}";
	}

}
