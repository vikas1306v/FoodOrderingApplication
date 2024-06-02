import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import Login from "./pages/LoginPage";
import About from "./pages/About";
import Home from "./pages/Home";
import PageNotFound from "./pages/PageNotFound";
import ForgotPassword from "./pages/ForgotPassword";
import RegisterPage from "./pages/RegisterPage";
import MyLocation from "./components/MyLocation";
import InternetConnetion from "./components/InternetConnetion";
import SearchBar from "./pages/SearchBar";
import NewNavbar from "./components/NewNavbar";
import Cart from "./pages/Cart";
import 'flowbite';
import 'flowbite/dist/flowbite.min.css';



function App() {
  return (
    <>
      {/* <InternetConnetion> */}
       
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<NewNavbar />}>
              <Route index element={<Home />} />
              <Route path="about" element={<About />} />
              <Route path="searchbar" element={<SearchBar/>}/>
              <Route path="cart" element={<Cart/>}/>
            </Route>
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/*" element={<PageNotFound />} />
            <Route path="/forgotpassword" element={<ForgotPassword />} />
          </Routes>
        </BrowserRouter>
      {/* </InternetConnetion> */}
    </>
  );
}

export default App;
