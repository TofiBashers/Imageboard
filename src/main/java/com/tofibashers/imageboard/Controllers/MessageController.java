package com.tofibashers.imageboard.Controllers;

import com.tofibashers.imageboard.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by TofixXx on 27.08.2014.
 */
@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    BoardService boardService;




    @RequestMapping(value = "/{boardId}/{threadId}", method = RequestMethod.POST)
    public String addMessage(HttpServletRequest request,
                                   @PathVariable("boardId") String boardId,
                                   @PathVariable("threadId") Integer threadId,
                                   @RequestParam(value = "image") MultipartFile image,
                                   @RequestParam(value = "text") String text)
    {
        boardService.validateBoard(boardId);
        messageService.addMessage(request, threadId, image, text);
        return "redirect:/" + boardId + "/" + threadId;
    }
}
