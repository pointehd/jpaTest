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
	
	public BoardResponse createBoard(String content) {
		log.info("create content {}", content);
		Board board = boardRepository.save(Board.builder().content(content).build());
		return board.toBoardResponse();
	}
	
	public BoardResponse updateBoard(BoardRequest request) {
		Board board = boardRepository.findById(request.getSeq()).orElseThrow(()->new RuntimeException("notFoundBoard"));
		boardRepository.save(board.contentUpdate(request.getContent()));
		return board.toBoardResponse();
	}
	
	public void deleteBoard(Long seq) {
		boardRepository.deleteById(seq);
	}
	
	public List<BoardResponse> getAllBoard(){
		return boardRepository.findAll().stream().map(Board::toBoardResponse).collect(Collectors.toList());
	}
}
