package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BoardSearchDto;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	@Transactional(readOnly = true)
	public Page<Board> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable) {
		return boardRepository.getMainBoardPage(boardSearchDto , pageable);
	}

}
