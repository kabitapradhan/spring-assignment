package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.ApiResponse;
import com.app.payload.PostDto;
import com.app.service.PostService;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
	@Autowired
	private PostService postService;
	
	// create or add Post into database
	@PostMapping("/user/{userId}")
	public ResponseEntity<PostDto> addPost(@RequestBody PostDto dto , @PathVariable("userId") Integer userId ) {
		PostDto createPost = this.postService.createPost(dto , userId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	// get all Post from database
	@GetMapping("/")
	public ResponseEntity<List<PostDto>> getAllPost() {
		List<PostDto> list = this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);
	}
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId ) {
		PostDto createPost = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.OK);
	}
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable("postId") Integer postId ) {
		this.postService.deletePost(postId);
		ApiResponse api = new ApiResponse();
		api.setMessage("Post deleteed succsessfully");
		api.setSuccess(true);
		return new ResponseEntity<ApiResponse>(api , HttpStatus.OK);
		
	}
	@PostMapping("/{postId}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto dto,@PathVariable("postId") Integer postId ) {
		PostDto updatePost = this.postService.updatePost(dto, postId);
		return new ResponseEntity<PostDto>(updatePost , HttpStatus.OK);
		
	}
	
	

}
