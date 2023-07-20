package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Post;
import com.app.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	public List<Post> findByUser(User user);
}
