import React, {useState} from "react";
import "./Modal.css";
import axios from "axios";
import {useParams} from "react-router-dom";

function Date() {
    return (
        <div>
            <p>기간</p>
            <input type="date" name="userSetDate"/>
        </div>
    );
}

const Modal = (props) => {
    const {booknum} = useParams();

    const {open, close, type} = props;

    const [boolean, setBoolean] = useState(true);
    const [topic, setTopic] = useState("");
    const [reason, setReason] = useState("");

    const onBooleanHandler = (event) => {
        setBoolean(event.currentTarget.value);
    };

    const onTopicHandler = (event) => {
        setTopic(event.currentTarget.value);
    };
    const onReasonHandler = (event) => {
        setReason(event.currentTarget.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await axios({
                    method: 'post',
                    url: 'http://localhost:8080/api/userId/1/bookId/' + booknum,
                    data: {
                        "id": 0,
                        "pro_con": true,
                        "topic": topic,
                        "due_date": "2022-12-22",
                        "reason": setReason

                    }
                }
            ).then((data) => {
                if (data.status === 200) { // 성공시

                    // window.location.replace('/')
                } else if (data.data == "sameIdExist") {
                    alert("중복된 ID 입니다. 다시 입력해주세요!!")
                } else {
                    console.log("예상치 못한 오류!!");
                }
            });
        } catch (error) {
            console.log(error)

        }

    };

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
                    <main>
                        {/*<select value={boolean}>*/}
                        {/*    <option value="true">찬성</option>*/}
                        {/*    <option value="false">반대</option>*/}
                        {/*</select>*/}
                        <div>
                            <input value={topic} onChange={onTopicHandler} type="text" name="topic" placeholder="주제"/>
                        </div>
                        <div>
                            <textarea value={reason} onChange={onReasonHandler} cols="55" rows="10" name="userExplan" placeholder="설명"/>
                        </div>

                        {type == "debate" && <Date/>}
                    </main>
                    <footer>
                        <button onClick={handleSubmit}>생성</button>
                    </footer>
                </section>
            ) : null}
        </div>
    );
};

export default Modal;
