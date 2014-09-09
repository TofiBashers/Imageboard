package com.tofibashers.imageboard.Service.Implementation;

import com.tofibashers.imageboard.DAO.MessageDAO;
import com.tofibashers.imageboard.DAO.ThreadDAO;
import com.tofibashers.imageboard.Entity.Message;
import com.tofibashers.imageboard.Entity.Thread;
import com.tofibashers.imageboard.Exception.PostingException;
import com.tofibashers.imageboard.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by TofixXx on 08.09.2014.
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    @Autowired
    ThreadDAO threadDAO;

    @Autowired
    MessageDAO messageDAO;

    @Override
    public void addMessage(HttpServletRequest request, Integer threadId, MultipartFile image, String text)
    {
        Thread thread = threadDAO.getThreadById(threadId);

        //2) Задать сообщению тред
        Message message = new Message();
        message.setText(text);
        message.setThread(thread);
        //3) Сохранять сообщение
        Message resMessage = messageDAO.addMessage(message);
        try{
            File file = new File(request.getSession().getServletContext().getRealPath("/appResources/images/") + resMessage.getId().toString());
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            fileOutputStream.write(image.getBytes());
            fileOutputStream.close();
        }
        catch (Exception ex){ throw new PostingException();}
    }

    @Override
    public List<Message> getAllMessages(Integer threadId)
    {
        return messageDAO.getAllMessages(threadId);
    }
}
