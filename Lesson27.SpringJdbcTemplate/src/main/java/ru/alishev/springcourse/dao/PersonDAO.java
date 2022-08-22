package ru.alishev.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        List<Person> people= session.createQuery("select p from Person p", Person.class).getResultList();
        return people;
    }

    @Transactional
    public Person show(int id) {
        Session session= sessionFactory.getCurrentSession();

        return session.get(Person.class,id);
    }
    @Transactional
    public void save(Person person) {
    Session session= sessionFactory.getCurrentSession();
     session.save(person);
    }
    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session= sessionFactory.getCurrentSession();
        Person person =session.get(Person.class,id);
        person.setEmail(updatedPerson.getEmail());
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());

    }
    @Transactional
    public void delete(int id) {
     Session session = sessionFactory.getCurrentSession();
     session.remove(session.get(Person.class,id));
    }
}
