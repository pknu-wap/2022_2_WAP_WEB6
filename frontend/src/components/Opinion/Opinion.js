import React, { useEffect, useState } from "react";
import ChatBox from "../ChatBox/ChatBox";

/*페이징
http://localhost:8080/api/CommentsPaged/page/1/size/10/proconId/1
*/

/*대댓글
url: "http://localhost:8080/api/reply/proconTopic/1/motherComment/1",
*/

function Opinion(props) {
  return (
    <div>
      <h2>의견</h2>
      <ol>
        {props.opList.map((it) => {
          if (!it.reply) {
            return (
              <ChatBox
                key={it.id}
                {...it}
                user={it.userName}
                getFavStatus={props.getFavStatus}
              />
            );
          }
          // return <ChatBox key={it.id} {...it} user={it.userName} />;
        })}
      </ol>
    </div>
  );
}

export default Opinion;
