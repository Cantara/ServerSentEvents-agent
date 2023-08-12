package no.cantara.sse;

import no.cantara.config.ApplicationProperties;
import no.cantara.stingray.application.StingrayApplication;
import no.cantara.stingray.application.StingrayApplicationFactory;
public class ServerSentEventsApplicationFactory implements StingrayApplicationFactory<ServerSentEventsApplication>{
    @Override
    public Class<?> providerClass() {
        return ServerSentEventsApplication.class;
    }

    @Override
    public String alias() {
        return "ServerSentEvents";
    }

    @Override
    public StingrayApplication<ServerSentEventsApplication> create(ApplicationProperties applicationProperties) {
        return new ServerSentEventsApplication(applicationProperties);
    }
}
