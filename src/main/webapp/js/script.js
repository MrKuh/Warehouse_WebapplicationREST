//onload
async function load() {
    initContainer();
    displayData(await getData());

}
//set container layout
async function initContainer() {
    var config = await loadConfig();
    var sourceContainerRow = document.getElementById("sourceContainerRow");
    sourceContainerRow.style.gridTemplateColumns = "repeat(" + config.sourceAmount + ", 1fr)";

    for (let i = 1; i < config.sourceAmount + 1; i++) {
        sourceContainerRow.innerHTML += '<div class=\"sourceContainer\" id=\"sourceContainer' + i + '\">\n' +
            '<span class=\"indicator\" id=\"indicator' + i + '\"></span>\n' +
            '</div>';
    }

    var targetContainerRow = document.getElementById("targetContainerRow");
    targetContainerRow.style.gridTemplateColumns = "repeat(" + config.targetAmount + ", 1fr)";
    for (let i = 1; i < config.targetAmount + 1; i++) {
        targetContainerRow.innerHTML += "<div class=\"targetContainer\" id=\"targetContainer" + i + "\">\n" +
            "                    <span class=\"amount\" id=\"amount" + i + "\"> </span>\n" +
            "                    <span class=\"source\" id=\"source" + i + "\"> </span>\n" +
            "                    <span class=\"nextSource\" id=\"nextSource" + i + "\"> </span>\n" +
            "                </div>";
    }
}

//Fetch
async function loadConfig() {
    return fetch('./api/config')
        .then((response) => response.json())
        .then((responseJson) => {
            return responseJson
        });
}

async function getData() {
    return fetch('./api/pick')
        .then((response) => response.json())
        .then((responseJson) => {
            return responseJson
        });
}

async function getNewData() {
    return fetch('./api/pick/', {method: 'POST',})
        .then((response) => response.json())
        .then((responseJson) => {
            return responseJson
        });
}
async function getSummary() {
    return fetch('./api/pick/getSummary/', {method: 'POST',})
        .then((response) => response.json())
        .then((responseJson) => {
            return responseJson
        });
}
async function setSummary() {
    return fetch('./api/pick/setSummary/', {method: 'PUT',});
}

async function skipOrder() {
    await fetch('./api/pick/skipOrder/', {method: 'PUT',});
    displayData(await getData());
}

async function getLastPick() {
    return fetch('./api/pick/reverse/', {method: 'POST',})
        .then((response) => response.json())
        .then((responseJson) => {
            return responseJson
        });
}


//display Data
async function clearAll() {
    deleteLine();
    var config = await loadConfig();
    for (let i = 1; i < config.sourceAmount + 1; i++) {
        document.getElementById("indicator" + i).innerText = " ";
        document.getElementById('sourceContainer' + i).classList.remove("active");
        document.getElementById('sourceContainer' + i).classList.remove("next");

    }
    for (let i = 1; i < config.targetAmount + 1; i++) {
        document.getElementById("amount" + i).innerText = " ";
        document.getElementById("source" + i).innerText = " ";
        document.getElementById("nextSource" + i).innerText = " ";
        document.getElementById('targetContainer' + i).classList.remove("active");
        document.getElementById('targetContainer' + i).classList.remove("next");
    }
    document.getElementById("orderNumber").innerText = "Auftrag:    ";
    document.getElementById("brand").innerText = " ";
    document.getElementById("productName").innerText = " ";
    document.getElementById("color").innerText = " ";
    document.getElementById("size").innerText = " ";
}

async function displayData(data) {
    await clearAll();
    let active = data[0];
    let next = data[1];

    //active
    document.getElementById("amount" + active.destination).innerText = "x" + active.amount;
    document.getElementById("source" + active.destination).innerText = active.productName;
    document.getElementById("indicator" + active.source).innerText = active.productName;

    document.getElementById("orderNumber").innerText = "Auftrag: " + active.orderNumber;
    document.getElementById("brand").innerText = active.productBrand;
    document.getElementById("productName").innerText = active.productName;
    document.getElementById("color").innerText = active.productColor;
    document.getElementById("size").innerText = active.productSize;

    drawActive(document.getElementById('sourceContainer' + active.source),
        document.getElementById('targetContainer' + active.destination));

    document.getElementById('sourceContainer' + active.source).classList.add("active");
    document.getElementById('targetContainer' + active.destination).classList.add("active");

    //next
    if (!document.getElementById('sourceContainer' + next.source).classList.contains("active")) {
        document.getElementById('sourceContainer' + next.source).classList.add("next");

    }
    if (!document.getElementById('targetContainer' + next.destination).classList.contains("active")) {
        document.getElementById('targetContainer' + next.destination).classList.add("next");
    }
    drawNext(document.getElementById('sourceContainer' + next.source),
        document.getElementById('targetContainer' + next.destination));
}

async function displaySummary(data){
    console.log(data);

    document.getElementById("modal_auftragsnummer").innerText = "Auftrag: " + data[0].orderNumber;

    if(document.getElementById("modal_info").innerHTML != ""){
        document.getElementById("modal_info").innerHTML = "";
    }

    document.getElementById("modal_info").innerHTML = "<hr style='width: 100%; height: 2px'>"

    for (var i = 0; i < data.length; i++) {
        document.getElementById("modal_info").innerHTML +=
            "<span>" + data[i].amount + "x</span> <span>" +
            data[i].productBrand + ", " + data[i].productName + " (" + data[i].productColor + ")</span> <br>" +
            "<hr style='width: 100%'>"
    }
}

//Buttons
async function nextPick() {
    var summary = await getSummary();
    if(summary.length == 0) {
        var data = await getNewData();
        setSummary();
        displayData(data);
    }else{
        //console.log(summary);
        displaySummary(summary);

        modal.style.display = "block";
        returnButton.disabled = true;
        completeButton.disabled = true;
    }
    //displayData(await getNewData());
}

async function reversPick() {
    displayData(await getLastPick());
}

function repeatPick(next) {

}

var modal = document.getElementById("myModal");
var popup = document.getElementById("popupB");
var span = document.getElementsByClassName("close")[0];
var returnButton = document.getElementById("returnbtn");
var completeButton = document.getElementById("completebtn");

span.onclick = function () {
    modal.style.display = "none";
    returnButton.disabled = false;
    completeButton.disabled = false;
    nextPick();
}



