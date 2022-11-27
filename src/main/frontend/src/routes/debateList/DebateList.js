import React, {useEffect, useState} from "react";

import dummy from "./data.json";
import {useParams} from "react-router-dom";
import DebateHeader from "../../components/DebateHeader/DebateHeader";
import DebateTopics from "../../components/DebateTopics/DebateTopics";
import {useLocation} from "react-router";
import axios from "axios";

//찬반 토론 목록 페이지
function DebateList() {
    // const {title} = useParams();
    const {booknum} = useParams();

    // const topicList = dummy.debateTopics.filter((book) => book.title === title);

    const [debateList, setDebateList] = useState([]);

    useEffect(() => {
        async function fetchData() {
            try {
                await axios({
                        method: 'get',
                        url: 'http://localhost:8080/api/proconTopic/' + booknum,
                    }
                ).then((data) => {
                    if (data.status === 200) { // 성공시

                        console.log(data)
                        console.log("성공!!")
                        setDebateList(data.data)
                    } else {
                        console.log("예상치 못한 오류!!");
                    }
                });
            } catch (error) {
                console.log(error)
            }
        };
        fetchData();
    }, []);

    return (

        <div className="wrap">
            <DebateHeader type="debate" debate="찬반 토론"></DebateHeader>
            <div>
                {debateList.map((topics) => (
                    <DebateTopics
                        // 찬반토론 주제 id
                        id={topics.id}
                        // 주제
                        topicsList={topics.topic}
                        // 책제목
                        title={topics.bookTitle}
                        // 책 id
                        bookId ={booknum}
                        type="procon"
                    ></DebateTopics>
                ))}
            </div>
        </div>
    );
}

export default DebateList;
