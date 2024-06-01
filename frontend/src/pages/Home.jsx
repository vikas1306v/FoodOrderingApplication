import React from "react";
import HomeSlideShow from "../components/HomeSlideShow";
import Footer from "./Footer";
import Items from "../components/Items";
import Category from "./Category";
import Test from "./Test";
import { logout } from "../redux/slices/UserSlice";
import { useSelector,useDispatch } from "react-redux";
const Home = () => {
  const dispatch = useDispatch();
  const handleclick = () => {
    confirm("Are you sure you want to logout?");
    dispatch(logout());
  };
  return (
    <div>

      <HomeSlideShow />
      <Category />
      <Items />
      {/* <Test /> */}
      <button onClick={handleclick}>click</button>
      <div className="pt-24">
        <Footer />
      </div>
    </div>
  );
};

export default Home;
