import axios from "axios";
import { BiSearch } from "react-icons/bi";
import React, { useEffect, useState, useRef } from "react";
import BookItem from "../../components/BookItem/BookItem";
import Pagination from "../../components/Pagination/Pagination";

const Home = () => {
  const isMounted = useRef(false);
  const [articles, setArticles] = useState([]);
  const [mta, setMta] = useState([]);
  const [page, setPage] = useState(1);
  const [bookName, setBookName] = useState("");
  const size = 10;

  //책 검색 api
  const getAPI = async () => {
    axios
      .get("https://dapi.kakao.com/v3/search/book?target=title", {
        params: { query: bookName, page: page, size: size }, //bookName 요청
        headers: { Authorization: "KakaoAK 8d39abafc96ae6955e8238b4b400703a" },
      })
      .then(function (msg) {
        //msg : api에서 받아온 정보 저장
        setArticles(msg.data.documents);
        setMta(msg.data.meta);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  useEffect(() => {
    if (isMounted.current) getAPI();
    else isMounted.current = true;
    window.scrollTo(0, 0);
  }, [bookName, page]);

  const bookSearch = (event) => {
    event.preventDefault();
    //const bookName = document.getElementById("title").value;
    setBookName(document.getElementById("title").value);
  };

  console.log(articles, mta);
  //console.log(bookName, page);
  //console.log(Array.isArray(articles), articles);

  return (
    <div>
      <center>
        <form>
          <div className="Searchbox">
            <input
              id="title"
              type="text"
              placeholder="책 제목을 검색하세요."
            ></input>
            <button className="icon" onClick={bookSearch} id="bisearch">
              <BiSearch size="35" />
            </button>
          </div>
        </form>
      </center>

      {articles.map((article, index) => (
        <BookItem article={article} key={index} />
      ))}
      {Array.isArray(articles) && articles.length === 0 ? null : (
        <Pagination
          total={mta.total_count}
          limit={size}
          page={page}
          setPage={setPage}
        />
      )}
    </div>
  );
};

export default Home;
