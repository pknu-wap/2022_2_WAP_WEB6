import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import DebateHeader from "../../components/DebateHeader/DebateHeader";
import DebateTopics from "../../components/DebateTopics/DebateTopics";
import { useLocation } from "react-router";
import axios from "axios";
import * as config from "../../config";
import "./DebateList.css";

//찬반 토론 목록 페이지
function DebateList() {
  const { booknum } = useParams();

  const [debateList, setDebateList] = useState([]);
  const [bookTitle, setBookTitle] = useState();
  const [content, setContent] = useState();
  const [dueDate, setDueDate] = useState();

  useEffect(() => {
    //책 목록
    async function fetchData() {
      try {
        await axios({
          method: "get",
          url: "http://" + config.URL + "/api/proconTopic/" + booknum,
        }).then((data) => {
          if (data.status === 200) {
            // 성공시

            console.log(data);
            console.log("성공!!");
            setDebateList(data.data);
          } else {
            console.log("예상치 못한 오류!!");
          }
        });
      } catch (error) {
        console.log(error);
      }
    }
    fetchData();

    //책 title 값 얻을 get -> history로 전달 필요
    async function fetchBookData() {
      try {
        await axios({
          method: "get",
          url: "http://" + config.URL + "/api/bookInfo/" + booknum,
        }).then((response) => {
          if (response.status === 200) {
            // 성공시
            // 책 데이터
            console.log(response);
            console.log("성공!");
            setBookTitle(response.data.title);
            setContent(response.data.contents);
            setDueDate(response.data.due_date);
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
        <DebateHeader
            type="debate"
            debate="찬반 토론"
            title={bookTitle}
        ></DebateHeader>
        <div className="bookContent">
          <h3>{bookTitle} 줄거리</h3>
          <span>{content}</span>
        </div>
        <div>
          {debateList.map((topics) => (
              <DebateTopics
                  // 찬반토론 주제 id
                  id={topics.id}
                  // 주제
                  topicsList={topics.topic}
                  // 책제목
                  title={topics.bookTitle}
                  // 책 id
                  bookId={booknum}
                  // Due_date
                  dueDate={topics.due_date}
                  type="procon"
              ></DebateTopics>
          ))}
        </div>
      </div>
  );
}

export default DebateList;
