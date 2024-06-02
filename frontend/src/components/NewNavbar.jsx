import React, { useEffect, useState } from "react";
import { Link, Outlet } from "react-router-dom";
import cart_img from "../assets/NewAssets/bag_icon.png";
import { useDispatch, useSelector } from "react-redux";
import { logout } from "../redux/slices/UserSlice";
const NewNavbar = () => {
  const userLoginData=useSelector(state=>state.user)
  const [user,setUser]=useState(false)
  const dispatch=useDispatch()
  useEffect(()=>{
    if(userLoginData.token!=null){
      setUser(true)
    }else{
      setUser(false)
    }
  },[userLoginData.token])
  const [menuOpen, setMenuOpen] = useState(false);

  const toggleMenu = () => {
    setMenuOpen(!menuOpen);
  };

  const closeMenu = () => {
    setMenuOpen(false);
  };
  const handleLogout=()=>{
    dispatch(logout())
  }

  return (
    <>
      <nav className="relative px-4 py-4 flex justify-between items-center bg-slate-300">
        <Link to="/">
          <h1 className="font-bold text-xl ml-5">Taste Heaven</h1>
        </Link>
        <div className="lg:hidden">
          <button
            onClick={toggleMenu}
            className="navbar-burger flex items-center text-blue-600 p-3"
          >
            <svg
              className="block h-4 w-4 fill-current"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <title>Mobile menu</title>
              <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"></path>
            </svg>
          </button>
        </div>
        <ul className="hidden lg:flex lg:mx-auto  lg:items-center lg:w-auto lg:space-x-6">
          <li>
            <Link className="text-xl text-gray-400 hover:text-gray-500" to="/">
              Home
            </Link>
          </li>
          <li className="text-gray-300">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              stroke="currentColor"
              className="w-4 h-4"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M12 5v0m0 7v0m0 7v0m0-13a1 1 0 110-2 1 1 0 010 2zm0 7a1 1 0 110-2 1 1 0 010 2zm0 7a1 1 0 110-2 1 1 0 010 2z"
              />
            </svg>
          </li>
          <li>
            <Link className="text-xl text-blue-600 font-bold" to="/searchbar">
              Search
            </Link>
          </li>
          <li className="text-gray-300">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              stroke="currentColor"
              className="w-4 h-4"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M12 5v0m0 7v0m0 7v0m0-13a1 1 0 110-2 1 1 0 010 2zm0 7a1 1 0 110-2 1 1 0 010 2zm0 7a1 1 0 110-2 1 1 0 010 2z"
              />
            </svg>
          </li>
          <li>
            <Link
              className="text-xl text-gray-400 hover:text-gray-500"
              to="/about"
            >
              Contact
            </Link>
          </li>
        </ul>
        <div className="hidden lg:flex lg:items-center lg:space-x-4">
        <Link to="/cart"><img src={cart_img}></img></Link>
         {
          user!=true? <Link
          className="py-2 px-6 bg-gray-50 hover:bg-gray-100 text-sm text-gray-900 font-bold rounded-xl transition duration-200"
          to="/login"
        >
          Sign In
        </Link>:null
         }
         {
          user!=true? <Link
          className="py-2 px-6 bg-blue-500 hover:bg-blue-600 text-sm text-white font-bold rounded-xl transition duration-200"
          to="/register"
        >
          Sign Up
        </Link>:null
         }
          {
          user==true? <button
          className="py-2 px-6 bg-blue-500 hover:bg-blue-600 text-sm text-white font-bold rounded-xl transition duration-200"
          onClick={handleLogout}
        >
          Logout
        </button>:null
         }
        </div>
      </nav>
      {menuOpen && (
        <div className="navbar-menu relative z-50 lg:hidden">
          <div
            className="navbar-backdrop fixed inset-0 bg-gray-800 opacity-25"
            onClick={closeMenu}
          ></div>
          <nav className="fixed top-0 left-0 bottom-0 flex flex-col w-5/6 max-w-sm py-6 px-6 bg-white border-r overflow-y-auto">
            <div className="flex items-center mb-8">
              <Link className="mr-auto text-3xl font-bold leading-none" to="#">
                <svg className="h-12" alt="logo" viewBox="0 0 10240 10240">
                  <path xmlns="http://www.w3.org/2000/svg" d=""></path>
                </svg>
              </Link>
              <button className="navbar-close" onClick={closeMenu}>
                <svg
                  className="h-6 w-6 text-gray-400 cursor-pointer hover:text-gray-500"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="M6 18L18 6M6 6l12 12"
                  ></path>
                </svg>
              </button>
            </div>
            <div>
              <ul className="font-semibold text-3xl">
                <li className="mb-1 font-semibold text-3xl">
                  <Link
                    className="block p-4 text-sm font-semibold text-gray-400 hover:bg-blue-50 hover:text-blue-600 rounded"
                    to="#"
                  >
                    Home
                  </Link>
                </li>
                <li className="mb-1">
                  <Link
                    className="block p-4 text-sm font-semibold text-gray-400 hover:bg-blue-50 hover:text-blue-600 rounded"
                    to="#"
                  >
                    About Us
                  </Link>
                </li>
                <li className="mb-1">
                  <Link
                    className="block p-4 text-sm font-semibold text-gray-400 hover:bg-blue-50 hover:text-blue-600 rounded"
                    to="#"
                  >
                    Services
                  </Link>
                </li>
                <li className="mb-1">
                  <Link
                    className="block p-4 text-sm font-semibold text-gray-400 hover:bg-blue-50 hover:text-blue-600 rounded"
                    to="#"
                  >
                    Pricing
                  </Link>
                </li>
                <li className="mb-1">
                  <Link
                    className="block p-4 text-sm font-semibold text-gray-400 hover:bg-blue-50 hover:text-blue-600 rounded"
                    to="#"
                  >
                    Contact
                  </Link>
                </li>
              </ul>
              <div className="hidden lg:flex lg:items-center lg:space-x-4">
                <Link
                  className="py-2 px-6 bg-gray-50 hover:bg-gray-100 text-sm text-gray-900 font-bold rounded-xl transition duration-200"
                  to="/login"
                >
                  Sign In
                </Link>
                <Link
                  className="py-2 px-6 bg-blue-500 hover:bg-blue-600 text-sm text-white font-bold rounded-xl transition duration-200"
                  to="/register"
                >
                  Sign Up
                </Link>
              </div>
            </div>
            <div className="mt-auto">
              <Link
                className="block w-full py-2 px-6 mb-4 bg-gray-50 hover:bg-gray-100 text-sm text-gray-900 font-bold rounded-xl transition duration-200"
                to="/login"
              >
                Sign In
              </Link>
              <Link
                className="block w-full py-2 px-6 bg-blue-500 hover:bg-blue-600 text-sm text-white font-bold rounded-xl transition duration-200"
                to="/register"
              >
                Sign Up
              </Link>
            </div>
          </nav>
        </div>
      )}

      <Outlet />
    </>
  );
};

export default NewNavbar;
