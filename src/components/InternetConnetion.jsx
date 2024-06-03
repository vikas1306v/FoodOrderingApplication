import React from "react";
import { Detector } from "react-detect-offline";
import wifiicon from "../assets/wifiicon.png";

const InternetConnetion = (props) => {
  return (
    <>
      <Detector
        render={({ online }) =>
          online ? (
            props.children
          ) : (
            <div className="flex justify-center">
              <div className=" p-4">
                <div className="flex justify-center">
                  <div className="flex flex-col items-center">
                    <img src={wifiicon} className="w-52 mb-4" alt="Wifi Icon" />
                    <h1 className="text-3xl">
                      Webpage is currently not available
                    </h1>
                  </div>
                </div>

                <p>
                  We're sorry, but the webpage you are trying to access is
                  currently unavailable. Here are a few things you can try:-
                  <ul className="list-disc pl-4">
                    <li>Check your internet connection.</li>
                    <li>Refresh your browser.</li>
                    <li>
                      You may have entered a wrong URL address. Please
                      double-check the URL and try again.
                    </li>
                    <li>Contact your service administrator.</li>
                    <li>Check your proxy service settings if you use one.</li>
                  </ul>
                </p>
              </div>
            </div>
          )
        }
      />
    </>
  );
};

export default InternetConnetion;
