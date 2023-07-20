package com.app.service;

import java.util.List;

import com.app.payload.PostDto;

public interface PostService {
	// create Post
	public PostDto createPost(PostDto dto , Integer userId);
	// get all Post
	public List<PostDto> getAllPost();
	public PostDto getPostById(int id);
	public PostDto updatePost(PostDto dto ,int id);
	//public PostDto updatePostWithUser(PostDto dto , int id);
	public void deletePost(int id);
	public List<PostDto> getPostByUser(int id);
}
