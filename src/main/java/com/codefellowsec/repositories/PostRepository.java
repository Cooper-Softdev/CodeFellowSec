package com.codefellowsec.repositories;
import com.codefellowsec.models.DoPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<DoPost, Long> {

}