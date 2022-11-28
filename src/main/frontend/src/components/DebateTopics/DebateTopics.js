import React, { useEffect } from "react";
import "./DebateTopics.css";
import { Link } from "react-router-dom";
import axios from "axios";

function Topics(props) {
  return (
    <Link
      to={
        props.type === "free"
          ? `/detailfree/${props.title}/${props.topic}`
          : `/detaildebate/${props.title}/${props.topic}/debateId/${props.debateId}/book/${props.bookId}`
      }
    >
      <article>
        <span>{props.topic}</span>
      </article>
    </Link>
  );
}

//key 값 확인하기
function DebateTopics(props) {
  return (
    <section className="topicSection">
      {/* {Object.values(props.topicsList).map((entrie, idx) => (
          <Topics key={idx} topic={entrie.topic} />
        ))} */}
      <Topics
        // 찬반
        type={props.type}
        debateId={props.id}
        topic={props.topicsList}
        title={props.title}
        bookId={props.bookId}
      />
    </section>
  );
}

export default DebateTopics;
