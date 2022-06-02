package com.isaachome.blog.payload;

import lombok.Data;

/**
 *
 * @author IsaacHome
 */

@Data
public class PostDto {
	private long id;
	private String title;
	private String description;
	private String content;
	
}
