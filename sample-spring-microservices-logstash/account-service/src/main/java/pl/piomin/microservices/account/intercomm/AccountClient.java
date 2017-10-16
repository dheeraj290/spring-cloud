package pl.piomin.microservices.account.intercomm;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@FeignClient(value="customer-service",fallback=AccountFallback.class)
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    List<Object> findAll();
    
}
