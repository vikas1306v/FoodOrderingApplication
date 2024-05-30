import React from "react";

import FilterPageItems from "./FilterPageItems";
const Items = () => {
  const arr = [1, 2, 3, 4, 5, 6, 7, 8];

  return (
    <>
      <div className="flex justify-center">
        <section class="text-gray-600 body-font w-3/4">
          <div class="container py-4 ">
            <div class="flex flex-wrap mx-2">
              {arr.map(() => (
                <FilterPageItems />
              ))}
            </div>
          </div>
        </section>
      </div>
    </>
  );
};

export default Items;
