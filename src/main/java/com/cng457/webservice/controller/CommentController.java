package com.cng457.webservice.controller;

import java.util.List;

import com.cng457.webservice.entity.Comment;
import com.cng457.webservice.entity.ItemNotFoundException;
import com.cng457.webservice.repository.ICommentRepository;
import com.cng457.webservice.repository.IPCRepository;
import com.cng457.webservice.repository.IPhoneRepository;
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

    @Autowired
    private final IPhoneRepository phoneRepository;

    CommentController(ICommentRepository repository, IPCRepository pcRepository, IPhoneRepository phoneRepository) {
        this.repository = repository;
        this.pcRepository = pcRepository;
        this.phoneRepository = phoneRepository;
    }

    /**
     * Retrieves and returns a list of available comments in the system.
     * @return
     */
    @GetMapping("/comments")
    List<Comment> all() {
        return repository.findAll();
    }


    /**
     * Retrieves and returns the comment with given id.
     * @param commentId
     * @return Comment object.
     */
    @GetMapping("/comments/{commentId}")
    Comment one(@PathVariable Long commentId) {
        return repository.findById(commentId).orElseThrow(() -> new ItemNotFoundException(commentId));
    }

    /**
     * Retrieves and returns a list of available computer comments for given product id.
     * @param productId
     * @return List of Comment objects.
     */
    @GetMapping("/computers/{productId}/comments")
    List<Comment> findCommentsById(@PathVariable Long productId) {
        return repository.findByProductId(productId);
    }

    /**
     * Creates a new comment for a computer with given productId.
     * @param productId
     * @param comment
     * @return Comment object.
     */
    @PostMapping("/computers/{productId}/comments/add")
    Comment addComment(@PathVariable Long productId, @RequestBody Comment comment) {
        return pcRepository.findById(productId).map(product -> {
            comment.setProduct(product);
            return repository.save(comment);
        }).orElseThrow(() -> new ItemNotFoundException(productId));
    }

    /**
     * Retrieves and returns a list of available phone comments for given product id.
     * @param productId
     * @return List of Comment objects.
     */
    @GetMapping("/phones/{productId}/comments")
    List<Comment> findPhoneCommentsById(@PathVariable Long productId) {
        return repository.findByProductId(productId);
    }

    /**
     * Creates a new comment for a phone with given productId.
     * @param productId
     * @param comment
     * @return Comment object.
     */
    @PostMapping("/phones/{productId}/comments/add")
    Comment addPhoneComment(@PathVariable Long productId, @RequestBody Comment comment) {
        return phoneRepository.findById(productId).map(product -> {
            comment.setProduct(product);
            return repository.save(comment);
        }).orElseThrow(() -> new ItemNotFoundException(productId));
    }
}