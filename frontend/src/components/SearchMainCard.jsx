import React from 'react'
import wifiicon from "../assets/wifiicon.png";

const SearchMainCard = (props) => {

  const ImageParentDiv={
    width:'100px',
    height:'px'
  }
 
  return (
    <div className='h-24 flex' >
      <div className=''style={ImageParentDiv}>
      <img src={props.data.download_url} className="w-[100%] h-[100%]" alt="Wifi Icon" />
      </div>  
      <h1 className='ml-3 mt-9'>{props.data.itemName}</h1>    
      <h1 className='ml-3 mt-9'>{props.data.itemName}</h1>   
      <h1 className='ml-3 mt-9'>{props.data.itemName}</h1>   
    </div>
  )
}

export default SearchMainCard
