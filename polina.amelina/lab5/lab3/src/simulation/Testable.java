package simulation;

public abstract class Testable {

    protected IntegerCollectionSupplier[] allowedCollections;

    public IntegerCollectionSupplier[] getAllowedCollections() {
        return allowedCollections;
    }

    public abstract void test(IntegerCollectionSupplier collectionSupplier);
}