import { type } from "@testing-library/user-event/dist/type";
import React from "react";
import "./TopicHeader.css";

function TopicHeader(props) {
  let dueDate = props.due_date + "";
  dueDate = dueDate.replace(/\,/g, "");
  dueDate = dueDate.split("T");

  return (
    <>
      <header className="topicHeader">
        <span>{props.topic}</span>
      </header>
      <span style={{ display: "flex", justifyContent: "flex-end" }}>
        {dueDate[0]}
      </span>
    </>
  );
}

export default TopicHeader;
