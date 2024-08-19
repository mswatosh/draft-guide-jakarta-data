async function loadQueries() {
    const response = await fetch("shipping/packageQuery")

    if (response.ok) {
        const list = await response.json();
        list.forEach(addToQueries)
    }
}

function addToQueries(item) {
    var button = document.createElement("button")
    button.setAttribute("onclick","")
    button.innerHTML = item

    var node = document.getElementById("querySection")
    node.appendChild(button)
    
    //<button id="findAllButton" class="buttons queryButton selectedQuery" onclick="setActiveQuery('findAll')">findAll</button>
} 