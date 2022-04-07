//data array
var sourceAmount = 4;
var targetAmount = 3;

var position = 0;

const data = [
    { orderNumber: 1201, destination: 1, source: 4, amount: 1, productBrand: "Nike", productName: "Air Force 1", productColor: "Weiß", productSize: "46" },
    { orderNumber: 1201, destination: 1, source: 2, amount: 1, productBrand: "Carhartt WIP", productName: "Cargo", productColor: "Beige", productSize: "XL" },
    { orderNumber: 1201, destination: 1, source: 3, amount: 1, productBrand: "LFDY", productName: "Pullover", productColor: "Schwarz", productSize: "L" },
    { orderNumber: 1202, destination: 2, source: 1, amount: 1, productBrand: "Adidas", productName: "NMD R1", productColor: "Weiß", productSize: "46" },
    { orderNumber: 1202, destination: 2, source: 1, amount: 1, productBrand: "Y&H", productName: "T-Shirt", productColor: "Schwarz", productSize: "46" },
    { orderNumber: 1203, destination: 3, source: 4, amount: 1, productBrand: "Alpha Industries", productName: "Pullover", productColor: "Weiß", productSize: "XL" },
    { orderNumber: 1203, destination: 3, source: 1, amount: 2, productBrand: "Tommy Jeans", productName: "Hemd", productColor: "Blau", productSize: "L" },
    { orderNumber: 1203, destination: 3, source: 3, amount: 1, productBrand: "Pull&Bear", productName: "T-Shirt", productColor: "Schwarz", productSize: "L" },
    { orderNumber: 1203, destination: 3, source: 2, amount: 1, productBrand: "North Face", productName: "Jacke", productColor: "Schwarz", productSize: "XL" },
    { orderNumber: 1204, destination: 1, source: 4, amount: 2, productBrand: "Adidas", productName: "Socken", productColor: "Weiß", productSize: "42-46" },
    { orderNumber: 1205, destination: 2, source: 4, amount: 1, productBrand: "Alpha Industries", productName: "Pullover", productColor: "Weiß", productSize: "XL" },
    { orderNumber: 1205, destination: 2, source: 1, amount: 2, productBrand: "Tommy Jeans", productName: "Hemd", productColor: "Blau", productSize: "L" },
    { orderNumber: 1205, destination: 2, source: 3, amount: 1, productBrand: "Pull&Bear", productName: "T-Shirt", productColor: "Schwarz", productSize: "L" },
    { orderNumber: 1206, destination: 3, source: 4, amount: 2, productBrand: "Adidas", productName: "Socken", productColor: "Weiß", productSize: "42-46" },
    { orderNumber: 1206, destination: 3, source: 2, amount: 1, productBrand: "Burton", productName: "Hoodie", productColor: "Blau", productSize: "XL" },
    { orderNumber: 1206, destination: 3, source: 2, amount: 1, productBrand: "North Face", productName: "Jacke", productColor: "Pink", productSize: "XL" },
    { orderNumber: 1207, destination: 1, source: 3, amount: 1, productBrand: "Adidas", productName: "Hose", productColor: "Schwarz", productSize: "L" },
    { orderNumber: 1207, destination: 1, source: 2, amount: 1, productBrand: "Nike", productName: "Hose", productColor: "Grau", productSize: "L" },
    { orderNumber: 1207, destination: 1, source: 4, amount: 1, productBrand: "Alpha Industries", productName: "Pullover", productColor: "Weiß", productSize: "XL" },
    { orderNumber: 1207, destination: 1, source: 1, amount: 2, productBrand: "Tommy Jeans", productName: "Hemd", productColor: "Blau", productSize: "L" },
];
function next() {
    position++;
    displayData(data);
}
//onload
async function load() {
    initContainer();
    displayData(data);


}

//set container layout
async function initContainer() {
    var sourceContainerRow = document.getElementById("sourceContainerRow");
    sourceContainerRow.style.gridTemplateColumns = "repeat(" + sourceAmount + ", 1fr)";

    for (let i = 1; i < sourceAmount + 1; i++) {
        sourceContainerRow.innerHTML += '<div class=\"sourceContainer\" id=\"sourceContainer' + i + '\">\n' +
            '<span class=\"indicator\" id=\"indicator' + i + '\"></span>\n' +
            '</div>';
    }

    var targetContainerRow = document.getElementById("targetContainerRow");
    targetContainerRow.style.gridTemplateColumns = "repeat(" + targetAmount + ", 1fr)";
    for (let i = 1; i < targetAmount + 1; i++) {
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
    return fetch('./api/pick/', { method: 'POST', })
        .then((response) => response.json())
        .then((responseJson) => {
            return responseJson
        });
}

async function getLastPick() {
    return fetch('./api/pick/reverse/', { method: 'POST', })
        .then((response) => response.json())
        .then((responseJson) => {
            return responseJson
        });
}


//display Data
async function clearAll() {
    deleteLine();


    for (let i = 1; i < sourceAmount + 1; i++) {
        document.getElementById("indicator" + i).innerText = " ";
        document.getElementById('sourceContainer' + i).classList.remove("active");
        document.getElementById('sourceContainer' + i).classList.remove("next");

    }
    for (let i = 1; i < targetAmount + 1; i++) {
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
    let active = data[position];
    let next = data[position + 1];

    if (position == data.length - 1) {
        active = data[position];
        next = data[0];
        position = -1;
    }

    //console.log(active);
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

//Buttons
async function nextPick() {
    displayData(await getNewData());
}

async function reversPick() {
    displayData(await getLastPick());
}

function repeatPick(next) {

}

