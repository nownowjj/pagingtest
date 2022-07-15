package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.demo.entity.Board;


@Transactional
public interface BoardRepository extends JpaRepository<Board, Long> ,QuerydslPredicateExecutor<Board>,BoardRepositoryCustom  {

//	Board findBoardById(Long id);

}
