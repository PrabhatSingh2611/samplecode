package digital.windmill.audra.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
@Deprecated//("temporary solution till athentication will be implemented")
public class SecurityService {

    public UUID getCurrentUserUuid() {
        return UUID.fromString("493a5be9-01ba-47c6-95c1-29c230528520");
    }

}
