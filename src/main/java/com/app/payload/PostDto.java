package com.app.payload;

import java.util.ArrayList;
import java.util.List;

import com.app.entity.Post;
import com.app.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private String addedDate;
	private UserDto user;

}
