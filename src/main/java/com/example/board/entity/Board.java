package com.example.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.example.board.controller.dto.BoardResponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long seq;
	
	String content;
	
	@CreationTimestamp
	LocalDateTime createDt;
	
	@Builder
	public Board(String content) {
		this.content = content;
	}
	
	public Board contentUpdate(String content) {
		this.content = content;
		return this;
	}
	
	public BoardResponse toBoardResponse() {
		return BoardResponse.builder().seq(seq).content(content).createDt(createDt).build(); 
	}
}
