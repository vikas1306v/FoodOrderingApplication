import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import TasteHeaven from "../assets/TasteHeavenLogo.png";
import { Link } from "react-router-dom";
import { Alert, Snackbar, Button } from '@mui/material';
import {useDispatch} from 'react-redux'
import { addUser } from "../redux/slices/UserSlice";

export default function Login() {

  const dispatch=useDispatch()
  const location=useLocation()
  const from = location.state?.from?.pathname || "/";
  const [loginData, setLoginData] = useState({
    email: "",
    password: "",
  });
  const [alertOpen, setAlertOpen] = useState(false);
  const [alertMessage, setAlertMessage] = useState('');
  const [severity,setSeverity]=useState('')

  const navigate = useNavigate();
  const handleClose = () => {
    setAlertOpen(false);
  };

  const handleSubmit = async (e) => {
    const url = "http://localhost:8080/foodapp/auth/login";
    e.preventDefault();
  
    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const data = await response.json();
      console.log(data);

      if (data.status) {
        dispatch(addUser(data.data));
        navigate(from, { replace: true });
      } else {
        console.error('Login failed:', data.message);
      }
    } catch (error) {
      console.error('Error during login:', error);
    }
    
  };
  return (
    <>
      <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <img
            className="mx-auto h-24 w-auto"
            src={TasteHeaven}
            alt="Your Company"
          />
          <h2 className="mt-4 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
            Sign in to your account
          </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form className="space-y-6" onSubmit={handleSubmit}>
            <div>
              <label
                htmlFor="email"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Email address
              </label>
              <div className="mt-2">
                <input
                  onChange={(e) => {
                    setLoginData((old) => ({
                      ...old,
                      [e.target.name]: e.target.value,
                    }));
                  }}
                  id="email"
                  name="email"
                  type="email"
                  autoComplete="email"
                  value={loginData.email}
                  required
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <div className="flex items-center justify-between">
                <label
                  htmlFor="password"
                  className="block text-sm font-medium leading-6 text-gray-900"
                >
                  Password
                </label>
                <div className="text-sm">
                  <Link
                    to="/forgotpassword"
                    className="font-semibold text-indigo-600 hover:text-indigo-500"
                  >
                    Forgot password?
                  </Link>
                </div>
              </div>
              <div className="mt-2">
                <input
                  onChange={(e) => {
                    setLoginData((old) => ({
                      ...old,
                      [e.target.name]: e.target.value,
                    }));
                  }}
                  id="password"
                  name="password"
                  type="password"
                  autoComplete="current-password"
                  value={loginData.password}
                  required
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <button
                type="submit"
                className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
              >
                Sign in
              </button>
            </div>
          </form>

          <p className="mt-10 text-center text-sm text-gray-500">
            Not a member?{" "}
            <a
              href="#"
              className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500"
            >
              <Link to="/register">Register Here!</Link>
            </a>
          </p>
        </div>
      </div>
      <Snackbar open={alertOpen} autoHideDuration={6000} onClose={handleClose}  anchorOrigin={{ vertical: 'top', horizontal: 'center' }}  sx={{ width: '30%' }} >
        <Alert onClose={handleClose} severity={severity} sx={{ width: '100%' }}>
          {alertMessage}
        </Alert>
      </Snackbar>

     
    </>
  );
}
