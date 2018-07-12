package accountapp;

import javax.persistence.*;

@Entity
public class Transaction {
	@Id
	private long tId;
	private double amount;
	private String reason;
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="aId")
	private Account account;
	

}
