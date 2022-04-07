const cnv = document.getElementById("active");
const cnv2 = document.getElementById("next");
const jg = new jsGraphics(cnv);
const jg2 = new jsGraphics(cnv2);
let help;


function drawActive(source, target){
    const coords = getCoords(source, target);

    help = 0.8

    jg.setColor("#e700ff"); // lila
    jg.setStroke(6);

    jg.drawLine(coords[0] + coords[4] / 2, coords[1] + coords[5], coords[0] + coords[4] / 2,
        (coords[1] + coords[5] * help + coords[3]) / 2);

    jg.drawLine(coords[0] + coords[4] / 2, (coords[1] + coords[5] * help + coords[3]) / 2, coords[2] + coords[6] / 2,
        (coords[1] + coords[5] * help + coords[3]) / 2);
-
    jg.drawLine(coords[2] + coords[6] / 2, (coords[1] + coords[5] * help + coords[3]) / 2,
        coords[2] + coords[6] / 2, coords[3]);

    jg.drawPolyline([(coords[2] + (coords[6] / 2) * 0.9), coords[2] + coords[6] / 2, (coords[2] + (coords[6] / 2) * 1.1)],
        [(coords[3] + (coords[1] + coords[5] + coords[3]) / 2 * 1.1) / 2, coords[3],
            (coords[3] + (coords[1] + coords[5] + coords[3]) / 2 * 1.1) / 2]);

    jg.paint();
}


function drawNext(source, target){
    const coords = getCoords(source, target);

    help = 1.2

    jg2.setColor("#808080"); // grau
    jg2.setStroke(6);

    jg2.drawLine(coords[0] + coords[4] / 2, coords[1] + coords[5], coords[0] + coords[4] / 2,
        (coords[1] + coords[5] * help + coords[3]) / 2);

    jg2.drawLine(coords[0] + coords[4] / 2, (coords[1] + coords[5] * help + coords[3]) / 2, coords[2] + coords[6] / 2,
        (coords[1] + coords[5] * help + coords[3]) / 2);

    jg2.drawLine(coords[2] + coords[6] / 2, (coords[1] + coords[5] * help + coords[3]) / 2,
        coords[2] + coords[6] / 2, coords[3]);

    jg2.drawPolyline([(coords[2] + (coords[6] / 2) * 0.9), coords[2] + coords[6] / 2, (coords[2] + (coords[6] / 2) * 1.1)],
        [(coords[3] + (coords[1] + coords[5] + coords[3]) / 2 * 1.1) / 2, coords[3],
            (coords[3] + (coords[1] + coords[5] + coords[3]) / 2 * 1.1) / 2]);

    jg2.paint();
}

function getCoords(source, target) {
    const coords = [source.getBoundingClientRect().x, source.getBoundingClientRect().y,
        target.getBoundingClientRect().x, target.getBoundingClientRect().y,
        source.getBoundingClientRect().width, source.getBoundingClientRect().height,
        target.getBoundingClientRect().width, target.getBoundingClientRect().height];
    //console.log(coords);

    return coords;
}

function deleteLine(){
    jg.clear();
    jg2.clear();
}
