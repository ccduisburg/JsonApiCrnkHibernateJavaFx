package com.codenotfound.crnk.domain.repository;


import com.codenotfound.crnk.domain.model.BookCategory;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.list.ResourceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookCategoryRepositoryImpl  extends ResourceRepositoryBase<BookCategory, Long> implements BookCategoryRepository {

    private SessionFactory sessionFactory;

    protected BookCategoryRepositoryImpl(Class<BookCategory> resourceClass) {
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



    public BookCategoryRepositoryImpl() {
        this(BookCategory.class);
    }

    @Override
    public ResourceList<BookCategory> findAll(QuerySpec querySpec) {
        Session session = sessionFactory.openSession();
        List<BookCategory> bookCategory = null;
        bookCategory = session.createQuery("from BookCategory", BookCategory.class).getResultList();
        session.close();
        return querySpec.apply(bookCategory);
    }

    @Override
    public <S extends BookCategory> S save(S s) {
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
    public <S extends BookCategory> S create(S s) {
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
            BookCategory bookCategory = session.find(BookCategory.class, id);
            session.delete(bookCategory);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}