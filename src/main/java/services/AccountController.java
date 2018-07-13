package services;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import Repositories.AccountRepositorieDBImpl;
import accountapp.Account;

@Path("/account")
public class AccountController {

	@Inject
	private AccountRepositorieDBImpl accountService;
	
	@GET
	public String getAllAccounts() {
		return accountService.findAllAccounts();
	}
	
	@GET @Path("{id}")
	public String getAccount(@PathParam("id") Long id) {
		return accountService.findAnAccount(id);
	}
	
	@DELETE @Path("{id}")
	public String deleteAccount(@PathParam("id") Long id) {
		return accountService.deleteAnAccount(id);
	}
	
	@POST
	public String addAccount(String account) throws IOException {
		return accountService.createAnAccount(account);
	}
	
	@PUT @Path("{id}")
	public String updateAccount(@PathParam("id") Long id, String account) {
		return accountService.updateAnAccount(id, account);
	}
	
}
