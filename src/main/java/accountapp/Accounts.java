package accountapp;

import javax.persistence.*;

public class Accounts {
	@Id //@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(length = 50)
	private String firstName;
	@Column(length = 50)
	private String lastName;

}
