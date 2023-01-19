import React, { useState } from "react";
import dummy from "./data.json";
import { useParams } from "react-router-dom";
import DebateHeader from "../../components/DebateHeader/DebateHeader";
import DebateTopics from "../../components/DebateTopics/DebateTopics";
import Pagination from "../../components/Pagination/Pagination";

//자유 토론 목록 페이지
function FreeDebateList() {
  const { title } = useParams();
  const freeTopicList = dummy.freeTopics.filter((book) => book.title === title);

  let limit = 5;
  //const [limit, setLimit] = useState(5);
  const [page, setPage] = useState(1);
  const offset = (page - 1) * limit;

  return (
    <div className="wrap">
      <DebateHeader type="free" debate="자유 토론"></DebateHeader>
      <div>
        {freeTopicList.map((topics) => (
          <DebateTopics
            key={topics.id}
            topicsList={topics.topic}
            title={topics.title}
            type="free"
          ></DebateTopics>
        ))}
      </div>
      <footer>
        <Pagination
          total={freeTopicList.length}
          limit={limit}
          page={page}
          setPage={setPage}
        />
      </footer>
    </div>
  );
}

export default FreeDebateList;
