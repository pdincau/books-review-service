package domain;

public interface ViewRepository {

    View findBy(String isbn);

    void updateOrCreate(View view);
}
