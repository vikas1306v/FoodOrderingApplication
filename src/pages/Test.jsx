import React from "react";

const Test = () => {
  return (
    <>
      <div class="flex justify-center "
      //  style={{ border: "1px solid black" }}
       >
        <div
          class="flex flex-col justify-center w-3/4"
          // style={{ border: "1px solid black" }}
        >
          <div
            class="flex flex-col lg:flex-row max-w-5xl rounded-md justify-center shadow-sm items-center ml-14 w-[100%]"
            style={{ backgroundColor: "#ffca00" }}
          >
            <div class="flex flex-col  items-center lg:text-left text-center justify-between  space-y-6 px-8">
              <div class="flex flex-col items-start space-y-3">
                <div class="text-3xl md:text-5xl font-bold px-8">
                  Design is a journey of{" "}
                </div>
                <div class="text-3xl text-orange-500 md:text-5xl font-bold px-8">
                  Discovery{" "}
                </div>
                <div class="h-1 rounded-2xl w-20 bg-orange-500 ml-10"></div>
              </div>
              <button class="">
                click
                {/* <ion-icon
                  name="caret-forward-outline"
                  class="mt-2 p-2 bg-orange-500 rounded-full text-3xl text-white border-2 border-orange-500 hover:bg-white hover:text-orange-500"
                ></ion-icon> */}
              </button>
            </div>
            <div class="flex space-x-2 md:space-x-6 md:m-4 w-1/2">
              <div class=" w-96 h-60 lg:h-96  overflow-hidden ">
                <img
                  src="https://img.pikbest.com/origin/06/26/69/342pIkbEsTk3X.jpg!w700wp"
                  class="h-full w-full"
                  alt=""
                />
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* <script src="https://use.fontawesome.com/03f8a0ebd4.js"></script> */}
    </>
  );
};

export default Test;
