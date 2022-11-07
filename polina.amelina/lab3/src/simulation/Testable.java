package simulation;

public abstract class Testable {

    protected CollectionSupplier[] allowedCollections;

    public CollectionSupplier[] getAllowedCollections() {
        return allowedCollections;
    }

    public abstract void test(CollectionSupplier collectionSupplier);
}