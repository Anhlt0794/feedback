package feedback.core.repositories;

import org.hibernate.criterion.Criterion;

import java.util.Collection;

/**
 * Created by Anh on 12/19/2023.
 */
public interface EntityChangeRepo {
    <T> Collection<T> queryAll(Class<T> class1);
    <T, E> T getByID(Class<T> class1, E id);
    <T,E> Collection<T> findByCriteria (Class<T> class1, Criterion...ts );
    <T,E> Collection<T> findByExample(Class<T> class1, E exampleInstance, String[] excludeProperty);
    <T> Collection<T> findByCriteria(Class<T> class1,int firstResult, int maxResults,Criterion... criterion) ;
    <T> int countByCriteria(Class<T> class1,Criterion... criterion);
    <T, E> T findReference(Class<T> class1, E id);
    <T> void refresh(T t);
    <T> T save(T t);
    <T> T update(T t);
    <T> void delete(T t);
}
