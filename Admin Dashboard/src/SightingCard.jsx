import React from "react";


function SightingCard(props) {
    return <>
        <div className="col-span-1 bg-white rounded-lg shadow mt-2">
            <div className="w-full flex items-center justify-between p-6 space-x-6">
              <div className="flex-1 truncate">
                <div className="flex items-center space-x-3">
                  <h3 className="text-gray-900 text-sm leading-5 font-medium truncate">
                    {props.title}
                  </h3>
                </div>
                <p className="mt-1 text-gray-500 text-sm leading-5">
                  <span className="flex-shrink-0 inline-block px-2 py-0.5 text-teal-800 text-xs leading-4 font-medium bg-green-100 rounded-full">
                    {props.notExtinct} Not Extinct
                  </span>
                  <span className="flex-shrink-0 ml-2 inline-block px-2 py-0.5 text-teal-800 text-xs leading-4 font-medium bg-red-100 rounded-full">
                    {props.endangered} Endangered
                  </span>
                </p>
              </div>
            </div>
            <div className="border-t border-gray-200">
              <div className="-mt-px flex">
                <div className="w-0 flex-1 flex border-r border-gray-200">
                  <a
                    href="#/"
                    className="relative -mr-px w-0 flex-1 inline-flex items-center justify-center py-4 text-sm leading-5 text-gray-700 font-medium border border-transparent rounded-bl-lg hover:text-gray-500 focus:outline-none focus:shadow-outline-blue focus:border-blue-300 focus:z-10 transition ease-in-out duration-150"
                    onClick={() => props.setCurrentCenter({lat: props.lat, lng: props.lng})}>
                    <span className="ml-3">Click to navigate</span>
                  </a>
                </div>
                <div className="-ml-px w-0 flex-1 flex">
                  <a
                    href="#/"
                    className="relative w-0 flex-1 inline-flex items-center justify-center py-4 text-sm leading-5 text-gray-700 font-medium border border-transparent rounded-br-lg hover:text-gray-500 focus:outline-none focus:shadow-outline-blue focus:border-blue-300 focus:z-10 transition ease-in-out duration-150">
                    <span className="ml-3">{props.sightings} sightings</span>
                  </a>
                </div>
              </div>
            </div>
          </div>
    </>
}

export default SightingCard;