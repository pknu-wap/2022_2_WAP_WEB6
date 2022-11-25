import React from "react";

const BookItem = ({ article }) => {
  const { thumbnail, title, authors, contents } = article;
  return (
    <div>
      {thumbnail && (
        <div className="thumbnail">
          <a href={thumbnail} target="_blank" rel="noopener noreferrer">
            <img src={thumbnail} alt="thumbnail" />
          </a>
        </div>
      )}
      <div>
        {title}<br/>
        {authors}
      </div>
    </div>
  );
};

export default BookItem;
