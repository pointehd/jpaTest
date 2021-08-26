package com.example.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.controller.dto.BoardRequest;
import com.example.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping
	public ResponseEntity<?> getBoardList() {
		return ResponseEntity.ok(boardService.getAllBoard());
	}
	
	@PostMapping
	public ResponseEntity<?> postBoad(@RequestBody BoardRequest request ) {
		System.out.println(request.toString());
		return ResponseEntity.ok(boardService.createBoard(request.getContent()));
	}
	
	@PutMapping
	public ResponseEntity<?> putEditBoard(@RequestBody BoardRequest request) {
		return ResponseEntity.ok(boardService.updateBoard(request));
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteBoard(@RequestBody BoardRequest request) {
		boardService.deleteBoard(request.getSeq());
		return ResponseEntity.ok(null);
	}
}
