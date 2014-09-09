package com.tofibashers.imageboard.Service;

import com.tofibashers.imageboard.Entity.Board;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by TofixXx on 09.09.2014.
 */
public interface ThreadService {
    public void validateThread(Integer threadId);
    public Integer createThread(HttpServletRequest request, Board board, MultipartFile image, String text);
}
