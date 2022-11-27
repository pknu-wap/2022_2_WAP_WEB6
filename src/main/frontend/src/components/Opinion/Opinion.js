import React from "react";
import ChatBox from "../ChatBox/ChatBox";

function Opinion(props) {
  //예시
  const userName = "식빵";

  return (
    <div>
      <h2>의견</h2>
      <ol>
        {props.opList.map((it) => {
          return <ChatBox key={it.id} {...it} user={userName} />;
        })}
      </ol>
    </div>
  );
}

export default Opinion;
