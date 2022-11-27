import React, { useState, useRef } from "react";
import EditorBox from "../EditorBox/EditorBox";
import "./EditorForm.css";

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
  const [selected, setSelected] = useState("찬성");
  const [content, setContent] = useState("");
  const freeOp = "자유";

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
          // props.onCreate(selected, content);
        }}
      >
        {props.op == "찬반" ? (
          <Selected select={selected} setSelected={setSelected} />
        ) : (
          <></>
        )}
        <EditorBox print="제출" setContent={setContent}></EditorBox>
      </form>
    </div>
  );
}

export default EditorForm;
