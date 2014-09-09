package com.tofibashers.imageboard.DAO;

import com.tofibashers.imageboard.Entity.Thread;
import com.tofibashers.imageboard.Repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by TofixXx on 09.08.2014.
 */
@Repository
@Transactional
public class ThreadDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ThreadRepository threadRepository;

    public void deleteThread(Thread thread)
    {
        threadRepository.delete(thread);
    }

    public Thread addThread(Thread thread)
    {return threadRepository.save(thread);}

    public List<Thread> getThreads(String boardId, int threadsNum){
        return entityManager.createQuery("SELECT thread FROM Thread thread " +
                "WHERE thread.board.id = :boardId ORDER BY thread.id")
                .setParameter("boardId", boardId).setMaxResults(threadsNum)
                .getResultList();
    };

    public Thread getThreadById(Integer threadId)
    {
        return (Thread) entityManager.createQuery("SELECT thread FROM Thread thread " +
                "WHERE thread.id = :Id").setParameter("Id", threadId).getSingleResult();
    }


}
