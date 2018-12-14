package com.kuba.demo.Repository;

import com.kuba.demo.Model.Comment;
import com.kuba.demo.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
public interface CommentRepository extends JpaRepository <Comment, Long>
{
    @Query("select c from Comment c order by c.creationDate desc ")
    List<Comment> getOrderedComments();
}
