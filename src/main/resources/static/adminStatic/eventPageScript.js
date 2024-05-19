// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};
window.onclick = function () {toggleContent()}
function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("scrollBtn").style.display = "block";
    } else {
        document.getElementById("scrollBtn").style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

function toggleContent() {
    var content = document.getElementById("hidden-content");
    if (content.style.display === "none") {
        content.style.display = "block";
    } else {
        content.style.display = "none";
    }
}

var isStatusAscending = true;
var isDateAscending = true;

function sortTableByStatus() {
    var table = document.querySelector("table");
    var rows = Array.from(document.querySelectorAll("table tbody tr"));

    rows.sort(function(a, b) {
        var statusA = a.cells[6].textContent === "true"; // Adjust based on how status is represented
        var statusB = b.cells[6].textContent === "true";
        if (isStatusAscending) {
            return statusA - statusB;
        } else {
            return statusB - statusA;
        }
    });

    // Apply the new order to the table
    for (var i = 0; i < rows.length; i++) {
        table.querySelector("tbody").appendChild(rows[i]);
    }

    // Toggle the sort direction for the next click
    isStatusAscending = !isStatusAscending;

}

function sortTableByDate() {
    var table = document.querySelector("table");
    var rows = Array.from(document.querySelectorAll("table tbody tr"));

    rows.sort(function(a, b) {
        var dateA = new Date(a.cells[5].textContent.split('.').reverse().join('-')); // Adjust date format accordingly
        var dateB = new Date(b.cells[5].textContent.split('.').reverse().join('-'));
        if (isDateAscending) {
            return dateA - dateB;
        } else {
            return dateB - dateA;
        }
    });

    // Apply the new order to the table
    for (var i = 0; i < rows.length; i++) {
        table.querySelector("tbody").appendChild(rows[i]);
    }

    // Toggle the sort direction for the next click
    isDateAscending = !isDateAscending;
}

function openSortOptions() {
    document.getElementById("sortModal").style.display = "block";
}

function closeSortOptions() {
    document.getElementById("sortModal").style.display = "none";
}

// Close the modal when the user clicks outside of it
window.onclick = function(event) {
    var modal = document.getElementById("sortModal");
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

