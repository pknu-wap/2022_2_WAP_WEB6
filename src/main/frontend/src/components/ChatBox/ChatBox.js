import React, { useState } from "react";
import EditorBox from "../EditorBox/EditorBox";
import "./ChatBox.css";

function ChatBox(props) {
  let classNameSet = null;
  let opinionDiv = null;

  if (props.proCon == true) {
    opinionDiv = <div className="pro">찬성</div>;
    classNameSet = "pro";
  } else if (props.proCon == false) {
    opinionDiv = <div className="con">반대</div>;
    classNameSet = "con";
  }
  //아직 지정 x
  else if (props.opinion == "자유") {
    classNameSet = "free";
  }
  const [visible, setVisible] = useState(false);

  return (
    <li className="chatBox">
      <div className="ChatBox-container">
        <div className={"userImg" + classNameSet}>
          <img
            src="https://cdn-icons-png.flaticon.com/512/4553/4553212.png"
            height="40"
            width="40"
          ></img>
          {opinionDiv}
        </div>
        <div className={"msg-container " + "msg" + classNameSet}>
          <h5>{props.user}</h5>
          <p dangerouslySetInnerHTML={{ __html: props.content }}></p>
        </div>
        <div className={"reaction " + "reaction" + classNameSet}>
          {/* 기능 구현X -> 좋아요, 싫어요, 대댓글*/}
          <span>👍 {props.likeNum}</span>
          <span>👎 {props.dislikeNum}</span>
          <button
            onClick={() => {
              setVisible(!visible);
            }}
          >
            댓글
          </button>
          <button>삭제</button>
        </div>
        <div className={visible ? "reply" : ""}>
          {visible && <EditorBox value="댓글" print="작성" />}
        </div>
      </div>
    </li>
  );
}

export default ChatBox;
