package com.kuba.demo.Repository;

import com.kuba.demo.Model.Comment;
import com.kuba.demo.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
public interface CommentRepository extends JpaRepository <Comment, Long>
{
    List<Comment> findAllByPostOrderByCreationDateDesc (Post post);
}
