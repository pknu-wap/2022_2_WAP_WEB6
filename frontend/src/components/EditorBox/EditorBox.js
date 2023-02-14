//CKEditor 설치
//npm install --save @ckeditor/ckeditor5-react @ckeditor/ckeditor5-build-classic
import React, { useState, useRef } from "react";
// import "@toast-ui/editor/dist/toastui-editor.css";
// import { Editor } from "@toast-ui/react-editor";
import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import "./EditorBox.css";

//에디터 내용 가져오기
function EditorBox(props) {
  //const editorRef = useRef();

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
        <button
          className="btn"
          onClick={props.onSubmit}
          disabled={props.disabled}
        >
          {props.print}
        </button>
      </p>
    </>
  );
}

export default EditorBox;
