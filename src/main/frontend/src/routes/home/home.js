import {Routes, Route, Link, useNavigate} from 'react-router-dom'


const Home = () => {

    let navigate = useNavigate()

    return (
        <div>
            <img onClick={() => {
                navigate('/')
            }} className="Toast_logo" src="./Toast_logo.png"/>
        </div>

    )
};

export default Home;
