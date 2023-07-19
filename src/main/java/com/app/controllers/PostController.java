package com.app.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.payload.ApiResponse;
import com.app.payload.PostDto;
import com.app.service.FileService;
import com.app.service.PostService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/post")
@CrossOrigin
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String imagePath;
	
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
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image , 
				@PathVariable("postId") Integer postId) throws Exception{
		PostDto dto = this.postService.getPostById(postId);
		String name = this.fileService.uploadImage(imagePath, image);
		dto.setImageName(name);
		PostDto updatePost = this.postService.updatePost(dto, postId);
		return new ResponseEntity<PostDto>(updatePost , HttpStatus.OK);
	}
	@GetMapping(value = "/image/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable("imageName") String imageName , HttpServletResponse response) throws IOException {
		
		InputStream resource = this.fileService.getImageResource(imagePath, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
	

}
