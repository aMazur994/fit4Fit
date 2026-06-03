import React from "react";

export const arrayRange = (start, stop, step) =>
    Array.from(
        { length: (stop - start) / step + 1 },
        (value, index) => start + index * step
    );
export function getReps() {
    return [...new Array(25)]
        .map((each,index) => ({label: index, value: index}))
}
export function getSec() {
    return [0, 15, 30, 45, 60, 75, 90, 105, 120, 135, 150, 165, 180];
}
export function getCombobox(nums){
    return (
        <div className="IntRepsFunction">
            {/* eslint-disable-next-line no-undef */}
            <select>
                <option value="?? Select a fruit ??"></option>
                {/* Mapping through each fruit object in our fruits array
          and returning an option element with the appropriate attributes / values.
         */}
                {nums.map((number) => {
                    return (<option >{number} </option>)
                })}
            </select>
        </div>
    );
}