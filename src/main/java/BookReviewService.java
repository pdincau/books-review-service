import com.google.common.collect.ImmutableMap;
import com.spotify.apollo.Environment;
import com.spotify.apollo.RequestContext;
import com.spotify.apollo.Response;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;
import okio.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.spotify.apollo.Status.OK;
import static okio.ByteString.encodeUtf8;

public class BookReviewService {

    static final Logger LOG = LoggerFactory.getLogger(BookReviewService.class);

    public static void main(String[] args) throws LoadingException {

        HttpService.boot(BookReviewService::init, "book-review", args);
    }

    static void init(Environment environment) {
        environment.routingEngine()
                .registerAutoRoute(Route.sync("GET", "/books/<id>/", BookReviewService::review))
                .registerAutoRoute(Route.sync("GET", "/ping", context -> "pong"));
    }

    private static Response<ByteString> review(RequestContext context)  {
        LOG.info("Received request to retrieve review");
        String id = context.request().parameter("id").orElse("");
        String body = "{}";
        LOG.info("Review: {}", body);
        return Response.forStatus(OK).withHeaders(headers()).withPayload(encodeUtf8(body));
    }

    private static Map<String, String> headers() {
        return ImmutableMap.<String, String>builder()
                .put("Content-Type", "application/json")
                .put("charset", "utf8")
                .build();
    }
}
