package com.tofibashers.imageboard.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tofibashers.imageboard.Entity.Thread;

/**
 * Created by TofixXx on 10.08.2014.
 */
public interface ThreadRepository extends JpaRepository<Thread, Integer> {
}
