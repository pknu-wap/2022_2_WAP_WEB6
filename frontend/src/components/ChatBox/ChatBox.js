import React, { useState } from "react";
import EditorBox from "../EditorBox/EditorBox";
import "./ChatBox.css";
import axios from "axios";
import * as config from "../../config";
import { useParams } from "react-router-dom";
import { getId } from "../../userInfo/userInfo";

function ChatBox(props) {
  const params = useParams();
  const [content, setContent] = useState("");
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

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (getId() == null) {
      alert("로그인을 해주세요!");
      window.location.replace("/login");
    } else {
      try {
        await axios({
          method: "post",
          url:
            `http://${config.URL}/api/proconTopic/${params.debateId}/user/` +
            getId() +
            `/parent/${props.id}`,
          data: {
            content: content,
            proCon: true,
            proConTopicId: props.id,
            reply: true,
          },
        }).then((data) => {
          if (data.status === 200) {
            // 성공시
            console.log(
              `http://${config.URL}/api/proconTopic/${params.debateId}/user/` +
                getId() +
                `/parent/${props.id}`
            );
            console.log(data);
            //   console.log("성공!!")
            //   localStorage.clear()
            //   localStorage.setItem('id', data.data.user.id)
            //   localStorage.setItem('username', data.data.user.username)
            //   localStorage.setItem('token', data.data.jwtToken)
            //
            //   window.location.replace('/')
            // } else if (data.data == "sameIdExist") {
            //   alert("중복된 ID 입니다. 다시 입력해주세요!!")
            // } else {
            //   console.log("예상치 못한 오류!!");
          }
        });
      } catch (error) {
        console.log(error);
      }
    }
  };

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
          <h5>{props.userName}</h5>
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
          {/* <button>수정</button> */}
        </div>
        <div className={visible ? "reply" : ""}>
          {visible && (
            <EditorBox
              //value="댓글"
              print="작성"
              setContent={setContent}
              onSubmit={handleSubmit}
            />
          )}
        </div>
      </div>
    </li>
  );
}

export default ChatBox;
