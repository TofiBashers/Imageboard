package com.tofibashers.imageboard.Service;

import com.tofibashers.imageboard.Entity.Message;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by TofixXx on 09.09.2014.
 */
public interface MessageService {
    public void addMessage(HttpServletRequest request, Integer threadId, MultipartFile image, String text);
    public List<Message> getAllMessages(Integer threadId);
}
