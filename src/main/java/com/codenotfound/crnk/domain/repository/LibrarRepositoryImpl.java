package com.codenotfound.crnk.domain.repository;


import com.codenotfound.crnk.domain.model.Library;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibrarRepositoryImpl extends ResourceRepositoryBase<Library, Long> implements LibraryRepository {

    private SessionFactory sessionFactory;

    protected LibrarRepositoryImpl(Class<Library> resourceClass) {
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



    public LibrarRepositoryImpl() {
        this(Library.class);
    }

    @Override
    public ResourceList<Library> findAll(QuerySpec querySpec) {
        Session session = sessionFactory.openSession();
        List<Library> libraries = session.createQuery("from Library", Library.class).getResultList();
        session.close();
        return querySpec.apply(libraries);
    }

    @Override
    public <S extends Library> S save(S s) {
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
    public <S extends Library> S create(S s) {
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
            Library Library = session.find(com.codenotfound.crnk.domain.model.Library.class, id);
            session.delete(Library);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}