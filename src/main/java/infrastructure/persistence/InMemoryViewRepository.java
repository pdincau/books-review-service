package infrastructure.persistence;

import domain.View;
import domain.ViewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InMemoryViewRepository implements ViewRepository {

    static final Logger LOG = LoggerFactory.getLogger(InMemoryViewRepository.class);

    private static InMemoryViewRepository instance = null;

    private List<View> views;

    public static InMemoryViewRepository getInstance() {
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
    public void insert(View view) {
        LOG.info("Inserting view: {}", view);
        views.add(view);
    }

    @Override
    public void update(View view) {
        LOG.info("Updating view: {}", view);
        views.removeIf(v -> v.getIsbn().equals(view.getIsbn()));
        views.add(view);
    }
}
