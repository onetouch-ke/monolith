import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import './Main.css';
import Layout from './Layout';
import logo from '../../logo.png'; // 이미지 import

function Main() {
  const location = useLocation();
  const user = location.state;

  return (
    <Layout>
      <div className="main-container">
        <h2>일반 사용자 메인 페이지</h2>

        <img src={logo} alt="Logo" style={{ width: '150px', height: 'auto', margin: '20px 0' }} />
        
        <p>사용자 ID: {user?.userId}</p>
        <p>이메일: {user?.email}</p>
      </div>
    </Layout>
  );
}

export default Main;
