package com.tofibashers.imageboard.Controllers;

import com.tofibashers.imageboard.Entity.Board;
import com.tofibashers.imageboard.Entity.Message;
import com.tofibashers.imageboard.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by TofixXx on 21.08.2014.
 */
@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

@RequestMapping("/")
    public ModelAndView getBoards(ModelAndView modelAndView){
    List<Board> boards = boardService.getAllBoards();
    modelAndView.addObject("boards", boards);
    modelAndView.setViewName("index");
    return modelAndView;
}

@RequestMapping(value = "/{boardId}")
    public ModelAndView getBoard(ModelAndView modelAndView,
                                  @PathVariable String boardId)
    {
        List<List<Message>> threadsAndMessages = boardService.getThread(boardId);
        modelAndView.addObject("threadsAndMessages", threadsAndMessages);
        modelAndView.addObject("boardId", boardId);
        modelAndView.setViewName("board");
        return modelAndView;
    }

}
