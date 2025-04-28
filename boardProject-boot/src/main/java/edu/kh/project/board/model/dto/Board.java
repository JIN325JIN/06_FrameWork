package edu.kh.project.board.model.dto;

public class Board {

	
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriteDate;
	private String boardUpdateDate;
	private int readCount;
	private String boardDelFl;
	private int boardCode;
	
	
	
	//MEMBER 테이블 조인
	private String memberNickname;
	
	//목록 조회시 상관쿼리 결과
	private int commnetCount;//댓글수
	private int likeCount;// 좋아요 수
	
	
}
