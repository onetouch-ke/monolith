package com.example.boards.service;

import com.example.boards.dto.BoardReqDto;
import com.example.boards.entity.BoardEntity;
import com.example.boards.repository.BoardRepository;
import com.example.users.entity.UserEntity;
import com.example.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
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
    public BoardEntity createBoard(BoardReqDto dto) {
    UserEntity author = userRepository.findByUserId(dto.getUserId())
        .orElseThrow(() -> new RuntimeException("작성자를 찾을 수 없습니다: " + dto.getUserId()));

    BoardEntity board = BoardEntity.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .author(author)
            .build();

    return boardRepository.save(board);
}


    // 게시글 수정
    public BoardEntity updateBoard(Long id, BoardReqDto dto) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());

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
