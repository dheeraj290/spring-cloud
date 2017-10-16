package pl.piomin.microservices.account.intercomm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AccountFallback implements AccountClient {

	@Override
	public List<Object> findAll() {
		
		return new ArrayList<Object>();
	}

	

}
