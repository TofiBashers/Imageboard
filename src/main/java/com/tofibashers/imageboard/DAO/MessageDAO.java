package com.tofibashers.imageboard.DAO;

import com.tofibashers.imageboard.Entity.Message;
import com.tofibashers.imageboard.Entity.Thread;
import com.tofibashers.imageboard.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

/**
 * Created by TofixXx on 09.08.2014.
 */
@Repository
@Transactional
public class MessageDAO {

    @Autowired
    private MessageRepository messageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Message> getAllMessages(Integer threadId)
    {
        return entityManager.createQuery("SELECT message FROM Message message " +
                "WHERE message.thread.id = :threadId ORDER BY message.id")
                .setParameter("threadId", threadId)
                .getResultList();
    }

    public List<Message> getMessages(Integer threadId, int messageNum)
    {
        /*
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> messageRoot = criteriaQuery.from(Message.class);
        //может быть ошибка!
        criteriaQuery.where(criteriaBuilder.equal(messageRoot.get("thread.id"), thread.getId()));
        return entityManager.createQuery(criteriaQuery).setMaxResults(messageNum).getResultList();*/
        return entityManager.createQuery("SELECT message FROM Message message " +
                "WHERE message.thread.id = :threadId ORDER BY message.id")
                .setParameter("threadId", threadId).setMaxResults(messageNum)
                .getResultList();
    }

/*
    public void deleteAllMessages(Thread thread)
    {
        entityManager.createQuery("DELETE FROM Message message WHERE message.thread.id = :threadId")
                .setParameter("threadId", thread.getId());
    }
*/
    public Message addMessage(Message message)
    {
        return messageRepository.save(message);
    }

    public void addImagePathAsId(Message message)
    {
        entityManager.createQuery("UPDATE Message message SET message.image_path = :Id " +
                "WHERE message.id = :Id")
                .setParameter("Id", message.getId().toString()).executeUpdate();
    }

    public void setThread(Message mess, Thread thread)
    {
        entityManager.createQuery("UPDATE Message message SET message.thread = :Thread " +
                "WHERE message.id = :messageId").setParameter("messageId", mess.getId())
                .setParameter("Thread", thread).executeUpdate();
    }

    public void deleteMessages(Thread thread)
    {
        Set<Message> messageSet = thread.getMessages();
        messageRepository.delete(messageSet);
    }


}
