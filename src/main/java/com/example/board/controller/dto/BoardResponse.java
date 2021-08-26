package com.example.board.controller.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BoardResponse {
	Long seq;
	String content;
	LocalDateTime createDt;
}
