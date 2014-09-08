package com.tofibashers.imageboard.Controllers;

import com.tofibashers.imageboard.DAO.BoardDAO;
import com.tofibashers.imageboard.DAO.MessageDAO;
import com.tofibashers.imageboard.DAO.ThreadDAO;
import com.tofibashers.imageboard.Entity.Board;
import com.tofibashers.imageboard.Entity.Message;
import com.tofibashers.imageboard.Entity.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by TofixXx on 27.08.2014.
 */
@Controller
public class MessageController {

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    ThreadDAO threadDAO;

    @Autowired
    BoardDAO boardDAO;

    @RequestMapping(value = "/{boardId}/{threadId}", method = RequestMethod.POST)
    public String addMessage(HttpServletRequest request,
                                   @PathVariable("boardId") String boardId,
                                   @PathVariable("threadId") Integer threadId,
                                   @RequestParam(value = "image") MultipartFile image,
                                   @RequestParam(value = "text") String text)
    {
        //добавить проверку на валидность борды
        Board board = boardDAO.getBoardById(boardId);
        //и треда

        //1) Получить тред с соотв. id
        Thread thread = threadDAO.getThreadById(threadId);

        //2) Задать сообщению тред
        Message message = new Message();
        message.setText(text);
        message.setThread(thread);
        //3) Сохранять сообщение
        Message resMessage = messageDAO.addMessage(message);
        messageDAO.addImagePathAsId(resMessage);
        resMessage.setImage_path(request.getSession().getServletContext().getRealPath("/appResources/images/") + resMessage.getId().toString());
        try{
            File file = new File(resMessage.getImage_path());
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            fileOutputStream.write(image.getBytes());
            fileOutputStream.close();
        }
        catch (Exception ex){}
        return "redirect:/" + boardId + "/" + threadId;
    }
}
