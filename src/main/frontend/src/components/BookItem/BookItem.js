import React from "react";

const BookItem = ({article}) => {
    const {thumbnail, title, authors, contents} = article;
    const clickMe = () => {
        document.location.href('/')

    }

    const handleSubmit = () => {

    };
    return (
        <div>
            {thumbnail && (
                <div className="thumbnail">
                    <a href={thumbnail} target="_blank" rel="noopener noreferrer">
                        <img src={thumbnail} alt="thumbnail"/>
                    </a>
                </div>
            )}
            <div>
                {title}<br/>
                {authors}<br/>
                {contents}
                <>
                    <br/>
                    <button className="fdebate" onClick={clickMe}>찬반토론</button>

                </>

            </div>
            <div>----------------------------------</div>
        </div>
    );
};

export default BookItem;
