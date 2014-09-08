package com.tofibashers.imageboard.Repositories;

import com.tofibashers.imageboard.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by TofixXx on 10.08.2014.
 */
public interface BoardRepository extends JpaRepository<Board, String>{
}
