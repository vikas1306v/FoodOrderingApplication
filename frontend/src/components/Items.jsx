import React, { useEffect } from "react";
import FilterPageItems from "./FilterPageItems";
import { useDispatch, useSelector } from "react-redux";
import { addItems, removeAllItems } from "../redux/slices/ItemSlice";

const Items = () => {
  const dispatch = useDispatch();
  const itemsReduxData = useSelector((state) => state.item.items);


  const getAllItems = async () => {
    try {
      const response = await fetch("http://localhost:8080/foodapp/item/all");
      const jsonData = await response.json();
      if (jsonData.status) {
        // console.log(jsonData.data);
        dispatch(addItems(jsonData));
        return;
      }
      if (jsonData.status === false) {
        dispatch(removeAllItems());
      }
    } catch (error) {
      console.log("Server is not running or something went wrong while fetching data");
    }
  };

  useEffect(() => {
    getAllItems();
  }, []);

  return (
    <div className="flex justify-center">
      <section className="text-gray-600 body-font w-3/4">
        <div className="container py-4">
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-3 gap-4 mx-2">
            {itemsReduxData!=null
              ? itemsReduxData.map((item, index) => (
                  <FilterPageItems key={index} item={item} />
                ))
              : null}
          </div>
        </div>
      </section>
    </div>
  );
};

export default Items;
