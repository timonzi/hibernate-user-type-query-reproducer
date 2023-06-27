package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserTypeTestBean {

    @Inject
    EntityManager em;


    @Transactional
    void createEntity() {
        final var entity = new MyEntity();
        entity.setField(new MyStringWrapper("some value"));
        em.persist(entity);
    }

    @Transactional
    List<MyEntity> getByQuery() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MyEntity> query = cb.createQuery(MyEntity.class);
        Root<MyEntity> root = query.from(MyEntity.class);

        query.where(
                cb.equal(cb.upper(root.get("field")), new MyStringWrapper("SOME VALUE"))
        );
        TypedQuery<MyEntity> result = em.createQuery(query);
        return result.getResultList();
    }

}
