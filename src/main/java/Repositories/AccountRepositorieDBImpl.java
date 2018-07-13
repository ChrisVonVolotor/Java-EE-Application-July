package Repositories;

import java.util.List;

import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.Transactional.TxType;

import org.json.JSONObject;

import CDI.RepositoryManager;
import accountapp.Account;

@Transactional(value = TxType.SUPPORTS)
public class AccountRepositorieDBImpl implements RepositoryManager {

	@PersistenceContext
	private EntityManager em;

	public String findAnAccount(Long aId) {
		return "{\"Account\":"+ em.find(Account.class, aId)+"}";
	}

	public String findAllAccounts() {
		TypedQuery<Account> query = em.createQuery("SELECT * FROM Account m ORDER BY m.aid DESC", Account.class);
		return "{\"Accounts\":"+query.getResultList()+"}";
	}

	@Transactional(value = TxType.REQUIRED)
	public String createAnAccount(String account) {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		return "{\"new Account\":"+ account+"}";
	}

	@Transactional(value = TxType.REQUIRED)
	public String updateAnAccount(long aId, String account) {
		em.find(Account.class, aId);
		JSONObject obj = new JSONObject(account);		
		em.getTransaction().begin();
		account.setFirstName(obj.firstName);
		account.setLastName(obj.lastName);
		em.getTransaction().commit();
		return "{\"Account\":"+ em.find(Account.class, aId)+"}";
	}

	@Transactional(value = TxType.REQUIRED)
	public String deleteAnAccount(Long aId) {
		

		em.getTransaction().begin();
		em.remove(em.find(Account.class, aId));
		em.getTransaction().commit();
		return "{\"Deleted\":"+aId+"}";
	}

}
