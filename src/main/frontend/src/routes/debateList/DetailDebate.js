import React, { useState, useRef } from "react";
// import TopicHeader from "../components/detailDebate/TopicHeader";
// import BookExplain from "../components/detailDebate/BookExplan";
// import Opinion from "../components/detailDebate/Opinion";
// import Form from "../components/detailDebate/Form";
import "./DetailDebate.css";
import dummy from "./data.json";
import {Form, useParams} from "react-router-dom";
import TopicHeader from "../../components/detailDebate/TopicHeader";
import BookExplain from "../../components/detailDebate/BookExplan";
import Opinion from "../../components/detailDebate/Opinion";

//찬반 토론 상세 페이지
function DetailDebate() {
  const [data, setData] = useState([]);
  const dataId = useRef(0);

  const params = useParams();
  const content = dummy.titles.filter((book) => book.title === params.title);
  //console.log(content);
  //console.log(content[0].plot);

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
        body={content[0].plot}
      ></BookExplain>
      <Opinion opList={data} />
      <Form op="찬반" onCreate={onCreate}></Form>
    </div>
  );
}
export default DetailDebate;
