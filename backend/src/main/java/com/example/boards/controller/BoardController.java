package com.example.boards.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    // 게시글 목록 저장용 임시 리스트 (DB 대용)
    private final Map<Long, String> boardDB = new HashMap<>();
    private long idCounter = 1;

    // 1. 게시판 전체 조회 (GET /board)
    @GetMapping
    public List<String> getAllPosts() {
        return new ArrayList<>(boardDB.values());
    }

    // 2. 게시글 단일 조회 (GET /board/{id})
    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id) {
        return boardDB.getOrDefault(id, "게시글이 존재하지 않습니다.");
    }

    // 3. 게시글 작성 (POST /board)
    @PostMapping
    public String createPost(@RequestBody String content) {
        boardDB.put(idCounter, content);
        return "게시글 등록 완료. ID: " + (idCounter++);
    }

    // 4. 게시글 삭제 (DELETE /board/{id})
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        if (boardDB.remove(id) != null) {
            return "게시글 삭제 완료.";
        } else {
            return "게시글이 존재하지 않습니다.";
        }
    }

    // 5. 게시글 수정 (PUT /board/{id})
    @PutMapping("/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody String newContent) {
        if (boardDB.containsKey(id)) {
            boardDB.put(id, newContent);
            return "게시글 수정 완료.";
        } else {
            return "게시글이 존재하지 않습니다.";
        }
    }
}
