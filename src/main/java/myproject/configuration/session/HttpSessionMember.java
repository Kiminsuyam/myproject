package myproject.configuration.session;

import myproject.dto.SessionMember;
import org.springframework.stereotype.Component;

@Component
public class HttpSessionMember extends AbstractHttpSession<SessionMember> {

	@Override
	protected String name() {
		return null;


	}
}
