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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TofixXx on 21.08.2014.
 */
@Controller
public class BoardController {

    @Autowired
    ThreadDAO threadDAO;

    @Autowired
    MessageDAO messageDAO;

    @Autowired
    BoardDAO boardDAO;

@RequestMapping("/")
    public ModelAndView getBoards(ModelAndView modelAndView){
    List<Board> boards = boardDAO.getAllBoards();
    modelAndView.addObject("boards", boards);
    modelAndView.setViewName("index");
    return modelAndView;
}

@RequestMapping(value = "/{boardId}")
    public ModelAndView getBoard(ModelAndView modelAndView,
                                  @PathVariable String boardId)
    {
        System.out.println("boardController");
        List<List<Message>> threadsAndMessages = new ArrayList<List<Message>>();
        List<Thread> threads = threadDAO.getThreads(boardId, 5);
        //List<BufferedImage> images = new ArrayList<BufferedImage>();
        for(Thread thread : threads)
        {
            List<Message> threadMessages = messageDAO.getMessages(thread.getId(), 3);
            /*Message m = threadMessages.get(0);
                if(m.getImage_path() != null)
                {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(m.getImage_path());
                        BufferedImage img = ImageIO.read(fileInputStream);
                        images.add(img);
                    }
                    catch (Exception e){}
                }
                else
                {
                    images.add(null);
                }*/
            threadsAndMessages.add(threadMessages);
        }
        modelAndView.addObject("threadsAndMessages", threadsAndMessages);
        modelAndView.addObject("boardId", boardId);
        //modelAndView.addObject("images", images);
        modelAndView.setViewName("board");
        return modelAndView;
    }

}
