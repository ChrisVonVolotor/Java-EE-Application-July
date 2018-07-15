package services;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import CDI.RepositoryManager;

@Path("/account")
public class AccountController {

	@Inject
	private RepositoryManager accountService;
	
	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return accountService.findAllAccounts();
	}
	
	@GET
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String getAccount(@PathParam("id") Long id) {
		return accountService.findAnAccount(id);
	}
	
	@DELETE @Path("/json/{id}")
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") Long id) {
		return accountService.deleteAnAccount(id);
	}
	
	@POST  @Path("/json")
	@Produces({ "application/json" })
	public String addAccount(String account) throws IOException {
		return accountService.createAnAccount(account);
	}
	
	@PUT
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("id") Long id, String account) {
		return accountService.updateAnAccount(id, account);
	}
	
}
