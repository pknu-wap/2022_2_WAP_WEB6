import React, { useState, useRef } from "react";
// import TopicHeader from "../components/detailDebate/TopicHeader";
// import BookExplain from "../components/detailDebate/BookExplan";
// import Opinion from "../components/detailDebate/Opinion";
// import Form from "../components/detailDebate/Form";
import "../DetailDebate/DetailDebate.css";
import dummy from "./data.json";
import { Form, useParams } from "react-router-dom";
import TopicHeader from "../../components/TopicHeader/TopicHeader";
import BookExplain from "../../components/BookExplain/BookExplain";
import Opinion from "../../components/Opinion/Opinion";
import EditorForm from "../../components/EditorForm/EditorForm";

//자유 토론 상세 페이지
function DetailFree() {
  const [data, setData] = useState([]);
  const dataId = useRef(0);

  const params = useParams();
  const content = dummy.titles.filter((book) => book.title === params.title);

  const onCreate = (opinion, content) => {
    const newItem = {
      opinion,
      content,
      id: dataId.current,
    };
    dataId.current += 1;
    setData([newItem, ...data]);
  };

  return (
    <div className="wrap">
      <TopicHeader topic={params.topic}></TopicHeader>
      <BookExplain
        img="https://image.aladin.co.kr/product/9871/8/cover500/k042535550_1.jpg"
        title={params.title}
        // body="새는 힘겹게 투장하여 알에서 나온다. 알은 세계다. 태어나려는 자는 한 세계를 깨뜨려야 한다. 새는 신에게로 날아간다. 그 신의 이름은 아프락사스다. - Page 110"
        body={content[0].plot}
      ></BookExplain>
      <Opinion opList={data} />
      <EditorForm onCreate={onCreate}></EditorForm>
    </div>
  );
}
export default DetailFree;
