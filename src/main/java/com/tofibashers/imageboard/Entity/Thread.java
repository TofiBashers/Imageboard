package com.tofibashers.imageboard.Entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by TofixXx on 08.08.2014.
 */
@Entity
@Table(name="threads")
public class Thread implements Serializable {
    @Id
    private Integer id;

    @OneToMany(mappedBy="thread", cascade = CascadeType.ALL)
    private Set<Message> messages;

    @ManyToOne
    @JoinColumn
    private Board board;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
