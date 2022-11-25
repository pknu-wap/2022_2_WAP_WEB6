import axios from "axios";
import {BiSearch} from "react-icons/bi";
import React, {useEffect, useState} from 'react';
import BookItem from "../../components/BookItem/BookItem";

const Home = () => {
    const [articles, setArticles] = useState([]);
    const bookSearch = (event) => {
        event.preventDefault(); //의도치 않은 이벤트 발생하지 않게 하는 함수
        const bookName = document.getElementById('title').value;
        console.log(bookName);

        axios.get("https://dapi.kakao.com/v3/search/book?target=title", {
            params: {query: bookName},   //bookName 요청
            headers: {Authorization: 'KakaoAK 8d39abafc96ae6955e8238b4b400703a'}
        })
            .then(function (msg) {    //msg : api에서 받아온 정보 저장
    
                //  for (int i = 0; i < msg.data.documents.length; i++;){
                //     console.log(msg.data.documents[i]);
                //  }

                let arr = msg.data.documents;
               setArticles(arr);
               console.log(articles)


            })
            .catch(function (error) {
                    console.log(error)
                }
            )
    }
    return (

        <div>
            <center>
                <form>
                    <div className="Searchbox">
                        <input id="title" type="text" placeholder='책 제목을 검색하세요.'></input>
                        <button onClick={bookSearch} id="bisearch">
                            <BiSearch className="icon" size="35"/>
                        </button>
                    </div>
                </form>
            </center>

            {articles.map((article) => (
                <BookItem article = {article} />

            ))}
        </div>


    )
};

export default Home;
