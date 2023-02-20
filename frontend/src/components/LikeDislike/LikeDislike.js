import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faThumbsDown, faThumbsUp} from "@fortawesome/free-regular-svg-icons";
import {faThumbsDown as fsThumbsDown, faThumbsUp as fsThumbsUp} from "@fortawesome/free-solid-svg-icons";
import React from "react";
import {getId} from "../../userInfo/userInfo";
import * as config from "../../config";
import axios from "axios";
function LikeDislike(props) {
    const handleCommentLike = async (status) => {
        if (getId() == null) {
            alert("로그인을 해주세요!");
            window.location.replace("/login");
        } else {
            try {
                await axios({
                    method: "post",
                    url:
                        `http://${config.URL}/api/toggle/comment/like/dislike`,
                    data: {
                        // userInfo.getId()
                        "commentId": props.commentId,
                        "userId" : getId(),
                        "debateId": props.debateId,
                        "status": status
                    }
                }).then((data) => {
                    if (data.status === 200) {
                        // 성공시
                    }
                });
            } catch (error) {
                console.log(error);
            }
        }
    }

    if (props.favStatus == 0) {
        return (<>
                <span> <FontAwesomeIcon onClick={() => handleCommentLike(1)} icon={faThumbsUp} size="1x"/> {props.likeNum}</span>
                <span> <FontAwesomeIcon onClick={() => handleCommentLike(-1)} icon={faThumbsDown} size="1x"/> {props.dislikeNum}</span>
            </>
        );
    }else if(props.favStatus == 1) {
        return (<>
                <span> <FontAwesomeIcon onClick={() => handleCommentLike(1)} icon={fsThumbsUp} size="1x"/> {props.likeNum}</span>
                <span> <FontAwesomeIcon onClick={() => handleCommentLike(-1)} icon={faThumbsDown} size="1x"/> {props.dislikeNum}</span>
            </>
        );
    } else {
        return (<>
                <span> <FontAwesomeIcon onClick={() => handleCommentLike(1)} icon={faThumbsUp} size="1x"/> {props.likeNum}</span>
                <span> <FontAwesomeIcon onClick={() => handleCommentLike(-1)} icon={fsThumbsDown} size="1x"/> {props.dislikeNum}</span>
            </>
        );
    }
}
export default LikeDislike;