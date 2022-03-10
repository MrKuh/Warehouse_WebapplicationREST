const cnv = document.getElementById("myCanvas");
const jg = new jsGraphics(cnv);

function myDrawFunction(source, target, check){
    const coords = getCoords(source, target);

    if (check){
        help = 0.8

        jg.setColor("#0000ff"); // blau
        jg.setStroke(6);
    }else {
        help = 1.2

        jg.setColor("#808080"); // grau
        jg.setStroke(6);
    }

    jg.drawLine(coords[0] + coords[4] / 2, coords[1] + coords[5], coords[0] + coords[4] / 2,
        (coords[1] + coords[5] * help + coords[3]) / 2);

    jg.drawLine(coords[0] + coords[4] / 2, (coords[1] + coords[5] * help + coords[3]) / 2, coords[2] + coords[6] / 2,
        (coords[1] + coords[5] * help + coords[3]) / 2);

    jg.drawLine(coords[2] + coords[6] / 2, (coords[1] + coords[5] * help + coords[3]) / 2,
        coords[2] + coords[6] / 2, coords[3]);

    jg.drawPolyline([(coords[2] + (coords[6] / 2) * 0.9), coords[2] + coords[6] / 2, (coords[2] + (coords[6] / 2) * 1.1)],
        [(coords[3] + (coords[1] + coords[5] + coords[3]) / 2 * 1.1) / 2, coords[3],
            (coords[3] + (coords[1] + coords[5] + coords[3]) / 2 * 1.1) / 2]);

    jg.paint();
}

function getCoords(source, target) {
    const coords = [source.getBoundingClientRect().x, source.getBoundingClientRect().y,
        target.getBoundingClientRect().x, target.getBoundingClientRect().y,
        source.getBoundingClientRect().width, source.getBoundingClientRect().height,
        target.getBoundingClientRect().width, target.getBoundingClientRect().height];
    console.log(coords);

    return coords;
}

function deleteLine(){
    jg.clear();
}
