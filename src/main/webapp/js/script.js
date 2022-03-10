var containerContent = [0, 0, 0];
var currentTargetContainer;
var contracts;
var targetAmount;
var sourceAmount;


function load() {
    loadConfig();
    initContant("next");
    initContant("not");
}

function nextPick(next){
    newContant();
    initContant(next);


}


function loadConfig() {
    fetch('./configServlet')
        .then(
            function (resp) {
                if (resp.status !== 200) {
                    console.log('Status Code: ' + resp.status);
                    return;
                }
                resp.json().then(function (data) {
                    targetAmount = data.targetAmount;
                    sourceAmount = data.sourceAmount;
                    initContainer();
                });
            }
        ).catch(function (err) {
        console.log('Fetch Error:-S', err);
    });
}


function initContainer() {
    var sourceContainerRow = document.getElementById("sourceContainerRow");
    sourceContainerRow.style.gridTemplateColumns = "repeat(" + sourceAmount + ", 1fr)";
    usedContainers = new Array(sourceAmount).fill(false);
    contracts = new Array(sourceAmount).fill(false);
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

function clearAll(){
    deleteLine();
    for (let i = 1; i < sourceAmount+1; i++) {
        document.getElementById("indicator" + i).innerText = " ";
        document.getElementById('sourceContainer' + i).classList.remove("active");
        document.getElementById('sourceContainer' + i).classList.remove("next");

    }
    for (let i = 1; i < targetAmount+1; i++) {
        document.getElementById("amount" + i).innerText = " ";
        document.getElementById("source" + i).innerText = " ";
        document.getElementById("nextSource" + i).innerText = " ";
        document.getElementById('targetContainer' + i).classList.remove("active");
        document.getElementById('targetContainer' + i).classList.remove("next");
    }
    document.getElementById("orderNumber").innerText = "Auftrag: ";
    document.getElementById("brand").innerText = " ";
    document.getElementById("productName").innerText = " ";
    document.getElementById("color").innerText = " ";
    document.getElementById("size").innerText = " ";
}





function initContant(next) {
    clearAll();
    fetch('./testDataServlet?next=' + next)
        .then(
            function (resp) {
                if (resp.status !== 200) {
                    console.log('Status Code: ' + resp.status);
                    return;
                }
                resp.json().then(function (pick) {
                    console.log("GET");
                    console.log(pick);
                    if("next" == next){
                        if(!document.getElementById('sourceContainer' + pick.source).classList.contains("active")){
                            document.getElementById('sourceContainer' + pick.source).classList.add("next");

                        }
                        if(!document.getElementById('targetContainer' + pick.destination).classList.contains("active")){
                            document.getElementById('targetContainer' + pick.destination).classList.add("next");
                        }
                        drawNext(document.getElementById('sourceContainer' + pick.source),
                            document.getElementById('targetContainer' + pick.destination));
                    }else{
                        document.getElementById("amount" + pick.destination).innerText = "x" + pick.amount;
                        document.getElementById("source" + pick.destination).innerText = pick.productName;
                        document.getElementById("indicator" + pick.source).innerText = pick.productName;

                        document.getElementById("orderNumber").innerText += pick.orderNumber;
                        document.getElementById("brand").innerText = pick.productBrand;
                        document.getElementById("productName").innerText = pick.productName;
                        document.getElementById("color").innerText = pick.productColor;
                        document.getElementById("size").innerText = pick.productSize;

                        drawActive(document.getElementById('sourceContainer' + pick.source),
                            document.getElementById('targetContainer' + pick.destination));

                        document.getElementById('sourceContainer' + pick.source).classList.add("active");
                        document.getElementById('targetContainer' + pick.destination).classList.add("active");
                    }
                });
            }
        ).catch(function (err) {
        console.log('Fetch Error:-S', err);
    });
}

var data = { next: "next" };
function newContant() {
    /*
    const options = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data),
    }

     */
    clearAll();
    fetch('./testDataServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(
            function (resp) {
                if (resp.status !== 200) {
                    console.log('Status Code: ' + resp.status);
                    return;
                }
                resp.json().then(function (pick) {
                    console.log(pick);

                    document.getElementById("amount" + pick.destination).innerText = "x" + pick.amount;
                    document.getElementById("source" + pick.destination).innerText = pick.productName;
                    document.getElementById("indicator" + pick.source).innerText = pick.productName;

                    document.getElementById("orderNumber").innerText += pick.orderNumber;
                    document.getElementById("brand").innerText = pick.productBrand;
                    document.getElementById("productName").innerText = pick.productName;
                    document.getElementById("color").innerText = pick.productColor;
                    document.getElementById("size").innerText = pick.productSize;

                    drawActive(document.getElementById('sourceContainer' + pick.source),
                        document.getElementById('targetContainer' + pick.destination));

                    document.getElementById('sourceContainer' + pick.source).classList.add("active");
                    document.getElementById('targetContainer' + pick.destination).classList.add("active");

                    /*
                    myDrawFunction(document.getElementById('sourceContainer' + pick.source),
                        document.getElementById('targetContainer' + pick.destination), false);

                    document.getElementById('sourceContainer' + pick.source).classList.add("next");
                    document.getElementById('targetContainer' + pick.destination).classList.add("next");

                     */
                });
            }
        ).catch(function (err) {
        console.log('Fetch Error:-S', err);
    });
}

var data = { next: "false" };
function newContantNext() {
    /*
    const options = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data),
    }

     */
    clearAll();
    fetch('./testDataServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(
            function (resp) {
                if (resp.status !== 200) {
                    console.log('Status Code: ' + resp.status);
                    return;
                }
                resp.json().then(function (pick) {
                    console.log(pick);

                    drawNext(document.getElementById('sourceContainer' + pick.source),
                        document.getElementById('targetContainer' + pick.destination));

                    document.getElementById('sourceContainer' + pick.source).classList.add("next");
                    document.getElementById('targetContainer' + pick.destination).classList.add("next");

                    /*
                    myDrawFunction(document.getElementById('sourceContainer' + pick.source),
                        document.getElementById('targetContainer' + pick.destination), false);

                    document.getElementById('sourceContainer' + pick.source).classList.add("next");
                    document.getElementById('targetContainer' + pick.destination).classList.add("next");

                     */
                });
            }
        ).catch(function (err) {
        console.log('Fetch Error:-S', err);
    });
}



/*
function loadBoxContent() {
    deleteLine();
    fetch('./testDataServlet?next=N')
        .then(
            function (resp) {
                if (resp.status !== 200) {
                    console.log('Status Code: ' + resp.status);
                    return;
                }
                resp.json().then(function (data) {
                    var counter = 1;
                    data.forEach(pick => {
                        console.log(pick);
                        containerContent[counter - 1] = pick.orderNumber;
                        //Detail View

                        document.getElementById("amount" + counter).innerText = pick.amount;
                        document.getElementById("source" + counter).innerText = pick.productName;
                        document.getElementById("nextSource" + counter).innerText = pick.orderNumber;
                        document.getElementById("indicator" + pick.source).innerText = pick.productName;
                        if (counter === 1) {
                            myDrawFunction(document.getElementById('sourceContainer' + pick.source), document.getElementById('targetContainer' + counter), true);

                            document.getElementById("brand").innerText = pick.productBrand;
                            document.getElementById("productName").innerText = pick.productName;
                            document.getElementById("color").innerText = pick.productColor;
                            document.getElementById("size").innerText = pick.productSize;

                            currentTargetContainer = 1;
                        } else if (counter === 2) {
                            myDrawFunction(document.getElementById('indicator' + pick.source), document.getElementById('source' + counter), false);
                        }

                        counter++;
                    });
                });
            }
        ).catch(function (err) {
        console.log('Fetch Error:-S', err);
    });
}

function getNextBoxContent() {
    alert("yo");
    fetch('./testDataServlet?next=' + document.getElementById("nextSource" + (currentTargetContainer+1)).value)
        .then(
            function (resp) {
                if (resp.status !== 200) {
                    console.log('Status Code: ' + resp.status);
                    return;
                }
                resp.json().then(function (pick) {
                    alert("allah");
                    //Set boxes with new content
                    document.getElementById("amount" + currentTargetContainer).innerText = pick.amount;
                    document.getElementById("source" + currentTargetContainer).innerText = pick.productName;
                    document.getElementById("nextSource" + currentTargetContainer).innerText = pick.orderNumber;
                    document.getElementById("indicator" + pick.source).innerText = pick.productName;

                    //Set details
                    document.getElementById("brand").innerText = pick.productBrand;
                    document.getElementById("productName").innerText = pick.productName;
                    document.getElementById("color").innerText = pick.productColor;
                    document.getElementById("size").innerText = pick.productSize;

                    //New target
                    if(currentTargetContainer<=targetAmount-1){
                        currentTargetContainer = 0;
                    }else{
                        currentTargetContainer++;
                    }
                });
            }
        ).catch(function (err) {
        console.log('Fetch Error:-S', err);
    });
}

 */



