package no.cantara.sse;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.inject.Inject;
import no.cantara.stingray.application.StingrayLogging;
import no.cantara.stingray.security.authentication.test.FakeStingrayAuthorization;
import no.cantara.stingray.test.StingrayTestClient;
import no.cantara.stingray.test.StingrayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(StingrayTestExtension.class)
public class RandomizerTest {

    private static final String contextPath = "serversentevents";
    static {
        StingrayLogging.init();
    }

    private static final Logger log = LoggerFactory.getLogger(RandomizerTest.class);

    @Inject
    StingrayTestClient testClient;

    @Test
    public void thatViewerCanDoAllExceptReseed() {
        testClient.useAuthorization(FakeStingrayAuthorization.application().applicationId("junit-viewer").addAccessGroup("viewers").build());
        log.info("GET /" + contextPath + "/str/10 Response: {}", testClient.get().path("/" + contextPath + "/str/10").execute().expect200Ok().contentAsString());
        log.info("GET /" + contextPath + "/int/1000 Response: {}", testClient.get().path("/" + contextPath + "/int/1000").execute().expect200Ok().contentAsString());
        log.info("GET /" + contextPath + "/health Response: {}", testClient.get().path("/" + contextPath + "/health").execute().expect200Ok().contentAsType(JsonNode.class).toPrettyString());
        testClient.put().path("/" + contextPath + "/seed/12345").execute().expect403Forbidden();
    }

    @Test
    public void thatAdminCanDoAll() {
        testClient.useAuthorization(FakeStingrayAuthorization.application().applicationId("junit-admin").addAccessGroup("admins").build());
        log.info("GET /" + contextPath + "/str/10 Response: {}", testClient.get().path("/" + contextPath + "/str/10").execute().expect200Ok().contentAsString());
        log.info("GET /" + contextPath + "/int/1000 Response: {}", testClient.get().path("/" + contextPath + "/int/1000").execute().expect200Ok().contentAsString());
        log.info("GET /" + contextPath + "/health Response: {}", testClient.get().path("/" + contextPath + "/health").execute().expect200Ok().contentAsType(JsonNode.class).toPrettyString());
        testClient.put().path("/" + contextPath + "/seed/12345").execute().expect200Ok();
    }

    @Test
    public void thatOpenApiWorks() {
        String openApiYaml = testClient.get().path("/" + contextPath + "/openapi.yaml").execute().expect200Ok().contentAsString();
        log.info("/" + contextPath + "/openapi.yaml:\n{}", openApiYaml);
    }
}