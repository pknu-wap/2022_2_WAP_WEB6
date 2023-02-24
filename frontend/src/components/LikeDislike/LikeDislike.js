import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faThumbsDown, faThumbsUp } from "@fortawesome/free-regular-svg-icons";
import {
  faThumbsDown as fsThumbsDown,
  faThumbsUp as fsThumbsUp,
} from "@fortawesome/free-solid-svg-icons";
import React, { useState } from "react";
import { getId } from "../../userInfo/userInfo";
import * as config from "../../config";
import axios from "axios";
import { useSearchParams } from "react-router-dom";

function LikeDislike(props) {
  const handleCommentLike = async (status) => {
    if (getId() == null) {
      alert("로그인을 해주세요!");
      window.location.replace("/login");
    } else {
      try {
        props.getFavStatus();

        await axios({
          method: "post",
          url: `http://${config.URL}/api/toggle/comment/like/dislike`,
          data: {
            // userInfo.getId()
            commentId: props.commentId,
            userId: getId(),
            debateId: props.debateId,
            status: status,
          },
        }).then((data) => {
          if (data.status === 200) {
            // 성공시
          }
        });
      } catch (error) {
        console.log(error);
      }
    }
  };

  if (props.favStatus == 0) {
    return (
      <>
        <span onClick={() => handleCommentLike(1)}>
          {" "}
          <FontAwesomeIcon icon={faThumbsUp} size="1x" /> {props.likeNum}
        </span>
        <span onClick={() => handleCommentLike(-1)}>
          {" "}
          <FontAwesomeIcon icon={faThumbsDown} size="1x" /> {props.dislikeNum}
        </span>
      </>
    );
  } else if (props.favStatus == 1) {
    return (
      <>
        <span onClick={() => handleCommentLike(1)}>
          {" "}
          <FontAwesomeIcon icon={fsThumbsUp} size="1x" /> {props.likeNum}
        </span>
        <span onClick={() => handleCommentLike(-1)}>
          {" "}
          <FontAwesomeIcon icon={faThumbsDown} size="1x" /> {props.dislikeNum}
        </span>
      </>
    );
  } else {
    return (
      <>
        <span onClick={() => handleCommentLike(1)}>
          {" "}
          <FontAwesomeIcon icon={faThumbsUp} size="1x" /> {props.likeNum}
        </span>
        <span onClick={() => handleCommentLike(-1)}>
          {" "}
          <FontAwesomeIcon icon={fsThumbsDown} size="1x" /> {props.dislikeNum}
        </span>
      </>
    );
  }
}
export default LikeDislike;
