import React from "react";

import { useState, useEffect } from "react";

function MyLocation() {
  const [userAddress, setUserAddress] = useState(null);

  useEffect(() => {

   
    const fetchUserAddress = async (latitude, longitude) => {
      try {
        const response = await fetch(
          `https://nominatim.openstreetmap.org/reverse?format=json&lat=${latitude}&lon=${longitude}&zoom=18&addressdetails=1`
        );
        const data = await response.json();
        console.log(data.address.postcode);
        
        setUserAddress(data.display_name);
      } catch (error) {
        console.error("Error fetching user address:", error);
      }
    };

    const getLocation = () => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            const { latitude, longitude } = position.coords;
            fetchUserAddress(latitude, longitude);
          },
          (error) => {
            console.error("Error getting geolocation:", error);
          }
        );
      } else {
        console.log("Geolocation is not supported by this browser.");
      }
    };

    getLocation();

  }, []);

  return (
  <>
   {userAddress}
  </>
  );
}

export default MyLocation;
