package com.tofibashers.imageboard.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by TofixXx on 08.08.2014.
 */
@Entity
@Table(name="messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //доделать, чтобы ParentID было несколько
    private Integer parent_id;
    private String text;

    private String image_path;

    @ManyToOne
    @JoinColumn
    private Thread thread;

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
