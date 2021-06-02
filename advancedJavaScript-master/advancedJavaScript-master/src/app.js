//var, let, const

let number1 = 10;
number1 = "test";
let student = {id:1, name:"someone"};
//console.log(student);

function save(point=10, student1){
    console.log(student1.name + " " + point);
}
//save(undefined, student);

let students = ["Somebody", "Someone", "Nobody", "NoOne"];
//console.log(students);

let students2 = [student, {id:2, name:"Somebody"}, "Ankara", {city:"İstanbul"}]
//console.log(students2);

//----------------------------------------------

//rest = params(C#) = varArgs(Java)

let showProducts = function(id,...products) {
    console.log(id);
    console.log(products[0]);
}
// console.log(typeof showProducts);
// showProducts(10,["Apple","Pear","Watermelon"])

//----------------------------------------------

//Spread
let points = [1,2,3,4,50,60,14];
console.log(...points)
console.log(Math.max(...points))
console.log(..."ABC", "D", ..."EFG", "H") // A B C D E F G H

//----------------------------------------------

//Destructuring
let populations = [10000, 20000, 30000, [4000,10000]]
let [small, medium, large, [veryhight, max]] = populations
console.log(small)
console.log(medium)
console.log(large)
console.log(veryhight)
console.log(max)

function someFunction([small1], number){
    console.log(small1)
}
someFunction(populations)

let category = {id:1, name:"Beverage"}
console.log(category.id);
console.log(category["name"]);

let{id,name} = category
console.log(id)
console.log(name)