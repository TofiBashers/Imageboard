package com.tofibashers.imageboard.Repositories;

import com.tofibashers.imageboard.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TofixXx on 09.08.2014.
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
