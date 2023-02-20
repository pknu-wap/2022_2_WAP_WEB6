import React, { useEffect, useState } from "react";
import EditorBox from "../EditorBox/EditorBox";
import "./ChatBox.css";
import axios from "axios";
import * as config from "../../config";
import { useParams } from "react-router-dom";
import { getId } from "../../userInfo/userInfo";
import { AiOutlineUp, AiOutlineDown } from "react-icons/ai";
import LikeDislike from "../LikeDislike/LikeDislike";

function CommentBox({ name, content, likeNum, dislikeNum, favStatus }) {
  return (
    <li className="commentBox">
      <div className="commetUserImg">
        <img
          src="https://cdn-icons-png.flaticon.com/512/4553/4553212.png"
          height="30"
          width="30"
        ></img>
      </div>
      <div className="msg-container">
        <h5>{name}</h5>
        <p dangerouslySetInnerHTML={{ __html: content }}></p>
      </div>
      <div className="reaction">
        <span>👍 {likeNum}</span>
        <span>👎 {dislikeNum}</span>
      </div>
    </li>
  );
}

function ChatBox(props) {
  const params = useParams();
  const [content, setContent] = useState("");
  const [nestedComment, setNestedCommet] = useState([]);
  const [postVisible, setPostVisible] = useState(false);
  const [getVisible, setGetVisible] = useState(false);

  const validContent = content.length > 0;

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

  useEffect(() => {
    //대댓글 get
    async function fetchNestedComment() {
      try {
        await axios({
          method: "post",
          url: `http://${config.URL}/api/reply/proconTopic/${params.debateId}/motherComment/${props.id}`,
          data: {
            "userId" : getId()
          }
        }).then((response) => {
          if (response.status === 200) {
            //console.log(response);
            setNestedCommet(response.data);
          } else {
            console.log("예상치 못한 오류!!");
          }
        });
      } catch (error) {
        console.log(error);
      }
    }

    fetchNestedComment();
  }, [getVisible === true]);
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
            window.location.reload();
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
      <li>
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
            <p dangerouslySetInnerHTML={{__html: props.content}}></p>
          </div>
          <div className={"reaction " + "reaction" + classNameSet}>
            {/* 기능 구현X -> 좋아요, 싫어요, 대댓글*/}
            <LikeDislike
                likeNum = {props.likeNum}
                dislikeNum = {props.dislikeNum}
                favStatus = {props.favStatus}
                commentId = {props.id}
                debateId =  {params.debateId}
            >
            </LikeDislike>
            {/*<span> <FontAwesomeIcon icon={faThumbsUp} size="1x"/> {props.likeNum}</span>*/}
            {/*<span> <FontAwesomeIcon icon={faThumbsDown} size="1x"/> {props.dislikeNum}</span>*/}
            <button
                onClick={() => {
                  setPostVisible(!postVisible);
                }}
            >
              답글
            </button>
          </div>
          <div className={postVisible ? "reply" : ""}>
            {postVisible && (
                <EditorBox
                    //value="댓글"
                    print="작성"
                    setContent={setContent}
                    onSubmit={handleSubmit}
                    disabled={!validContent}
                />
            )}
          </div>
          <div
              className={"nestedComment-btn " + "getBtn-" + classNameSet}
              onClick={() => setGetVisible(!getVisible)}
          >
            {getVisible ? <AiOutlineUp/> : <AiOutlineDown/>}
            <span>답글</span>
          </div>
          <ol className="commentWrap">
            {getVisible &&
                nestedComment.map((it) => {
                  return (
                      <CommentBox
                          key={it.id}
                          name={it.userName}
                          content={it.content}
                          likeNum={it.likeNum}
                          dislikeNum={it.dislikeNum}
                          favStatus={it.favStatus}
                      />
                  );
                })}
          </ol>
        </div>
      </li>
  );
}

export default ChatBox;
