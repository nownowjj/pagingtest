package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSearchDto {
	private String searchBy ; // id or name
    private String searchQuery = ""; // 검색 키워드
    private String searchDateType ; // 조회 기간 범위
    private String searchSubject; // 공지사항 제목 검색
}