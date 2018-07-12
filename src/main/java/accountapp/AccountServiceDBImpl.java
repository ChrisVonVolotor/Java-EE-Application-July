package accountapp;

import java.util.List;

import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.Transactional.TxType;

@Transactional(value = TxType.SUPPORTS)
public class AccountServiceDBImpl {

	@PersistenceContext
	private EntityManager em;

	public Account findAnAccount(Long aId) {
		return em.find(Account.class, aId);
	}

	public List<Account> findAllAccounts() {
		TypedQuery<Account> query = em.createQuery("SELECT * FROM Account m ORDER BY m.aid DESC", Account.class);
		return query.getResultList();
	}

	@Transactional(value = TxType.REQUIRED)
	public Account createAnAccount(Account account) {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		return account;
	}

	@Transactional(value = TxType.REQUIRED)
	public Account updateAnAccount(Account account, String firstName, String lastName) {
		em.find(Account.class, account.getaId());

		em.getTransaction().begin();
		account.setFirstName(firstName);
		account.setLastName(lastName);
		em.getTransaction().commit();
		return account;
	}

	@Transactional(value = TxType.REQUIRED)
	public Account deleteAnAccount(Account account) {
		em.find(Account.class, account.getaId());

		em.getTransaction().begin();
		em.remove(account);
		em.getTransaction().commit();
		return em.find(Account.class, account.getaId());
	}

}
