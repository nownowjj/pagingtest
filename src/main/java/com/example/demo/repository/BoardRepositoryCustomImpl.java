package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.sound.midi.Soundbank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.example.demo.dto.BoardSearchDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.QBoard;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;


public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
	private JPAQueryFactory queryFactory;
	
	public BoardRepositoryCustomImpl(EntityManager em){ this.queryFactory = new JPAQueryFactory(em);}
	
	@Override
	public Page<Board> getMainBoardPage(BoardSearchDto boardSearchDto, Pageable pageable) {
		System.out.println("======================== BoardBoardRepositoryCustomImpl");
		System.out.println(boardSearchDto.getSearchQuery()+ "검색 값");

        QueryResults<Board> results = this.queryFactory
                .selectFrom(QBoard.board)
                .where(
                		searchByLike(boardSearchDto.getSearchBy() , boardSearchDto.getSearchQuery()))
                .orderBy(QBoard.board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Board> content = results.getResults();
        System.out.println("===================== content.size() ");
        System.out.println("content.size() : " + content.size());
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
	}
	
    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
    	System.out.println(searchBy+searchQuery + "=====================");
        if(StringUtils.equals("subject", searchBy)){
            return QBoard.board.subject.like("%" + searchQuery + "%") ;

        }else if(StringUtils.equals("contents", searchBy)){
            return QBoard.board.contents.like("%" + searchQuery + "%") ;
        }
        return null ;
    }
}
