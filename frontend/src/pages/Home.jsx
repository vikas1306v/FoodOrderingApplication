import React from "react";
import HomeSlideShow from "../components/HomeSlideShow";
import Footer from "./Footer";
import Items from "../components/Items";
import Category from "./Category";
import Test from "./Test";

const Home = () => {
  return (
    <div>
      <HomeSlideShow />
      <Category />
      <Items />
      {/* <Test /> */}
      <div className="pt-24">
        <Footer />
      </div>
    </div>
  );
};

export default Home;
