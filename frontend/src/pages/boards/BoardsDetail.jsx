import React, { useEffect, useState } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';

function BoardDetail() {
  const { id } = useParams();
  const [board, setBoard] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`/boards/${id}`)
      .then(res => res.json())
      .then(data => setBoard(data))
      .catch(err => console.error('게시글 조회 실패:', err));
  }, [id]);

  if (!board) return <div>로딩 중...</div>;

  return (
    <div>
      <h2>{board.title}</h2>
      <p>{board.content}</p>
      <Link to={`/boards/edit/${board.id}`}>수정</Link>
      <button onClick={() => {
        fetch(`/boards/${board.id}`, { method: 'DELETE' })
          .then(() => navigate('/boards'))
          .catch(err => console.error('삭제 실패:', err));
      }}>
        삭제
      </button>
      <br />
      <Link to="/boards">목록으로</Link>
    </div>
  );
}

export default BoardDetail;
