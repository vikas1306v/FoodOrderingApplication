import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import Navbar from "./components/Home/Navbar";
import Login from "./pages/LoginPage";
import About from "./pages/Home/About";

function App() {
  return (
    <>
    
      <BrowserRouter>
      
        
        <Routes>
        <Route path="/" element={<Navbar />}>
          <Route path="about" element={<About/>}/>
        
          </Route>
        <Route path="/login" element={<Login />} />
        
         
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
