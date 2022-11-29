import React, {useState, useRef, useEffect} from "react";
import EditorBox from "../EditorBox/EditorBox";
import "./EditorForm.css";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {getToken, getId, getUsername} from "../../userInfo/userInfo";

//찬성 반대 값 가져오기
function Selected(props) {
    const handleSelect = (e) => {
        props.setSelected(e.target.value);
        //console.log(e.target.value);
    };

    return (
        <select
            onChange={handleSelect}
            defaultValue={props.selected}
            key={props.selected}
        >
            <option value="찬성">찬성</option>
            <option value="반대">반대</option>
        </select>
    );
}

function EditorForm(props) {
    const history = useNavigate();

    const [selected, setSelected] = useState("찬성");
    const [content, setContent] = useState("");
    const freeOp = "자유";

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (getId() == null) {
            alert("로그인을 해주세요!");
            window.location.replace("/login");
        } else {
            try {
                await axios({
                    method: "post",
                    url: `http://localhost:8080/api/proconTopic/${props.TopicId}/user/` + getId(),
                    data: {
                        content: content,
                        proCon: selected == "찬성" ? true : false,
                        proConTopicId: props.TopicId,
                    },
                }).then((data) => {
                    if (data.status === 200) {

                        console.log("댓글등록 성공");
                        window.location.reload();
                    }
                });
            } catch (error) {
                console.log(error);
            }
        }


    };

    return (
        <div>
            <h2>Create</h2>
            <form
                className="opinionForm"
                onSubmit={(e) => {
                    e.preventDefault();
                    {
                        props.op == "찬반"
                            ? props.onCreate(selected, content)
                            : props.onCreate(freeOp, content);
                    }
                }}
            >
                {props.op == "찬반" ? (
                    <Selected select={selected} setSelected={setSelected}/>
                ) : (
                    <></>
                )}
                <EditorBox
                    print="제출"
                    setContent={setContent}
                    onSubmit={handleSubmit}
                ></EditorBox>
            </form>
        </div>
    );
}

export default EditorForm;
