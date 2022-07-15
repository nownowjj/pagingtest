package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.BoardSearchDto;
import com.example.demo.entity.Board;

@Transactional
public interface BoardRepositoryCustom {
	Page<Board> getMainBoardPage(BoardSearchDto boardSearchDto , Pageable pageable);

}
