import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './BoardsList.css';
import Layout from '../users/Layout';

function BoardList() {
  const [boards, setBoards] = useState([]);

  useEffect(() => {
    fetch('/boards') // 백엔드 API
      .then(res => res.json())
      .then(data => setBoards(data))
      .catch(err => console.error('게시글 목록 조회 실패:', err));
  }, []);

  return (
    <Layout>
        <div className="board-list-container">
        <h2>게시판 목록</h2>
        <Link to="/boards/write">새 글 작성</Link>
        <ul>
            {boards.map(board => (
            <li key={board.id}>
                <Link to={`/boards/${board.id}`}>{board.title}</Link>
            </li>
            ))}
        </ul>
        </div>
    </Layout>
  );
}

export default BoardList;
