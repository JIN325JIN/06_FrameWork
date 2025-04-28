package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.board.model.mapper.BoardMapper;

@Service
@Transactional(rollbackFor=Exception.class)
public class BoardServiceImpl implements BoardService{

	
	
	@Autowired
	private BoardMapper mapper;

	
	
	//게시판 종류 조회 서비스
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		return mapper.selectBoardTypeList();
	}


	//특정 게시판의 지정된 페이지 목록 조회 서비스 
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
