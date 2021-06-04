package com.cng457.webservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cng457.webservice.entity.Comment;
import com.cng457.webservice.entity.PC;
import com.cng457.webservice.entity.ItemNotFoundException;
import com.cng457.webservice.repository.ICommentRepository;
import com.cng457.webservice.repository.IPCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CommentController {

    @Autowired
    private final ICommentRepository repository;

    @Autowired
    private final IPCRepository pcRepository;

    CommentController(ICommentRepository repository, IPCRepository pcRepository) {
        this.repository = repository;
        this.pcRepository = pcRepository;
    }

    @GetMapping("/comments")
    List<Comment> all() {
        return repository.findAll();
    }

    @GetMapping("/comments/{commentId}")
    Comment one(@PathVariable Long commentId) {
        return repository.findById(commentId).orElseThrow(() -> new ItemNotFoundException(commentId));
    }

    @GetMapping("/computers/{productId}/comments")
    List<Comment> findCommentsById(@PathVariable Long productId) {
        return repository.findByProductId(productId);
    }

    @PostMapping("/computers/{productId}/comments/add")
    Comment addComment(@PathVariable Long productId, @RequestBody Comment comment) {
        return pcRepository.findById(productId).map(product -> {
            comment.setProduct(product);
            return repository.save(comment);
        }).orElseThrow(() -> new ItemNotFoundException(productId));
    }

}