import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import DebateHeader from "../../components/DebateHeader/DebateHeader";
import DebateTopics from "../../components/DebateTopics/DebateTopics";
import { useLocation } from "react-router";
import axios from "axios";
import * as config from "../../config";
import "./AllDebate.css";
import Header from "../../components/Header/Header";
import Pagination from "../../components/Pagination/Pagination";

//찬반 토론 목록 페이지
function AllDebate() {
  const { booknum } = useParams();

  const [debateList, setDebateList] = useState([]);
  const [bookTitle, setBookTitle] = useState();
  const [content, setContent] = useState();
  const [dueDate, setDueDate] = useState();

  const [limit, setLimit] = useState(5);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  useEffect(() => {
    //책 목록
    async function fetchData() {
      try {
        await axios({
          method: "get",
          url: "http://" + config.URL + "/api/proconTopic/allTopic",
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

    if (booknum) fetchBookData();
  }, []);

  return (
    <div className="wrap">
      <Header type="debate" debate="모든 토론" title={bookTitle}></Header>
      <label>
        페이지 당 표시할 게시물 수:&nbsp;
        <select
          type="number"
          value={limit}
          onChange={({ target: { value } }) => setLimit(Number(value))}
        >
          <option value="5">5</option>
          <option value="10">10</option>
          <option value="20">20</option>
          <option value="30">30</option>
          <option value="100">100</option>
        </select>
      </label>
      <main>
        {debateList.slice(offset, offset + limit).map((topics) => (
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
            // Book_id
            bookId={topics.bookId}
            type="procon"
            key={topics.id}
          ></DebateTopics>
        ))}
      </main>
      <footer>
        <Pagination
          total={debateList.length}
          limit={limit}
          page={page}
          setPage={setPage}
        />
      </footer>
    </div>
  );
}

export default AllDebate;
