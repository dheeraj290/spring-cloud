package pl.piomin.microservices.account.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.microservices.account.intercomm.AccountClient;
import pl.piomin.microservices.account.model.Account;

@RestController
@RefreshScope
public class ApiController {

	@Autowired
	private AccountClient accountClient;
	
	private List<Account> accounts;
	
	protected static Logger logger = LoggerFactory.getLogger(ApiController.class.getName());
	
	@Value("${test-message}")
	private String temp;
	
	public ApiController() {
		accounts = new ArrayList<>();
		accounts.add(new Account(1, 1, "111111"));
		accounts.add(new Account(2, 2, "222222"));
		accounts.add(new Account(3, 3, "333333"));
		accounts.add(new Account(4, 4, "444444"));
		accounts.add(new Account(5, 1, "555555"));
		accounts.add(new Account(6, 2, "666666"));
		accounts.add(new Account(7, 2, "777777"));
	}
	
	@RequestMapping(value = "/accounts/{number}",method = RequestMethod.GET)
	public Account findByNumber(@PathVariable("number") String number) {
		logger.info(String.format("Account.findByNumber(%s)", number));
		Account a = accounts.stream().filter(it -> it.getNumber().equals(number)).findFirst().get();
		logger.info(String.format("Account.findByNumber: %s", a));
		return a;
	}
	
	@RequestMapping(value = "/accounts/customer/{customer}",method = RequestMethod.GET)
	public List<Account> findByCustomer(@PathVariable("customer") Integer customerId) {
		logger.info(String.format("Account.findByCustomer(%s)", customerId));
		List<Account> as = accounts.stream().filter(it -> it.getCustomerId().intValue()==customerId.intValue()).collect(Collectors.toList());
		logger.info(String.format("Account.findByCustomer: %s", as));
		return as;
	}
	
	@RequestMapping(value = "/accounts",method = RequestMethod.GET)
	public List<Account> findAll() {
		System.out.println("the value is ====="+temp);
		logger.info("Account.findAll()");
		logger.info(String.format("Account.findAll: %s", accounts));
		accountClient.findAll();
		return accounts;
	}
	
}
