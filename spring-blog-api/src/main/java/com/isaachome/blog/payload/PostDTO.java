package com.isaachome.blog.payload;

import lombok.Data;

/**
 *
 * @author IsaacHome
 */

@Data
public class PostDTO {
	private Long id;
	private String title;
	private String description;
	private String content;
	
}
