package com.tofibashers.imageboard.Service.Implementation;

import com.tofibashers.imageboard.DAO.MessageDAO;
import com.tofibashers.imageboard.DAO.ThreadDAO;
import com.tofibashers.imageboard.Entity.Board;
import com.tofibashers.imageboard.Entity.Message;
import com.tofibashers.imageboard.Entity.Thread;
import com.tofibashers.imageboard.Exception.PostingException;
import com.tofibashers.imageboard.Exception.ResourceNotFoundException;
import com.tofibashers.imageboard.Service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by TofixXx on 08.09.2014.
 */
@Service
@Transactional
public class ThreadServiceImpl implements ThreadService{

    @Autowired
    ThreadDAO threadDAO;

    @Autowired
    MessageDAO messageDAO;

    @Override
    public void validateThread(Integer threadId)
    {
        if(threadDAO.getThreadById(threadId) == null)
        {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public Integer createThread(HttpServletRequest request, Board board, MultipartFile image, String text)
    {
        Thread thread = new Thread();
        Message message = new Message();
        message.setText(text);
        //1) сохранение сообщения в БД
        Message resMessage = messageDAO.addMessage(message);
        //2) задание id новому треду, сохранение в БД
        thread.setId(resMessage.getId());
        thread.setBoard(board);
        threadDAO.addThread(thread);
        //3) присвоение поля thread сообщению
        messageDAO.setThread(resMessage, thread);
        try{
            File file = new File(request.getSession().getServletContext().getRealPath("/appResources/images/") + resMessage.getId().toString());
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            fileOutputStream.write(image.getBytes());
            fileOutputStream.close();
        }
        catch (Exception ex){ throw new PostingException();}
        return thread.getId();
    }
    //м.б в MessageService

}
