import './App.css';
import Navbar from "./Navbar";
import Home from "./Home";
import Agro from "./Agro";
import Logo from "./Logo";

import {BrowserRouter, Routes, Route, Link} from "react-router-dom";
import Investment from "./Investment";


function App() {

    return (
        <BrowserRouter>
            <div className="App">
                <header>
                    <Logo />
                    <Navbar/>
                </header>
                <main>
                        <Routes>
                            <Route exact path="/" element={<Home/>}/>
                            <Route exact path="/agro" element={<Agro/>}/>
                            <Route exact path="/investment" element={<Investment/>}/>
                        </Routes>
                </main>
            </div>
        </BrowserRouter>
    );
}

export default App;
