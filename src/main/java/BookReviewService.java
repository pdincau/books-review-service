import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.spotify.apollo.Environment;
import com.spotify.apollo.RequestContext;
import com.spotify.apollo.Response;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.httpservice.LoadingException;
import com.spotify.apollo.route.Route;
import domain.CommandGetViews;
import domain.EventHandler;
import domain.View;
import domain.ViewRepository;
import infrastructure.persistence.InMemoryViewRepository;
import infrastructure.queue.RabbitMQEventHandler;
import okio.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.spotify.apollo.Status.OK;
import static okio.ByteString.encodeUtf8;

public class BookReviewService {

    static final Logger LOG = LoggerFactory.getLogger(BookReviewService.class);
    private static final ViewRepository repository = InMemoryViewRepository.getInstance();

    public static void main(String[] args) throws LoadingException {
        View view = new View();
        view.setAverageStars(1.5);
        view.setIsbn("anyIsbn");
        repository.updateOrCreate(view);
        EventHandler eventHandler = new RabbitMQEventHandler(repository);
        eventHandler.listen();
        HttpService.boot(BookReviewService::init, "book-review", args);
    }

    static void init(Environment environment) {
        environment.routingEngine()
                .registerAutoRoute(Route.sync("GET", "/books", BookReviewService::review))
                .registerAutoRoute(Route.sync("GET", "/ping", context -> "pong"));
    }

    private static Response<ByteString> review(RequestContext context)  {
        LOG.info("Received request to retrieve review");
        String isbn = context.request().parameter("isbn").orElse("");
        CommandGetViews command = new CommandGetViews(repository, isbn);
        View view = command.execute();
        String body = new Gson().toJson(view);
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
