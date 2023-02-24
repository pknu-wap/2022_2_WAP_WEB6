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
import * as config from "../../config";
import Pagination from "../../components/Pagination/Pagination";
import { getId } from "../../userInfo/userInfo";

//찬반 토론 상세 페이지
function DetailDebate() {
  const [comment, setComment] = useState([]);
  const [bookdata, setBookdata] = useState([]);
  const [debatedata, setDebatedata] = useState([]);
  const [page, setPage] = useState(1);
  const size = 10;
  const [fav, setFav] = useState(false);
  const getFavStatus = () => setFav(!fav);
  //console.log(fav);

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
    async function commentPagination() {
      try {
        await axios({
          method: "post",
          //댓글 페이징 요청
          url: `http://${config.URL}/api/CommentsPaged/page/${page}/size/${size}/proconId/${params.debateId}`,
          data: {
            // userInfo.getId()
            userId: getId(),
          },
        }).then((response) => {
          if (response.status === 200) {
            // 성공시
            //console.log(response.data.response);
            // console.log("paging comment data");
            setComment(response.data.response);
          } else {
            console.log("예상치 못한 오류!!");
          }
        });
      } catch (error) {
        console.log(error);
      }
    }

    commentPagination();
  }, [page, fav]);

  useEffect(() => {
    //책 정보 get
    async function fetchBookData() {
      try {
        await axios({
          method: "get",
          url: "http://" + config.URL + "/api/bookInfo/" + params.bookId,
        }).then((response) => {
          if (response.status === 200) {
            // 성공시
            // 책 데이터
            // console.log(response);
            // console.log("book data");
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

    //토론 정보 get
    async function fetchDebateData() {
      try {
        await axios({
          method: "get",
          url: "http://" + config.URL + "/api/proconTopic/" + params.bookId,
        }).then((response) => {
          if (response.status === 200) {
            // 성공시
            // 책 데이터
            // console.log(response);
            // console.log("debate data");
            setDebatedata(response.data);
          } else {
            console.log("예상치 못한 오류!!");
          }
        });
      } catch (error) {
        console.log(error);
      }
    }
    fetchDebateData();
  }, []);

  //주제 설명(api=>reason)
  const bodyContent = debatedata.map((topic) =>
    topic.id == params.debateId ? topic.reason : ""
  );

  //마감 날짜 => 자유 토론 시 체크 추가하기
  const dueDate = debatedata.map((topic) =>
    topic.id == params.debateId ? topic.due_date : ""
  );

  return (
    <div className="wrap">
      <TopicHeader
        topic={params.topic}
        // due_date={dueDate[params.debateId - 1]}
        due_date={dueDate}
      ></TopicHeader>
      <BookExplain
        imgsrc={bookdata.url}
        title={bookdata.title}
        body={bodyContent}
      ></BookExplain>
      {Array.isArray(comment) && comment.length === 0 ? null : (
        <>
          <Opinion opList={comment.content} getFavStatus={getFavStatus} />
          <Pagination
            total={comment.totalElements}
            limit={size}
            page={page}
            setPage={setPage}
          />
        </>
      )}
      <EditorForm
        op="찬반"
        onCreate={onCreate}
        TopicId={params.debateId}
      ></EditorForm>
    </div>
  );
}

export default DetailDebate;
