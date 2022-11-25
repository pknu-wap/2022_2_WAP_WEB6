import React, { useState, useRef } from "react";
import "@toast-ui/editor/dist/toastui-editor.css";
import { Editor } from "@toast-ui/react-editor";

//에디터 내용 가져오기
function EditorBox(props) {
  const editorRef = useRef();

  const handleEditorBtn = (e) => {
    const data = editorRef.current.getInstance().getHTML();
    props.setContent(data);
    //console.log(data);
  };

  return (
    <>
      <div>
        <Editor
          ref={editorRef}
          name="content"
          initialValue=" "
          previewStyle="vertical"
          height="300px"
          initialEditType="wysiwyg"
          toolbarItems={[
            ["heading", "bold", "italic", "strike"],
            ["hr", "quote"],
            ["ul", "ol", "task", "indent", "outdent"],
            ["table", "link"],
            ["code", "codeblock"],
          ]}
          //키보드 입력 컨트롤 방지?
          useCommandShortcut={false}
        />
      </div>
      <p className="editorsubmit">
        <button className="btn" onClick={handleEditorBtn}>
          {props.print}
        </button>
      </p>
    </>
  );
}

export default EditorBox;
