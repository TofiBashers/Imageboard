package com.tofibashers.imageboard.Repository;

import com.tofibashers.imageboard.Entity.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TofixXx on 10.08.2014.
 */
public interface ThreadRepository extends JpaRepository<Thread, Integer> {
}
