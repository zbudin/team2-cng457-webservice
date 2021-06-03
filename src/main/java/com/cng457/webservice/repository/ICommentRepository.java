package com.cng457.webservice.repository;

import com.cng457.webservice.entity.Comment;
import com.cng457.webservice.entity.Product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Sort;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByProduct(@Param("computer") Product product, Sort sort);
}