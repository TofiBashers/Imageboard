package com.tofibashers.imageboard.DAO;

import com.tofibashers.imageboard.Entity.Board;
import com.tofibashers.imageboard.Repositories.BoardRepository;
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
public class BoardDAO {

    @Autowired
    BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Board> getAllBoards()
    {
        return boardRepository.findAll();
    }

    public Board getBoardById(String BoardId)
    {
        return (Board) entityManager.createQuery("SELECT board FROM Board board " +
                "WHERE board.id = :Id").setParameter("Id", BoardId).getSingleResult();
    }
}
