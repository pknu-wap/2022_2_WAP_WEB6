import React from "react";
import "./BookExplain.css";

function BookExplain(props) {
  return (
    <section className="bookSection">
      <article className="bookImg">
        <img src={props.imgsrc}></img>
      </article>
      <article>
        <div>{props.title}</div>
        <div>{props.body}</div>
      </article>
    </section>
  );
}

export default BookExplain;
