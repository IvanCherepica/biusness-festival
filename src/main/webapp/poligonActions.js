var festivalPoligons= {};



function getKeyByValue(object, value) {
    return Object.keys(object).find(key => object[key] === value);
}

function festilvalPoligonOnClick(event) {
    var currentPoligon = event.get('target');
    var currentPoligonID = getKeyByValue(festivalPoligons, currentPoligon);



    console.log('polygon clicked. currentPoligonID:' + currentPoligonID);
}