import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Login from './pages/users/Login';
import Register from './pages/users/Register';
import Admin from './pages/users/Admin';
import Main from './pages/users/Main';

import BoardsList from './pages/boards/BoardsList';      // 게시판 목록
import BoardsDetail from './pages/boards/BoardsDetail';  // 게시글 상세
import BoardsForm from './pages/boards/BoardsForm';      // 게시글 작성/수정

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="/main" element={<Main />} />

        <Route path="/boards" element={<BoardsList />} />
        <Route path="/boards/:id" element={<BoardsDetail />} />
        <Route path="/boards/write" element={<BoardsForm />} />
        <Route path="/boards/edit/:id" element={<BoardsForm />} />
      </Routes>
    </Router>
  );
}

export default App;
