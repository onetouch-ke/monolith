package com.example.boards.controller;

import com.example.boards.dto.BoardReqDto;
import com.example.boards.entity.BoardEntity;
import com.example.boards.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 전체 게시글 조회
    @GetMapping
    public ResponseEntity<List<BoardEntity>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    // 단일 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardEntity> getBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    // 게시글 작성
    @PostMapping
    public ResponseEntity<BoardEntity> createBoard(@RequestBody BoardReqDto requestDto) {
        return ResponseEntity.ok(boardService.createBoard(requestDto));
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<BoardEntity> updateBoard(@PathVariable Long id, @RequestBody BoardReqDto requestDto) {
        return ResponseEntity.ok(boardService.updateBoard(id, requestDto));
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardEntity> deleteBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.deleteBoard(id));
    }
}
