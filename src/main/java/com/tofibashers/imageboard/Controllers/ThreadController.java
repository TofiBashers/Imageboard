package com.tofibashers.imageboard.Controllers;

import com.tofibashers.imageboard.Entity.Board;
import com.tofibashers.imageboard.Entity.Message;
import com.tofibashers.imageboard.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by TofixXx on 27.08.2014.
 */
@Controller
public class ThreadController {

    @Autowired
    BoardService boardService;

    @Autowired
    ThreadService threadService;

    @Autowired
    MessageService messageService;


    @RequestMapping(value = "/{boardId}/{threadId}")
    public String getThread(Model model,
                                  @PathVariable("boardId") String boardId,
                                  @PathVariable("threadId") Integer threadId)
    {

        List<Message> messages = messageService.getAllMessages(threadId);
        model.addAttribute("messageList", messages);
        model.addAttribute("boardId", boardId);
        return "thread";
    }

    @RequestMapping(value = "/{boardId}", method = RequestMethod.POST)
    public String createThread(ModelAndView modelAndView, HttpServletRequest request,
                                     @PathVariable("boardId") String BoardId,
                                     @RequestParam(value = "image") MultipartFile image,
                                     @RequestParam(value = "text") String text) throws  IOException{
        //добавить проверку на валидность борды
        Board board = boardService.getBoardById(BoardId);
        Integer threadId = threadService.createThread(request, board, image, text);
        return "redirect:/" + BoardId + "/" + threadId;
    }
}
