package com.blog.payloads;

public class CommentDto {
	
	
    private Integer id;
	

	private String content;
	

	
	//private Post post;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	



	public CommentDto(Integer id, String content) {
		super();
		this.id = id;
		this.content = content;
	
	}



	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	
	
	
	

}
