import React from "react";
import menu1 from "../assets/NewAssets/menu_1.png";
import menu2 from "../assets/NewAssets/menu_2.png";

const categories = [
  { imgSrc: {menu2}, name: "Pizza" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Burger" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Sushi" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Salad" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Pasta" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Burger" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Pasta" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Burger" }
];

const CategoryItem = ({ imgSrc, name }) => (
  <div className="category-item">
    <div className="flex flex-col items-center p-5">
      <img src={menu1} className="category-image" alt={name}  />
    </div>
    <h3 className="category-name flex justify-center flex-col sm:text-sm lg:text-xl" >{name}</h3>
  </div>
);

const Category = () => {
  return (
    <>
      <div className="flex justify-center">
        <div className="category-container  justify-between  w-3/4 mt-[9px] ">
          {categories.map((category, index) => (
            <CategoryItem key={index} imgSrc={category.imgSrc} name={category.name} />
          ))}
        </div>
      </div>
      <div className="flex justify-center">
        <hr className="w-3/4 mt-3" style={{ border: "1px solid rgb(210,204,240)" }} />
      </div>
    </>
  );
};

export default Category;