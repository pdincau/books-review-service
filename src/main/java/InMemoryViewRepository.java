import java.util.ArrayList;
import java.util.List;

public class InMemoryViewRepository implements ViewRepository {

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
        View view = views.stream()
                .filter(v -> isbn.equals(v.getIsbn()))
                .findFirst()
                .orElse(new View());
        return view;
    }

    @Override
    public void save(View view) {
        views.add(view);
    }
}
