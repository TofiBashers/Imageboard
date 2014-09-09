package com.tofibashers.imageboard.Service.Implementation;

import com.tofibashers.imageboard.DAO.BoardDAO;
import com.tofibashers.imageboard.DAO.MessageDAO;
import com.tofibashers.imageboard.DAO.ThreadDAO;
import com.tofibashers.imageboard.Entity.Board;
import com.tofibashers.imageboard.Entity.Message;
import com.tofibashers.imageboard.Entity.Thread;
import com.tofibashers.imageboard.Exception.ResourceNotFoundException;
import com.tofibashers.imageboard.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TofixXx on 08.09.2014.
 */
@Service
@Transactional
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardDAO boardDAO;

    @Autowired
    ThreadDAO threadDAO;

    @Autowired
    MessageDAO messageDAO;

    @Override
    public void validateBoard(String boardId)
    {
        Board board = boardDAO.getBoardById(boardId);
        if(board == null)
        {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public Board getBoardById(String boardId)
    {
        Board board = boardDAO.getBoardById(boardId);
        if(board == null)
        {
            throw new ResourceNotFoundException();
        }
        return board;
    }

    @Override
    public List<Board> getAllBoards()
    {
        return boardDAO.getAllBoards();
    }

    @Override
    public List<List<Message>> getThread(String boardId)
    {
        List<List<Message>> threadsAndMessages = new ArrayList<List<Message>>();
        List<Thread> threads = threadDAO.getThreads(boardId, 5);
        for(Thread thread : threads)
        {
            List<Message> threadMessages = messageDAO.getMessages(thread.getId(), 3);
            threadsAndMessages.add(threadMessages);
        }
        return threadsAndMessages;
    }
}
