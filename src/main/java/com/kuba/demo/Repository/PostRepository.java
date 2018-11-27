package com.kuba.demo.Repository;

import com.kuba.demo.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long>
{

}
