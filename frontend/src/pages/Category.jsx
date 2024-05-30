import React from "react";

const categories = [
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Pizza" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Burger" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Sushi" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Salad" },
  { imgSrc: "https://www.shutterstock.com/image-photo/fresh-tasty-burger-isolated-on-600nw-705104968.jpg", name: "Pasta" }
];

const CategoryItem = ({ imgSrc, name }) => (
  <div className="">
    <div className="flex p-6 flex-col ">
      <img src={imgSrc} className="category-image" alt={name} />
      <h3 className="flex justify-center">{name}</h3>
    </div>
  </div>
);

const Category = () => {
  return (
    <>
      <div className="flex justify-center">
        <div className="flex justify-between h-36 w-3/4" style={{ marginTop: "5px" }}>
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
