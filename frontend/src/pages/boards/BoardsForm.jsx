import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

function BoardForm() {
  const { id } = useParams(); // 수정 시 사용
  const navigate = useNavigate();
  const isEdit = !!id;

  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  useEffect(() => {
    if (isEdit) {
      fetch(`/boards/${id}`)
        .then(res => res.json())
        .then(data => {
          setTitle(data.title);
          setContent(data.content);
        });
    }
  }, [id, isEdit]);

  const handleSubmit = (e) => {
    e.preventDefault();

    const method = isEdit ? 'PUT' : 'POST';
    const url = isEdit ? `/boards/${id}` : '/boards';
    const payload = {
      title,
      content,
      authorId: 1 // 테스트용: 실제 로그인 연동 필요
    };

    fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    })
      .then(() => navigate('/boards'))
      .catch(err => console.error('저장 실패:', err));
  };

  return (
    <div>
      <h2>{isEdit ? '게시글 수정' : '새 게시글 작성'}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>제목: </label>
          <input value={title} onChange={e => setTitle(e.target.value)} required />
        </div>
        <div>
          <label>내용: </label>
          <textarea value={content} onChange={e => setContent(e.target.value)} required />
        </div>
        <button type="submit">{isEdit ? '수정' : '작성'}</button>
      </form>
    </div>
  );
}

export default BoardForm;
