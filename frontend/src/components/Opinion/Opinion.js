import React from "react";
import ChatBox from "../ChatBox/ChatBox";

function Opinion(props) {
  return (
    <div>
      <h2>의견</h2>
      <ol>
        {props.opList.map((it) => {
          return <ChatBox key={it.id} {...it} user={it.userName} />;
        })}
      </ol>
    </div>
  );
}

export default Opinion;
