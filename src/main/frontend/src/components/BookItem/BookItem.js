import React from "react";
import DebateList from "../../routes/debateList/DebateList";
import {Routes, Route, useNavigate} from 'react-router-dom';
import axios from "axios";


const BookItem = ({article}) => {
    const navigate = useNavigate();
    const {thumbnail, title, authors, contents} = article;
    const history = useNavigate();


    const checkBook = async () => {
        await axios({
                method: 'post',
                url: 'http://localhost:8080/api/check/book',
                data: {
                    "id": 0,
                    "url": thumbnail,
                    "title": title,
                    "authors": authors[0],
                    "contents": contents,
                }
            }
        ).then((data) => {
            console.log(data);
        });
    };

    const handleSubmit = () => {

        checkBook(title, authors);

        navigate('/debateList/' + title, {
            state: {
                'thumbnail': thumbnail, 'title': title, 'authors': authors, 'contents': contents
            }
        })
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
                    <button className="fdebate" onClick={handleSubmit}>찬반토론
                    </button>

                </>

            </div>
            <div>----------------------------------</div>
        </div>
    );
};

export default BookItem;
