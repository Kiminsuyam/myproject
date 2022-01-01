package myproject.configuration.session;

import myproject.dto.SessionNiceAuth;
import org.springframework.stereotype.Component;

@Component
public class HttpSessionNiceAuth extends AbstractHttpSession<SessionNiceAuth>{

	@Override
	protected String name() {
		return "SESSION_NICEAUTH";
	}
}
