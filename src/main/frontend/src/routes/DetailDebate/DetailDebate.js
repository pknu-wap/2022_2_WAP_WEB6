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

    return (
        <div className="wrap">
            <TopicHeader topic={params.topic}></TopicHeader>
            <BookExplain
                img="https://image.aladin.co.kr/product/9871/8/cover500/k042535550_1.jpg"
                title={params.title}
                body={content[0].plot}
            ></BookExplain>
            <Opinion opList={data}/>
            <EditorForm op="찬반" onCreate={onCreate}></EditorForm>
        </div>
    );
}

export default DetailDebate;
