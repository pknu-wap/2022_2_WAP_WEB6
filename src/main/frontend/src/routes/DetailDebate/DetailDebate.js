import React, { useState, useRef, useEffect } from "react";
// import TopicHeader from "../components/detailDebate/TopicHeader";
// import BookExplain from "../components/detailDebate/BookExplan";
// import Opinion from "../components/detailDebate/Opinion";
// import Form from "../components/detailDebate/Form";
import "./DetailDebate.css";
import { Form, useParams } from "react-router-dom";
import TopicHeader from "../../components/TopicHeader/TopicHeader";
import BookExplain from "../../components/BookExplain/BookExplain";
import Opinion from "../../components/Opinion/Opinion";
import EditorForm from "../../components/EditorForm/EditorForm";
import axios from "axios";
import * as config from '../../config';

//찬반 토론 상세 페이지
function DetailDebate() {
  const [comment, setComment] = useState([]);
  const [bookdata, setBookdata] = useState([]);

  const dataId = useRef(0);
  const params = useParams();
  // console.log(params.debateId);

  //댓글(현재 고정 값 -> likeNum, dislikeNum, userName)
  const onCreate = (proCon, comment) => {
    const newItem = {
      content: comment,
      likeNum: 2,
      dislikeNum: 1,
      id: dataId.current,
      proCon: proCon,
      proConTopicId: params.debateId,
      userName: "admin",
    };
    dataId.current += 1;
    setComment([newItem, ...comment]);
  };

  useEffect(() => {
    //댓글 get
    async function fetchComments() {
      try {
        await axios({
          method: "get",
          url:
            "http://"+config.URL+"/api/proconTopic/" +
            params.debateId +
            "/comments",
        }).then((response) => {
          if (response.status === 200) {
            // 성공시
            // 댓글 데이터
            console.log(response);
            console.log("성공!!");
            setComment(response.data);
          } else {
            console.log("예상치 못한 오류!!");
          }
        });
      } catch (error) {
        console.log(error);
      }
    }
    fetchComments();

    //책 정보 get
    async function fetchBookData() {
      try {
        await axios({
          method: "get",
          url: "http://"+config.URL+"/api/bookInfo/" + params.bookId,
        }).then((response) => {
          if (response.status === 200) {
            // 성공시
            // 책 데이터
            console.log(response);
            console.log("성공!!");
            setBookdata(response.data);
          } else {
            console.log("예상치 못한 오류!!");
          }
        });
      } catch (error) {
        console.log(error);
      }
    }
    fetchBookData();
  }, []);

  return (
    <div className="wrap">
      <TopicHeader topic={params.topic}></TopicHeader>
      <BookExplain
        imgsrc={bookdata.url}
        title={bookdata.title}
        body={bookdata.contents}
      ></BookExplain>
      <Opinion opList={comment} />
      <EditorForm
        op="찬반"
        onCreate={onCreate}
        TopicId={params.debateId}
      ></EditorForm>
    </div>
  );
}

export default DetailDebate;
