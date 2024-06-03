import { Carousel } from "flowbite-react";
import React from "react";
import Carousal1 from "../assets/Carousal1.png";
import header from "../assets/header_img.png";

const HomeSlideShow = () => {
  return (
    <>
      <div className="flex justify-center">
        <div className="mt-12 mb-12 h-48 sm:h-64 xl:h-80 2xl:h-96 w-full sm:w-3/4">
          <Carousel slide={true} slideInterval={3000}>
            <img src={header} alt="Header Image" className="mb-10 w-full h-full object-cover" />
            <img src={Carousal1} alt="Carousal Image 1" className="w-full h-full object-cover" />
          </Carousel>
        </div>
      </div>
      <div className="flex justify-center">
        <hr className="w-full sm:w-3/4" style={{ border: "1px solid rgb(210,204,240)" }} />
      </div>
    </>
  );
};

export default HomeSlideShow;
