async function loadQueries() {
    const response = await fetch("shipping/packageQuery")

    if (response.ok) {
        const list = await response.json();
        list.forEach(addToQueries)
    }
}

function addToQueries(item) {
    var button = document.createElement("button")
    button.setAttribute("onclick","callQuery({ 'method' : '" + item.name + "' })")
    button.innerHTML = item.name

    var node = document.getElementById("querySection")
    node.appendChild(button)
    
    //<button id="findAllButton" class="buttons queryButton selectedQuery" onclick="setActiveQuery('findAll')">findAll</button>
} 

async function callQuery(query) {
    const response = await fetch("shipping/packageQuery", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(query),
	})

	if (response.ok) {
		const body = await response.text();
		if (body.length > 0) {
            var node = document.getElementById("resultsSection")
            node.replaceChildren() //clear the results section

			for (m of JSON.parse(body)) {
				var div = document.createElement("div")
                div.innerHTML = "id = " + m.id;
                div.innerHTML += " length = " + m.length;
                div.innerHTML += " width = " + m.width;
                div.innerHTML += " height = " + m.height;
                node.appendChild(div)
			}
		}
	} else {
		toast("Error! TODO better message",0)
	}

    console.log(response);
}