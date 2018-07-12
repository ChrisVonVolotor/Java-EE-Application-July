package accountapp;

import javax.persistence.*;

@Entity
public class Transaction {
	@Id
	private long id;
	private double amount;
	private String reason;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Account account;
	

}
