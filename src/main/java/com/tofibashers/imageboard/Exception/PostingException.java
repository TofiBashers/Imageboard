package com.tofibashers.imageboard.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by TofixXx on 08.09.2014.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PostingException extends RuntimeException {
}
