public interface ViewRepository {

    View findBy(String isbn);

    void save(View view);
}
