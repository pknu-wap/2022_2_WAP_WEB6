import React, {useEffect, useState} from "react";

import dummy from "./data.json";
import {useParams} from "react-router-dom";
import DebateHeader from "../../components/DebateHeader/DebateHeader";
import DebateTopics from "../../components/DebateTopics/DebateTopics";
import {useLocation} from "react-router";
import axios from "axios";

//찬반 토론 목록 페이지
function DebateList() {
    const {title} = useParams();
    const {booknum} = useParams();

    const topicList = dummy.debateTopics.filter((book) => book.title === title);

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
                {topicList.map((topics) => (
                    <DebateTopics
                        key={topics.id}
                        topicsList={topics.topic}
                        title={topics.title}
                        type="procon"
                    ></DebateTopics>
                ))}
            </div>
        </div>
    );
}

export default DebateList;
