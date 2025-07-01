package com.example.boards.service;

import com.example.boards.entity.BoardEntity;
import com.example.boards.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 전체 게시글 조회
    public List<BoardEntity> getAllBoards() {
        return boardRepository.findAll();
    }

    // 단일 게시글 조회 (id 기준)
    public BoardEntity getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    }

    // 게시글 작성
    public BoardEntity createBoard(BoardEntity board) {
        return boardRepository.save(board);
    }

    // 게시글 수정
    public BoardEntity updateBoard(Long id, BoardEntity updatedBoard) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        board.setTitle(updatedBoard.getTitle());
        board.setContent(updatedBoard.getContent());

        return boardRepository.save(board);
    }

    // 게시글 삭제
    public BoardEntity deleteBoard(Long id) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        boardRepository.delete(board);
        return board;
    }
}
