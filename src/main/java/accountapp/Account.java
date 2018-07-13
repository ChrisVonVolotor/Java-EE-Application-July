package accountapp;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Account {
	@Id @//@GeneratedValue(strategy=GenerationType.AUTO)
	private Long aId;
	@Column(length = 50) @NotNull
	private String firstName;
	@Column(length = 50) @NotNull
	private String lastName;
	
	@OneToMany(mappedBy="account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions;
	
	Account(Long id, String firstName, String lastName){
		this.aId=aId;
		this.firstName=firstName;
		this.lastName=lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName; 
	}

	public Long getaId() {
		return aId;
	}

	public void setaId(Long aId) {
		this.aId = aId;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
