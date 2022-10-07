import {Routes, Route} from "react-router-dom";

import HelloWorld from "./routes/helloworld.component";

function App() {
    return (
        <Routes>
            <Route path='/'>
                <Route path='helloworld' element={<HelloWorld/>}/> {/*react spring 통신 예제 */}

                {/*<Route index={true} element={<Home/>}/> /!* index=true이므로 기본*!/*/}


            </Route>


        </Routes>
    );
}
export default App;
