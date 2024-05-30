import React, { useEffect, useState } from "react";
import SearchMainCard from "../components/SearchMainCard";
import { Pagination } from "flowbite-react";
const SearchBar = () => {
  const [search, setsearch] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const onPageChange = (page) => setCurrentPage(page);

  const [searchData, setsearchData] = useState([]);

  const handleChange = (e) => {
    setsearch(e.target.value);
  };

  useEffect(() => {
    if (search !== "") {
      fetch(`https://picsum.photos/v2/list?page=2&limit=${currentPage*10}`)
      .then((res) => res.json())
      .then((data) => {
          // Assuming `setsearchData` is a function to set the search data
          setsearchData(data);
      })
      .catch((error) => {
          console.error('Error fetching data:', error);
      });
  
    }
  }, [search,currentPage]);
 

  return (
    <>
      <div className="mt-28 mb-10" >
        <form class=" mx-auto"  style={{width:'860px'}}>
          <label
            for="default-search"
            class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white"
          >
            search
          </label>
          <div class="relative">
            <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
              <svg
                class="w-4 h-4 text-gray-500 dark:text-gray-400"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 20 20"
              >
                <path
                  stroke="currentColor"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"
                />
              </svg>
            </div>
            <input
              type="search"
              id="default-search"
              class="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="search"
              onChange={handleChange}
              value={search}
              required
            />
          </div>
        <div className="search_result mt-4 flex flex-col space-y-3 ">
            {searchData.map((data, index) =>  <SearchMainCard data={data} key={index}/>
            )}
          </div>
         
        </form>
        <div className="flex overflow-x-auto sm:justify-center">
      <Pagination currentPage={currentPage} totalPages={100} onPageChange={onPageChange} showIcons />
    </div>
      </div>
      
    </>
  );
};

export default SearchBar;
