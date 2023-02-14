import React, { useState } from "react";
import DebateList from "../../routes/debateList/DebateList";
import { Routes, Route, useNavigate } from "react-router-dom";
import axios from "axios";
import * as config from "../../config";

const BookItem = ({ article }) => {
  const navigate = useNavigate();
  const { thumbnail, title, authors, contents } = article;
  const [bookId, setBookId] = useState("");

  const checkBook = async () => {
    await axios({
      //해당 책이 있는지 체크
      method: "post",
      url: "http://" + config.URL + "/api/check/book",
      data: {
        url: thumbnail,
        title: title,
        authors: authors[0],
        contents: contents,
      },
    }).then((data) => {
      // setBookId(data.data.id);
      navigate("/debateList/book/" + data.data.id, {
        // state: {
        //     'bookId':data.data.id,'thumbnail': thumbnail, 'title': title, 'authors': authors, 'contents': contents
        // }
      });
    });
  };

  const handleSubmit = () => {
    checkBook(title, authors);
  };

  return (
    <div className="container">
      {thumbnail && (
        <div>
          <a href={thumbnail} target="_blank" rel="noopener noreferrer">
            <img className="thumbnail" src={thumbnail} alt="thumbnail" />
          </a>
        </div>
      )}
      <div className="factor">
        <div className="title">
          {title}
          <br />
        </div>
        <div className="authors">
          {authors}
          <br />
        </div>
        <div className="contents">
          <br />
          {contents}…
        </div>
        <div>
          <br />
        </div>
        <button className="fdebate" onClick={handleSubmit}>
          찬반토론
        </button>
        <button className="fdebate free">자유토론</button>
      </div>
    </div>
  );
};

export default BookItem;
