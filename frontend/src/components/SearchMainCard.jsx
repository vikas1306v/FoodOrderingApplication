import React from 'react'
import wifiicon from "../assets/wifiicon.png";
import { Link } from 'react-router-dom';
import food_3 from "../assets/NewAssets/food_3.png";

const SearchMainCard = (props) => {

 
  return (
    <Link
    to="/about"
    className="h-24 flex border-black  hover:bg-slate-200 " 
  >
    <div className='border-black' >
      <img src={food_3} className="w-full h-full p-3" alt="Wifi Icon" />
    </div>
    <div className="flex flex-col justify-center ml-3">
      <h1 className="">{props.data.itemName}</h1>
      <h1 className="">{props.data.itemDescription}</h1>
    </div>
  </Link>
  )
}

export default SearchMainCard
