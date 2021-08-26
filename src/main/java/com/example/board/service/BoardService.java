package com.example.board.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.controller.dto.BoardRequest;
import com.example.board.controller.dto.BoardResponse;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	
	// 생성
	public BoardResponse createBoard(String content) {
		log.info("create content {}", content);
		Board board = boardRepository.save(Board.builder().content(content).build());
		return board.toBoardResponse();
	}
	
	// BOARD 정보 변경
	public BoardResponse updateBoard(BoardRequest request) {
		Board board = boardRepository.findById(request.getSeq()).orElseThrow(()->new RuntimeException("notFoundBoard"));
		boardRepository.save(board.contentUpdate(request.getContent()));
		return board.toBoardResponse();
	}
	
	// BOARD 삭제 (실제 삭제됨)
	public void deleteBoard(Long seq) {
		boardRepository.deleteById(seq);
	}
	
	// 전체조회
	public List<BoardResponse> getAllBoard(){
		return boardRepository.findAll().stream().map(Board::toBoardResponse).collect(Collectors.toList());
	}
}
