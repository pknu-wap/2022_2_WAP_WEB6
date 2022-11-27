import React, {useEffect} from "react";
import "./DebateTopics.css";
import {Link} from "react-router-dom";
import axios from "axios";

function Topics(props) {
    return (
        <article>
            <Link
                to={
                    props.type === "free"
                        ? `/detailfree/${props.title}/${props.topic}`
                        : `/detaildebate/${props.title}/${props.topic}`
                }
            >
                <span>{props.topic}</span>
            </Link>
        </article>
    );
}

//key 값 확인하기
function DebateTopics(props) {

    // useEffect(() => {
    //     async function fetchData() {
    //         try {
    //             await axios({
    //                     method: 'get',
    //                     url: 'http://localhost:8080/api/proconTopic/' + booknum,
    //                 }
    //             ).then((data) => {
    //                 if (data.status === 200) { // 성공시
    //
    //                     console.log(data)
    //                     console.log("성공!!")
    //                     setDebateList(data.data)
    //                 } else {
    //                     console.log("예상치 못한 오류!!");
    //                 }
    //             });
    //         } catch (error) {
    //             console.log(error)
    //         }
    //     };
    //     fetchData();
    // }, []);


    return (
        <section className="topicSection">
            {/* {Object.values(props.topicsList).map((entrie, idx) => (
          <Topics key={idx} topic={entrie.topic} />
        ))} */}
            <Topics
                // 찬반
                type={props.type}
                key={props.id}
                topic={props.topicsList}
                title={props.title}
            />
        </section>
    );
}

export default DebateTopics;
