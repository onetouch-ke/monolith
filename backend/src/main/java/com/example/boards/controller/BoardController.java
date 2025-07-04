package com.example.boards.controller;

import com.example.boards.dto.BoardReqDto;
import com.example.boards.dto.BoardResDto;
import com.example.boards.entity.BoardEntity;
import com.example.boards.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 전체 게시글 조회
    @GetMapping
public ResponseEntity<List<BoardResDto>> getAllBoards() {
    List<BoardEntity> boards = boardService.getAllBoards();
    List<BoardResDto> responses = boards.stream()
        .map(board -> BoardResDto.builder()
            .id(board.getId())
            .title(board.getTitle())
            .content(board.getContent())
            .authorUsername(board.getAuthor().getUsername())
            .build())
        .toList();
    return ResponseEntity.ok(responses);
}


    // 단일 게시글 조회
    @GetMapping("/{id}")
public ResponseEntity<BoardResDto> getBoard(@PathVariable Long id) {
    BoardEntity board = boardService.getBoardById(id);
    BoardResDto response = BoardResDto.builder()
        .id(board.getId())
        .title(board.getTitle())
        .content(board.getContent())
        .authorUsername(board.getAuthor().getUsername())
        .build();
    return ResponseEntity.ok(response);
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
