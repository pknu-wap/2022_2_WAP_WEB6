import React, {useState, useRef, useEffect} from "react";
// import TopicHeader from "../components/detailDebate/TopicHeader";
// import BookExplain from "../components/detailDebate/BookExplan";
// import Opinion from "../components/detailDebate/Opinion";
// import Form from "../components/detailDebate/Form";
import "./DetailDebate.css";
import dummy from "../debateList/data.json";
import {Form, useParams} from "react-router-dom";
import TopicHeader from "../../components/TopicHeader/TopicHeader";
import BookExplain from "../../components/BookExplain/BookExplain";
import Opinion from "../../components/Opinion/Opinion";
import EditorForm from "../../components/EditorForm/EditorForm";
import axios from "axios";

//찬반 토론 상세 페이지
function DetailDebate() {
    const [data, setData] = useState([]);
    const dataId = useRef(0);

    const params = useParams();
    const content = dummy.titles.filter((book) => book.title === params.title);
    //console.log(content);
    //console.log(content[0].plot);

    const onCreate = (opinion, content) => {
        const newItem = {
            opinion,
            content,
            id: dataId.current,
        };
        dataId.current += 1;
        setData([newItem, ...data]);
    };

    useEffect(() => {
        async function fetchComments() {
            try {
                await axios({
                        method: 'get',
                        url: 'http://localhost:8080/api/proconTopic/'+params.debateId + '/comments',
                    }
                ).then((data) => {
                    if (data.status === 200) { // 성공시
                        // 댓글 데이터
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
        fetchComments();



    }, []);


    return (
        <div className="wrap">
            <TopicHeader topic={params.topic}></TopicHeader>
            {/*<div>{params.bookid}</div>*/}
            <BookExplain
                img="https://image.aladin.co.kr/product/9871/8/cover500/k042535550_1.jpg"
                title={params.title}
                body="Sdfsdfsdf"
            ></BookExplain>
            <Opinion opList={data}/>
            <EditorForm op="찬반" onCreate={onCreate}></EditorForm>
        </div>
    );
}

export default DetailDebate;
