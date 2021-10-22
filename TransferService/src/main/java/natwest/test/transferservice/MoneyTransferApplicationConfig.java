package natwest.test.transferservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import natwest.test.transferservice.api.TransferServiceAPI;
import natwest.test.transferservice.api.TransferServiceAPIimpl;

@Configuration
public class MoneyTransferApplicationConfig {
	@Bean
	public TransferServiceAPI transferServiceApi() {
		return new TransferServiceAPIimpl();
	}
}