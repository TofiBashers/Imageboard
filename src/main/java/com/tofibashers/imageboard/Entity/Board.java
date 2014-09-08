package com.tofibashers.imageboard.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by TofixXx on 08.08.2014.
 */
@Entity
@Table(name="boards")
public class Board implements Serializable {
    @Id
    private String name;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private Set<Thread> threads;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Thread> getThreads() {
        return threads;
    }

    public void setThreads(Set<Thread> threads) {
        this.threads = threads;
    }
}
