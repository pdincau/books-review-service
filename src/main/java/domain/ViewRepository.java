package domain;

public interface ViewRepository {

    View findBy(String isbn);

    void insert(View view);

    void update(View view);
}
