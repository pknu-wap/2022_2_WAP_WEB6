import React from "react";
import "./DebateTopics.css";
import { Link } from "react-router-dom";

function Topics(props) {
  return (
    <article>
      <Link
        to={
          props.type === "free"
            ? `/detailfree/${props.title}/${props.topic}`
            : `/detaildebate/${props.title}/${props.topic}`
        }
      >
        <span>{props.topic}</span>
      </Link>
    </article>
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
        type={props.type}
        key={props.id}
        topic={props.topicsList}
        title={props.title}
      />
    </section>
  );
}

export default DebateTopics;
