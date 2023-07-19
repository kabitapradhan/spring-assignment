package com.app.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Post;
import com.app.entity.User;
import com.app.payload.PostDto;
import com.app.payload.UserDto;
import com.app.repositories.PostRepository;
import com.app.repositories.UserRepository;
import com.app.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepo;
	@Autowired 
	private UserRepository userRepo;
	@Autowired
	private ModelMapper mapper;
	@Override
	public PostDto createPost(PostDto dto , Integer id) {
		User user = this.userRepo.findById(id).get();
		Post post = this.mapper.map(dto, Post.class);
		post.setUser(user);
		post.setAddedDate(new Date());
		post.setImageName("default.png");
		Post save = this.postRepo.save(post);
		return this.mapper.map(save, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> list = this.postRepo.findAll();
		List<PostDto> all = list.stream()
				.map(mp-> this.mapper.map(mp, PostDto.class))
				.collect(Collectors.toList());
		return all;
	}

	@Override
	public PostDto getPostById(int id) {
		Post post = this.postRepo.findById(id).get();
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto , int postId) {
		Post post = this.postRepo.findById(postId).get();
		post.setContent(dto.getContent());
		post.setImageName(dto.getImageName());
		post.setTitle(dto.getTitle());
		Post save = this.postRepo.save(post);
		return this.mapper.map(save, PostDto.class);
	}
	

	@Override
	public void deletePost(int id) {
		Post post = this.postRepo.findById(id).get();
		this.postRepo.delete(post);
	}

}
