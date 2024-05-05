// Get ul elements from the document
var lists = document.getElementsByTagName("ul");

// Iterate through each element
for(var i = 0; i < lists.length; i++) {

    // Get each li from the ul element
    var entries = lists[i].getElementsByTagName("li");

    // Declare and fill arrays to do the sorting
    var links = [];
    var strings = [];
    var sorted = [];
    for(var j = 0; j < entries.length; j++) {
        links.push(entries[j].innerHTML);
        
        // Using toUpperCase() so it sorts alphabetically without checking for case.
        strings.push(entries[j].innerText.toUpperCase());
    }
    sorted = strings.slice().sort();

    // Iterate through each sorted innerText string and find the matching innerHTML for it.
    for(var j = 0; j < entries.length; j++) {
        for(var k = 0; k < entries.length; k++) {
            if(sorted[j] == strings[k]) {
                entries[j].innerHTML = links[k];
            }
        }
    }
}