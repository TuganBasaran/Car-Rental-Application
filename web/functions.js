function displayCourseList() {
    fetch('http://localhost:8080/cars')
    .then(response => response.json())
    .then(data => printCourses(data))
}

function printCourses(cars){
    var carList = document.createElement("p");
    carList.innerHTML = cars;
    console.log(cars);
    var body = document.getElementById("bell");
    body.appendChild(cars);
}