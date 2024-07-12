package co.com.sofkau.mongo.helper;

public interface MapperGeneric<M, E> {
    M toEntity(E entity);

    E toData(M model);
}
