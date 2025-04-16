function displayCourseList(params) {
    fetch('http://localhost:8080/')
    .then(response => response.json())
    .then(data => printCourses(data))
}

function printCourses(courses){
    var table = "<table>"
    for(i = 0; i < courses.length; i++){
        table += "<tr>"
        table += "<td>" + courses[i].name
    }
}