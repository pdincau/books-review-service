package domain;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandGetViews extends HystrixCommand<View> {

    private final ViewRepository repository;
    private final String isbn;

    static final Logger LOG = LoggerFactory.getLogger(CommandGetViews.class);

    public CommandGetViews(ViewRepository repository, String isbn) {
        super(HystrixCommandGroupKey.Factory.asKey("BookReviewGroup"));
        this.repository = repository;
        this.isbn = isbn;
    }

    @Override
    protected View run() {
        LOG.info("Invoking command to retrieve view");
        return repository.findBy(isbn);
    }

    @Override
    protected View getFallback() {
        LOG.info("Using fallback while applying command to retrieve view");
        return new View();
    }
}
