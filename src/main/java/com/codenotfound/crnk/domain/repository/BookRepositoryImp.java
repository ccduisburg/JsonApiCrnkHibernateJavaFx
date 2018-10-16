package com.codenotfound.crnk.domain.repository;


import com.codenotfound.crnk.domain.model.Book;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRepositoryImp extends ResourceRepositoryBase<Book, Long> implements BookRepository {

    private SessionFactory sessionFactory;

    protected BookRepositoryImp(Class<Book> resourceClass) {
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



    public BookRepositoryImp() {
        this(Book.class);
    }

    @Override
    public ResourceList<Book> findAll(QuerySpec querySpec) {
        Session session = sessionFactory.openSession();
        List<Book> adress = null;
        adress = session.createQuery("from Book", Book.class).getResultList();
        session.close();
        return querySpec.apply(adress);
    }

    @Override
    public <S extends Book> S save(S s) {
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
    public <S extends Book> S create(S s) {
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
            Book book = session.find(Book.class, id);
            session.delete(book);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}