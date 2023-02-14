import "./Mypage.css";
import React, { useState } from "react";

function Aside(props) {
  const lis = [];

  props.article.map((t) => {
    lis.push(
      <li key={t.id}>
        <a
          id={t.id}
          href={"/read/" + t.id}
          onClick={(e) => {
            e.preventDefault();
            props.onChangeMode(Number(e.target.id));
          }}
        >
          {t.title}
        </a>
      </li>
    );
  });

  return (
    <aside>
      <ul>{lis}</ul>
    </aside>
  );
}

function Article(props) {
  return (
    <article>
      <h2>{props.title}</h2>
      {props.body}
    </article>
  );
}

function Mypage() {
  const [mode, setMode] = useState("MAIN");
  const [id, setId] = useState(null);
  const mainArticle = [
    { id: 1, title: "토론 목록 조회", body: "생성글 목록" },
    { id: 2, title: "댓글 목록 조회", body: "작성 댓글 목록" },
    { id: 3, title: "좋아요 목록", body: "좋아요 누른 목록" },
    { id: 4, title: "사용자 정보", body: "사용자 정보 변경" },
  ];

  let content = null;
  if (mode === "MAIN") {
    content = <Article title="MAIN" body="main blabla" />;
  } else if (mode === "READ") {
    let title,
      body = null;

    mainArticle.map((i) => {
      if (i.id === id) {
        title = i.title;
        body = i.body;
      }
    });

    content = <Article title={title} body={body} />;
  }

  return (
    <div>
      <h1 onClick={() => setMode("MAIN")}>마이페이지</h1>
      <div className="pagelist">
        <Aside
          article={mainArticle}
          onChangeMode={(id) => {
            setMode("READ");
            setId(id);
          }}
        />
        <section>{content}</section>
      </div>
    </div>
  );
}

export default Mypage;
