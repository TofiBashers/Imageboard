package com.tofibashers.imageboard.Controllers;

import com.tofibashers.imageboard.DAO.BoardDAO;
import com.tofibashers.imageboard.DAO.MessageDAO;
import com.tofibashers.imageboard.DAO.ThreadDAO;
import com.tofibashers.imageboard.Entity.Board;
import com.tofibashers.imageboard.Entity.Message;
import com.tofibashers.imageboard.Entity.Thread;
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
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by TofixXx on 27.08.2014.
 */
@Controller
public class ThreadController {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    ThreadDAO threadDAO;

    @Autowired
    BoardDAO boardDAO;


    @RequestMapping(value = "/{boardId}/{threadId}")
    public String getThread(Model model,
                                  @PathVariable("boardId") String boardId,
                                  @PathVariable("threadId") Integer threadId)
    {

        List<Message> messages = messageDAO.getAllMessages(threadId);
        model.addAttribute("messageList", messages);
        model.addAttribute("boardId", boardId);
        return "thread";
    }

    @RequestMapping(value = "/{boardId}", method = RequestMethod.POST)
    public String createThread(ModelAndView modelAndView, HttpServletRequest request,
                                     HttpServletResponse response,
                                     @PathVariable("boardId") String BoardId,
                                     @RequestParam(value = "image") MultipartFile image,
                                     @RequestParam(value = "text") String text) throws  IOException{
        //добавить проверку на валидность борды
        Board board = boardDAO.getBoardById(BoardId);

        Thread thread = new Thread();
        Message message = new Message();
        System.out.println(text);
        message.setText(text);
        //1) сохранение сообщения в БД
        Message resMessage = messageDAO.addMessage(message);
        messageDAO.addImagePathAsId(resMessage);
        //2) задание id новому треду, сохранение в БД
        thread.setId(resMessage.getId());
        thread.setBoard(board);
        threadDAO.addThread(thread);
        //3) присвоение поля thread сообщению
        messageDAO.setThread(resMessage, thread);
        resMessage.setImage_path(request.getSession().getServletContext().getRealPath("/appResources/images/") + resMessage.getId().toString());
        System.out.println(resMessage.getImage_path());
        try{
            File file = new File(resMessage.getImage_path());
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            fileOutputStream.write(image.getBytes());
            fileOutputStream.close();
        }
        catch (Exception ex){}
        return "redirect:/" + BoardId + "/" + thread.getId();
    }
}
