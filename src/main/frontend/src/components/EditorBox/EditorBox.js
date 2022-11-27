//CKEditor 설치
//npm install --save @ckeditor/ckeditor5-react @ckeditor/ckeditor5-build-classic
import React, {useState, useRef} from "react";
// import "@toast-ui/editor/dist/toastui-editor.css";
// import { Editor } from "@toast-ui/react-editor";
import {CKEditor} from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import "./EditorBox.css";
import axios from "axios";

//에디터 내용 가져오기
function EditorBox(props) {
    const editorRef = useRef();

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await axios({
                    method: 'post',
                    url: 'http://localhost:8080/api/proconTopic/1/user/1',
                    data: {
                        "content" :"test contffent",
                        "proCon":true,
                        "proConTopicId": 1
                    }
                }
            ).then((data) => {
                if (data.status === 200) { // 성공시

                    console.log("댓글등록 성공")
                }
            });
        } catch (error) {
            console.log(error)

        }
    };

    return (
        <>
            <div>
                <CKEditor
                    editor={ClassicEditor}
                    data=""
                    config={{
                        placeholder: "의견을 입력하세요.",
                    }}
                    onChange={(event, editor) => {
                        const data = editor.getData();
                        props.setContent(data);
                    }}
                />
            </div>
            <p className="editorsubmit">
                <button className="btn">{props.print}</button>
            </p>
        </>
    );
}

export default EditorBox;
