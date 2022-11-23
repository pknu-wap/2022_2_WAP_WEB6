import React, { useState } from "react";
import dummy from "./data.json";
import { useParams } from "react-router-dom";
import DebateHeader from "../../components/debateList/DebateHeader";
import DebateTopics from "../../components/debateList/DebateTopics";

//자유 토론 목록 페이지
function FreeDebateList() {
  const { title } = useParams();
  const freeTopicList = dummy.freeTopics.filter((book) => book.title === title);

  return (
    <div className="wrap">
      <DebateHeader type="free" debate="자유 토론"></DebateHeader>
      <div>
        {freeTopicList.map((topics) => (
          <DebateTopics
            id={topics.id}
            topicsList={topics.topic}
            title={topics.title}
            type="free"
          ></DebateTopics>
        ))}
      </div>
    </div>
  );
}

export default FreeDebateList;
