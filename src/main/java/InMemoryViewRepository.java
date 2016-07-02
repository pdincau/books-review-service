import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InMemoryViewRepository implements ViewRepository {

    static final Logger LOG = LoggerFactory.getLogger(InMemoryViewRepository.class);

    private static InMemoryViewRepository instance = null;

    private List<View> views;

    public static ViewRepository getInstance() {
        if (instance == null) {
            return new InMemoryViewRepository();
        }
        return instance;
    }

    public InMemoryViewRepository() {
        this.views = new ArrayList<>();
    }

    @Override
    public View findBy(String isbn) {
        LOG.info("Recovering view with isbn: {}", isbn);

        View view = views.stream()
                .filter(v -> isbn.equals(v.getIsbn()))
                .findFirst()
                .orElse(new View());
        return view;
    }

    @Override
    public void updateOrCreate(View view) {
        LOG.info("Saving view: {}", view);
        views.add(view);
    }
}
