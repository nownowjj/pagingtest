package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.BoardSearchDto;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final BoardService boardService;
	
	@GetMapping(value = {"/","/{page}"})
	public String main(){
		
		return "index";
	}
	
	@GetMapping(value= {"/board","/board/{page}"})
	public String goboard(BoardSearchDto boardSearchDto ,Model model , @PathVariable("page") Optional<Integer> page){
		System.out.println(boardSearchDto.getSearchQuery());
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 ,3);
		Page<Board> datas = boardService.getBoardPage(boardSearchDto, pageable);
		
		model.addAttribute("datas", datas);
		model.addAttribute("boardSearchDto", boardSearchDto);
		model.addAttribute("maxPage", 5); // 하단에 보여줄 페이지 번호의 최대 개수
		
		return "/board/board";
	}
	
}
