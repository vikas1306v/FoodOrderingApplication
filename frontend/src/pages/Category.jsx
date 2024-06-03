import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {addItems} from "../redux/slices/ItemSlice"


const CategoryItem = ({category}) => {
  const dispatch=useDispatch();
  const handleCategoryClick=async ()=>{
    try{
      const data = await fetch(`http://localhost:8080/foodapp/category/all/item/${category.categoryId}`)
      const jsonData = await data.json()
      if(jsonData.status){
        dispatch(addItems(jsonData))
        return ;
      }
      if(jsonData.status==false){
        alert("no data found")
      }
    }catch(Exception){
      console.log("server is not running or something went wrong which fetching data")  
    }

  }
  
  return (
  <button onClick={handleCategoryClick}>
    <div className="category-item">
    <div className="flex flex-col items-center p-5">
      <img src={category.categoryImage} alt ="category"className="category-image"   />
    </div>
    <h3 className="category-name flex justify-center flex-col sm:text-sm lg:text-xl" >{category.categoryName}</h3>
  </div>
  </button>
);
}

const Category = () => {
  const [categoryData,setCategoryData] =useState([])
  const getAllCategory = async () => {
    try{
      const data = await fetch("http://localhost:8080/foodapp/category/all")
      const jsonData = await data.json()
      if(jsonData.status){
        setCategoryData(jsonData.data)
        return ;
      }
      if(jsonData.status==false){
        alert("no data found")
      }
    }catch(Exception){
      console.log("server is not running or something went wrong which fetching data")  
    }
  }
  useEffect(()=>{
    getAllCategory()
  },[])
  return (
    <>
      <div className="flex justify-center">
        <div className="category-container  justify-between  w-3/4 mt-[9px] ">
          {categoryData.length>0?categoryData.map((data, index) => (
            <CategoryItem key={index} category={data} />
          )):null}
        </div>
      </div>
      <div className="flex justify-center">
        <hr className="w-3/4 mt-3" style={{ border: "1px solid rgb(210,204,240)" }} />
      </div>
    </>
  );
};

export default Category;