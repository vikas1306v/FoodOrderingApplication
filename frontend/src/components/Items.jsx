import React from "react";

import FilterPageItems from "./FilterPageItems";
const Items = () => {
  const arr = [1, 2, 3, 4, 5, 6, 7, 8];

  return (
    <>
      <div class="flex justify-center">
    <section class="text-gray-600 body-font w-3/4">
        <div class="container py-4">
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-3 gap-4 mx-2">
                {arr.map((item, index) => (
                    <FilterPageItems key={index} />
                ))}
            </div>
        </div>
    </section>
</div>
    </>
  );
};

export default Items;
