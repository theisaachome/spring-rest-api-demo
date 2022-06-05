package com.isaachome.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {
    private List<PostDto> contents;
    private int pageNo;
    private  int pageSize;
    private  long totalElements;
    private  int totalPages;
    private boolean last;

}
