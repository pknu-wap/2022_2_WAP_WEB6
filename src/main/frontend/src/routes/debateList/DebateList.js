import React, { useState } from "react";

import dummy from "./data.json";
import { useParams } from "react-router-dom";
import DebateHeader from "../../components/debateList/DebateHeader";
import DebateTopics from "../../components/debateList/DebateTopics";

//찬반 토론 목록 페이지
function DebateList() {
  const { title } = useParams();
  const topicList = dummy.debateTopics.filter((book) => book.title === title);

  return (
    <div className="wrap">
      <DebateHeader type="debate" debate="찬반 토론"></DebateHeader>
      <div>
        {topicList.map((topics) => (
          <DebateTopics
            id={topics.id}
            topicsList={topics.topic}
            title={topics.title}
            type="procon"
          ></DebateTopics>
        ))}
      </div>
    </div>
  );
}

export default DebateList;
