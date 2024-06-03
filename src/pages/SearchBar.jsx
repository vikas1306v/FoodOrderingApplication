import React, { useEffect, useState } from "react";
import SearchMainCard from "../components/SearchMainCard";
import { Pagination } from "flowbite-react";




const SearchBar = () => {
  const [search, setsearch] = useState("");
  const [totalPages, setTotalPages] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [searchData, setsearchData] = useState([]);
  const[error,setError]=useState(false);
  const [isData, setIsData] = useState(false);
  const onPageChange = (page) => setCurrentPage(page);

  const handleChange = (e) => {
    setsearch(e.target.value);
  };
  const fetchData = async () => {
    try {
      const response = await fetch(`http://localhost:8080/foodapp/item/search?search=${search}&page=${currentPage}&size=5`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        }
      });

      const data = await response.json();
      if(data.status==false){
        setIsData(false);
        setError(true);
        return;
      }
      if(data.data.length>0){
        setError(false);    
        setIsData(true);
        setsearchData(data.data);
        setTotalPages(data.page.total_page);
      }
    } catch (error) {
      setError(true);
      setIsData(false);
    }
  }


  useEffect(() => {
    if(search==""){
      setIsData(false);
      setError(false);
      setsearchData([]);
    }
    if (search !== "") {
      fetchData();
    }
  }, [search, currentPage]);




  return (
    <>
      <div className="mt-16 mb-10"

      >
        <form class=" mx-auto" style={{ width: '860px' }}>
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
          <div className="search_result mt-4 flex flex-col  ">      
            {error==false&&isData==true?searchData.map((data, index) => <SearchMainCard data={data} key={index} />
            ):null}
            {
error==true&&isData==false?
              <h1 className="text-2xl ml-[360px] font-bold">No Data Found</h1>:null
            }
          </div>

        </form>
       {
         error==false&&isData==true?<div className="flex overflow-x-auto sm:justify-center">
         <Pagination color="red" currentPage={currentPage} totalPages={totalPages} onPageChange={onPageChange} showIcons />
       </div>:null
       }
      </div>

    </>
  );
};

export default SearchBar;
