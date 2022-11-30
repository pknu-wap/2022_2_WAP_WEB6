import React, {useState, useRef} from "react";
import "./Modal.css";
import axios from "axios";
import {useParams, useNavigate} from "react-router-dom";
import {getId} from "../../userInfo/userInfo";
import * as config from '../../config';

function Date(props) {
    return (
        <div>
            <p>기간</p>
            <input type="date" name="userSetDate" ref={props.dateref}/>
        </div>
    );
}

const Modal = (props) => {
    const {booknum} = useParams();
    const history = useNavigate();

    const {open, close, type} = props;


    const topicRef = useRef(null);
    const explanRef = useRef(null);
    const dateRef = useRef(null);

    // const debateId = useRef(5);
    // console.log(debateId.current);

    const handleSubmit = async (event) => {
        if (getId() == null) {
            alert("로그인을 해주세요!");
            window.location.replace("/login")
        } else {
            try {
                await axios({
                    method: "post",
                    url: "http://"+config.URL+"/api/userId/1/bookId/" + booknum,
                    data: {
                        id: 0, //dataId.current
                        pro_con: type == "debate" ? true : false,
                        topic: topicRef.current.value,
                        due_date: dateRef.current.value,
                        reason: explanRef.current.value, //explan == reason
                    },
                }).then((data) => {
                    if (data.status === 200) {
                        // 성공시
                        //window.location.replace("/");
                        //alert("생성 완료");
                        window.location.reload();
                        // history(
                        //   type === "free"
                        //     ? `/detailfree/${props.title}/${topicRef.current.value}`
                        //     : `/detaildebate/${props.title}/${topicRef.current.value}/debateId/${debateId.current}/book/${booknum}`
                        // );
                    } else if (data.data == "sameIdExist") {
                        alert("중복된 ID 입니다. 다시 입력해주세요!!");
                    } else {
                        console.log("예상치 못한 오류!!");
                    }
                });
            } catch (error) {
                console.log(error);
            }
        }


    };

    function onSubmit(e) {
        e.preventDefault();
        handleSubmit();
    }

    return (
        //모달 열렸으면 openModal
        <div className={open ? "openModal modal" : "modal"}>
            {open ? (
                <section>
                    <header>
                        <button className="close" onClick={close}>
                            &times;
                        </button>
                    </header>
                    <form onSubmit={onSubmit}>
                        <div>
                            <input
                                // value={topic}
                                // onChange={onTopicHandler}
                                type="text"
                                name="topic"
                                placeholder="주제"
                                ref={topicRef}
                            />
                        </div>
                        <div>
              <textarea
                  // value={reason}
                  // onChange={onReasonHandler}
                  cols="55"
                  rows="10"
                  name="userExplan"
                  placeholder="설명"
                  ref={explanRef}
              />
                        </div>

                        {type == "debate" && <Date dateref={dateRef}/>}
                        <div className="formbtn">
                            <button>생성</button>
                        </div>
                    </form>
                    {/* <footer>
             <button onClick={handleSubmit}>생성</button>
          </footer> */}
                </section>
            ) : null}
        </div>
    );
};

export default Modal;