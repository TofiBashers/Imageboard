package com.tofibashers.imageboard.Service;

import com.tofibashers.imageboard.Entity.Board;
import com.tofibashers.imageboard.Entity.Message;

import java.util.List;

/**
 * Created by TofixXx on 09.09.2014.
 */
public interface BoardService {
    public void validateBoard(String boardId);
    public Board getBoardById(String boardId);
    public List<Board> getAllBoards();
    public List<List<Message>> getThread(String boardId);
}
