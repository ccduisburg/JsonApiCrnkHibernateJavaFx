package com.codenotfound.crnk.domain.repository;


import com.codenotfound.crnk.domain.model.Address;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressRepositoryImpl extends ResourceRepositoryBase<Address, Long> implements AddressRepository {

    private SessionFactory sessionFactory;

    protected AddressRepositoryImpl(Class<Address> resourceClass) {
        super(resourceClass);
        initSessionFactory();
    }

    private void initSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private void closeSessionFactory() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }



    public AddressRepositoryImpl() {
        this(Address.class);
    }

    @Override
    public ResourceList<Address> findAll(QuerySpec querySpec) {
        Session session = sessionFactory.openSession();
        List<Address> libraries = session.createQuery("from Address", Address.class).getResultList();
        session.close();
        return querySpec.apply(libraries);
    }

    @Override
    public <S extends Address> S save(S s) {
        Session session = sessionFactory.openSession();
        S ret = null;
        try {
            session.getTransaction().begin();
            session.save(s);
            session.getTransaction().commit();
            ret = s;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return ret;
    }

    @Override
    public <S extends Address> S create(S s) {
        Session session = sessionFactory.openSession();
        S ret = null;
        try {
            session.getTransaction().begin();
            session.persist(s);
            session.getTransaction().commit();
            ret = s;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return ret;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            Address Address = session.find(com.codenotfound.crnk.domain.model.Address.class, id);
            session.delete(Address);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}