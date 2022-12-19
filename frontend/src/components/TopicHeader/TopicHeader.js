import React from "react";
import "./TopicHeader.css";

function TopicHeader(props) {
  return (
    <header className="topicHeader">
      <span>{props.topic}</span>
    </header>
  );
}

export default TopicHeader;
